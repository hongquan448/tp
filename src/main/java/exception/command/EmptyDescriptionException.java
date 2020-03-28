package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
public class EmptyDescriptionException extends CustomException {
    public EmptyDescriptionException() {
        super("Description shouldn't be empty");
    }
}