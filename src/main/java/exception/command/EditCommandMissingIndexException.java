package exception.command;

import exception.CustomException;

public class EditCommandMissingIndexException extends CustomException {
    public EditCommandMissingIndexException() {
        super("Index to edited not mentioned");
    }
}