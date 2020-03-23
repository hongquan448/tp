package exception.command;

import exception.CustomException;

public class InvalidHelpCommandException extends CustomException {
    public InvalidHelpCommandException() {
        super("Wrong command used to view command list (Should be :help )");
    }
}