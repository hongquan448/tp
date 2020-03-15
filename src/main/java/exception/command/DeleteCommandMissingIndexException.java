package exception.command;

import exception.CustomException;

public class DeleteCommandMissingIndexException extends CustomException {
    public DeleteCommandMissingIndexException() {
        super("Index to delete not mentioned");
    }
}