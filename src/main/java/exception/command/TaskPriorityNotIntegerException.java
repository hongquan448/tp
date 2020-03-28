package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
public class TaskPriorityNotIntegerException extends CustomException {
    public TaskPriorityNotIntegerException() {
        super("Priority should be an integer");
    }
}