package command;

import exception.command.EventDetailsNotProvidedException;
import task.Event;
import task.TaskList;
import ui.Ui;

/**
 * This command is used to add new events to the Tasklist.
 */
public class AddEventCommand extends Command {

    /** The new event to be added. */
    Event newEvent;

    /**
     * Constructor for the AddCommand Object.
     * Abstracts out the details from the user input and uses it to create a new event object.
     *
     * @param eventDetails The array contains the information regarding the new event to be added.
     * @param isOneWordCommand Denotes whether the user given input is single or multi worded.
     * @throws Exception If the wrong format is used to create an event.
     */
    public AddEventCommand(String[] eventDetails, boolean isOneWordCommand) throws Exception {
        if (isOneWordCommand) {
            throw new EventDetailsNotProvidedException();
        }

        String[] details = eventDetails[1].split("/");
        String description = details[0];
        String date = details[1].substring(2);
        String startTime = details[2].substring(2);
        String endTime = details[3].substring(2);
        String priority = details[4].substring(2);
        newEvent =  new Event(description,date,startTime,endTime,priority);
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui) throws Exception {
        taskList.addTask(newEvent, ui);
    }

}
