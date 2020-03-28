package command;

import exception.command.EventCreationFormatNotFollowedException;
import exception.command.EventDetailsNotProvidedException;
import task.Event;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;

//@@author GanapathySanathBalaji
/**
 * This command is used to add new events to the TaskList.
 */
public class AddEventCommand extends Command {

    public static final String START_TIME_AFTER_END_TIME_ERROR_MESSAGE = "Start time after end time exception "
            + "not thrown";
    public static final String DATE_BEFORE_CURRENT_DATE_ERROR_MESSAGE = "Date past current day exception not thrown";
    public static final String SLASH_SYMBOL = "/";

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
        String[] details = eventDetails[1].split(SLASH_SYMBOL);
        if (details.length != 5) {
            throw new EventCreationFormatNotFollowedException();
        }
        String description = details[0];
        String date = details[1].substring(2);
        String startTime = details[2].substring(2);
        String endTime = details[3].substring(2);
        String priority = details[4].substring(2);
        newEvent =  new Event(description,date,startTime,endTime,priority);
        assert !newEvent.getStartTime().isAfter(newEvent.getEndTime()) : START_TIME_AFTER_END_TIME_ERROR_MESSAGE;
        assert !newEvent.getDate().isBefore(LocalDate.now()) : DATE_BEFORE_CURRENT_DATE_ERROR_MESSAGE;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui)  {
        taskList.addTask(newEvent, ui);
    }

}
