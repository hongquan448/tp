package exception.command;

import exception.CustomException;

public class EmptyDescriptionException extends CustomException {
    public EmptyDescriptionException() {
        super("Description shouldn't be empty");
    }
}