package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
public class InvalidHelpCommandException extends CustomException {
    public InvalidHelpCommandException() {
        super("Wrong command used to view command list (Should be :help )");
    }
}