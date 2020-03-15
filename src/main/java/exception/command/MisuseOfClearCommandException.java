package exception.command;

import exception.CustomException;

public class MisuseOfClearCommandException extends CustomException {
    public MisuseOfClearCommandException() {
        super("Wrong command clear events (Should be :clear )");
    }
}
