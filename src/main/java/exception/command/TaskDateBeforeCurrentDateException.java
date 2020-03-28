package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
public class TaskDateBeforeCurrentDateException extends CustomException {
    public TaskDateBeforeCurrentDateException() {
        super("Date specified must be a current or a future date");
    }
}