package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
public class NumberOfTasksToBeScheduledNotAnIntegerException extends CustomException {
    public NumberOfTasksToBeScheduledNotAnIntegerException() {
        super("Number of tasks to be scheduled as mentioned is not an integer");
    }
}