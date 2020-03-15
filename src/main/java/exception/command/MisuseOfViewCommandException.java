package exception.command;

import exception.CustomException;

public class MisuseOfViewCommandException extends CustomException {
    public MisuseOfViewCommandException() {
        super("Wrong command used to view events (Should be :view )");
    }
}