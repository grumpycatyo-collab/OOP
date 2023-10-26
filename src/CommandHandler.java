public class CommandHandler {

    public void handlePikaCommand(String command) {
        handlePikaCommand(command, null, null);
    }

    public void handlePikaCommand(String command, String arg1) {
        handlePikaCommand(command, arg1, null);
    }

    public void handlePikaCommand(String command, String arg1, String arg2) {
        if (command.equals("commit")) {
            CommandRunner.commit(arg1, arg2);
        } else if (command.equals("info")) {
            if (arg1 != null) {
                CommandRunner.info(arg1);
            } else {
                System.out.println("Missing filename argument for 'info' command.");
            }
        } else if (command.equals("status")) {
            CommandRunner.status();
        } else if (command.equals("help")) {
            CommandRunner.help();
        } else {
            System.out.println("Unknown command");
        }
    }
}
