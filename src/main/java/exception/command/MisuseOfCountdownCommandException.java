package exception.command;

import exception.CustomException;

public class MisuseOfCountdownCommandException extends CustomException {
    public MisuseOfCountdownCommandException() {
        super("Wrong command used to view countdown of events (Should be :countdown )");
    }
}
