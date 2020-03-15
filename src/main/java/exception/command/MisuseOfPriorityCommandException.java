package exception.command;

import exception.CustomException;

public class MisuseOfPriorityCommandException extends CustomException {
    public MisuseOfPriorityCommandException() {
        super("Wrong command used to view events sorted by priority (Should be :priority_view )");
    }
}