import java.util.TimerTask;

public class ChangeDetector extends TimerTask {
    private final CommandHandler commandHandler;

    public ChangeDetector(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void run() {
        commandHandler.detectChanges();
    }
}
