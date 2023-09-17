public class Diamond {
    private int size;
    public Diamond(int size) {
        if (size % 2 == 0) {
            size += 1;
        }
        this.size = size;
    }

    public void printDiamond() {
        for (int i = 0; i < size; i++) {
            if (i < size / 2) {
                System.out.println(" ".repeat(size / 2 - i) + "*".repeat(2 * i + 1));
            } else if (i == size / 2) {
                System.out.println("*".repeat(size));
            } else {
                System.out.println(" ".repeat(i - size / 2) + "*".repeat(2 * (size - i) - 1));
            }
        }
    }
}




