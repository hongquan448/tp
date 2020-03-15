package exception.command;

import exception.CustomException;

public class EventDetailsNotProvidedException extends CustomException {
    public EventDetailsNotProvidedException() {
        super("Event details are not provided");
    }
}
