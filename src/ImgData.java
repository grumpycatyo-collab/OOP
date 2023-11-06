import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ImgData extends Data {
    private String imageSize;

    public ImgData(File file) {
        super(file, "Image Modification");
        try {
            this.imageSize = getImageSize(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            this.imageSize = "Unknown";
        }
    }

    public String getImageSize() {
        return imageSize;
    }

    @Override
    public String toString() {
        return super.toString() + ", imageSize='" + imageSize + '\'';
    }
    public static String getImageSize(Path file) throws IOException {
        String extension = file.toString().substring(file.toString().lastIndexOf('.') + 1).toLowerCase();
        if (extension.equals("png") || extension.equals("jpg")) {
            int width = 0;
            int height = 0;

            try {
                java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(file.toFile());
                width = img.getWidth();
                height = img.getHeight();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return width + "x" + height;
        }

        return "Not an image file";
    }
}
