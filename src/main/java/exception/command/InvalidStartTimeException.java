package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
public class InvalidStartTimeException extends CustomException {
    public InvalidStartTimeException() {
        super("Start time provided is invalid or in wrong format (Should be HH:MM) 24 Hr format");
    }
}