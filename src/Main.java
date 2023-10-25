import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command:");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            if (parts[0].equals("pika")) {
                if (parts.length >= 4) {
                    String message = "";
                    if (parts[3].startsWith("\"")) {
                        for (int i = 3; i < parts.length; i++) {
                            message += parts[i] + " ";
                        }
                        message = message.substring(1, message.length() - 2);
                    } else {
                        message = parts[3];
                    }
                    commandHandler.handlePikaCommand(parts[1], parts[2], message);
                } else if (parts.length >= 3) {
                    commandHandler.handlePikaCommand(parts[1], parts[2]);
                } else if (parts.length >= 2){
                    commandHandler.handlePikaCommand(parts[1]);
                }
            } else {
                System.out.println("Unknown command");
            }
        }



    }
}
