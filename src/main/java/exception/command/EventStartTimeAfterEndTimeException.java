package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
public class EventStartTimeAfterEndTimeException extends CustomException {
    public EventStartTimeAfterEndTimeException() {
        super("Start time should be before End time");
    }
}