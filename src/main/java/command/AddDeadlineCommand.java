package command;

import exception.command.EventDetailsNotProvidedException;
import task.Deadline;
import task.TaskList;
import ui.Ui;

/**
 * This command is used to add new deadlines to the Tasklist.
 */
public class AddDeadlineCommand extends Command {

    /** The new event to be added. */
    Deadline newDeadline;

    /**
     * Constructor for the AddCommand Object.
     * Abstracts out the details from the user input and uses it to create a new deadline object.
     *
     * @param deadlineDetails The array contains the information regarding the new deadline to be added.
     * @param isOneWordCommand Denotes whether the user given input is single or multi worded.
     * @throws Exception If the wrong format is used to create an event.
     */
    public AddDeadlineCommand(String[] deadlineDetails, boolean isOneWordCommand) throws Exception {
        if (isOneWordCommand) {
            throw new EventDetailsNotProvidedException();
        }
        String[] details = deadlineDetails[1].split("/");
        String description = details[0];
        String date = details[1].substring(2);
        String dueTime = details[2].substring(2);
        String priority = details[3].substring(2);
        newDeadline =  new Deadline(description, date, dueTime, priority);
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui) throws Exception {
        taskList.addTask(newDeadline, ui);
    }

}
