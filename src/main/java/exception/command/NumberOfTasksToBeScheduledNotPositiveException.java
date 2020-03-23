package exception.command;

import exception.CustomException;

public class NumberOfTasksToBeScheduledNotPositiveException extends CustomException {
    public NumberOfTasksToBeScheduledNotPositiveException() {
        super("Number of tasks to be scheduled as mentioned is not a positive integer");
    }
}