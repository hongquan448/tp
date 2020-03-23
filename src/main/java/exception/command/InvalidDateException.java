package exception.command;

import exception.CustomException;

public class InvalidDateException extends CustomException {
    public InvalidDateException() {
        super("Date provided is invalid or is in wrong format (Should be YYYY-MM-DD)");
    }
}