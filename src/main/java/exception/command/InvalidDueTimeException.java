package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
public class InvalidDueTimeException extends CustomException {
    public InvalidDueTimeException() {
        super("Due time provided is invalid or in wrong format (Should be HH:MM) 24 Hr format");
    }
}