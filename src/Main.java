import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command:");
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);

            if (parts.length >= 2 && parts[0].equals("pika")) {
                String command = parts[1];
                commandHandler.handlePikaCommand(command);
            } else {
                System.out.println("Unknown command");
            }
        }
    }
}
