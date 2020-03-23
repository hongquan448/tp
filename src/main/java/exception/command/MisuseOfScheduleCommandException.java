package exception.command;

import exception.CustomException;

public class MisuseOfScheduleCommandException extends CustomException {
    public MisuseOfScheduleCommandException() {
        super("Wrong command to create schedule events (Should be :schedule <number of tasks> )");
    }
}
