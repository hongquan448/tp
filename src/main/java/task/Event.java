package task;

import ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Represents an event and contains the related functions.
 */
public class Event extends Task {

    public static final int EDIT_DESCRIPTION = 1;
    public static final int EDIT_DATE = 2;
    public static final int EDIT_START_TIME = 3;
    public static final int EDIT_END_TIME = 4;
    public static final int EDIT_PRIORITY = 5;
    public static final String ERROR_MESSAGE = "Error encountered during execution";
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int priority;

    /**
     * Setter for priority of the deadline.
     *
     * @param priority The new priority of the event.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Constructor for Event Class.
     *
     * @param description Description of the event.
     * @param date Date of specified Event.
     * @param startTime Start time of specified event.
     * @param endTime End time of specified event.
     * @param priority Priority of specified event.
     * @throws Exception If wrong format is used for date, time or priority fields.
     */
    public Event(String description, String date, String startTime, String endTime, String priority)
            throws Exception {
        taskType = TaskType.Event;
        parseDescription(description);
        parseDate(date);
        parseStartTime(startTime);
        parseEndTime(endTime);
        parsePriority(priority);
        if (this.startTime.isAfter(this.endTime)) {
            throw new Exception("Start time should be before End time");
        }
        if (this.date.isBefore(LocalDate.now())) {
            throw new Exception("Date specified must be a current or a future date");
        }
    }

    private void parsePriority(String priority) throws Exception {
        try {
            this.priority = Integer.parseInt(priority.strip());
        } catch (NumberFormatException e) {
            throw new Exception("Priority should be an integer");
        }
    }

    private void parseEndTime(String endTime) throws Exception {
        try {
            this.endTime = LocalTime.parse(endTime.strip());
        } catch (DateTimeParseException e) {
            throw new Exception("End time provided is invalid or in wrong format (Should be HH:MM)");
        }
    }

    private void parseStartTime(String startTime) throws Exception {
        try {
            this.startTime = LocalTime.parse(startTime.strip());
        } catch (DateTimeParseException e) {
            throw new Exception("Start time provided is invalid or in wrong format (Should be HH:MM)");
        }
    }

    private void parseDate(String date) throws Exception {
        try {
            this.date = LocalDate.parse(date.strip());
        } catch (DateTimeParseException e) {
            throw new Exception("Date provided is invalid or is in wrong format (Should be YYYY-MM-DD)");
        }

    }

    private void parseDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the information related to information in form of a string.
     *
     * @return eventInfo Contains information related to the event.
     */
    public String getTaskInformation() {
        String eventInfo = "[E] " + description + " at " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " from " + startTime.toString() + " to " + endTime.toString() + " with priority " + priority;
        return eventInfo;
    }

    /**
     * Returns the number of days left till the event.
     *
     * @return Number of days till event.
     */
    @Override
    public long numberOfDaysLeft() {
        return ChronoUnit.DAYS.between(LocalDate.now(),this.date);
    }

    /**
     * Returns the priority of the specified event.
     *
     * @return Priority of event.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns the date of the event.
     *
     * @return Date of event.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Used to identify if the string contains the keyword specified in its description.
     *
     * @param keyword The keyword to be matched with the description.
     * @return containsKeyword Indicates the presence/absence of keyword in the event's description.
     * @throws Exception If keyword entered is empty.
     */
    public boolean hasKeyword(String keyword) throws Exception {
        if (keyword.equals("")) {
            throw new Exception("Keyword is empty");
        }
        boolean containsKeyword = description.contains(keyword);
        return containsKeyword;
    }

    /**
     * Returns the event in the format used to store it in the data file.
     *
     * @return formattedEventDetails Contains the event details in the required format.
     */
    public String getFormattedDetails() {
        String formattedEventDetails = "E#" + description + "#" + date + "#" + startTime + "#" + endTime + "#"
                + priority + "\n";
        return formattedEventDetails;
    }

    /**
     * Edits the event at the index specified.
     *
     * @param ui Used to interact with the user.
     * @return Newly updated event.
     */
    public Event editEvent(Ui ui) {
        printOptionsToEdit(ui);
        int fieldToBeEdited = 0;
        fieldToBeEdited = getFieldToBeEdited(ui, false, fieldToBeEdited);
        switch (fieldToBeEdited) {
        case EDIT_DESCRIPTION:
            editDescription(ui);
            break;
        case EDIT_DATE:
            editDate(ui);
            break;
        case EDIT_START_TIME:
            editStartTime(ui);
            break;
        case EDIT_END_TIME:
            editEndTime(ui);
            break;
        case EDIT_PRIORITY:
            editPriority(ui);
            break;
        default:
            ui.printMessage(ERROR_MESSAGE);
            break;
        }
        printUpdatedDetails(ui);
        return this;
    }

    private void editPriority(Ui ui) {
        boolean exceptionEncountered;
        do {
            exceptionEncountered = false;
            ui.printMessage("Enter new priority:");
            String newPriorityString = ui.getUserIn();
            try {
                parsePriority(newPriorityString);
            } catch (Exception e) {
                ui.printMessage(e.getMessage());
                exceptionEncountered = true;
            }
        } while (exceptionEncountered);
    }

    private void editEndTime(Ui ui) {
        boolean exceptionEncountered;
        do {
            exceptionEncountered = false;
            ui.printMessage("Enter new End Time:");
            String newEndTimeString = ui.getUserIn();
            try {
                parseEndTime(newEndTimeString);
                if (this.endTime.isBefore(this.startTime)) {
                    throw new Exception("End time should be after start time");
                }
            } catch (Exception e) {
                ui.printMessage(e.getMessage());
                exceptionEncountered = true;
            }
        } while (exceptionEncountered);
    }

    private void editStartTime(Ui ui) {
        boolean exceptionEncountered;
        do {
            exceptionEncountered = false;
            ui.printMessage("Enter new Start Time:");
            String newStartTimeString = ui.getUserIn();
            try {
                parseStartTime(newStartTimeString);
            } catch (Exception e) {
                ui.printMessage(e.getMessage());
                exceptionEncountered = true;
            }
        } while (exceptionEncountered);
    }

    private void editDate(Ui ui) {
        boolean exceptionEncountered;
        do {
            exceptionEncountered = false;
            ui.printMessage("Enter new Date:");
            String newDateString = ui.getUserIn();
            try {
                parseDate(newDateString);
                if (this.date.isBefore(LocalDate.now())) {
                    throw new Exception("Date specified must be a current or a future date");
                }
            } catch (Exception e) {
                ui.printMessage(e.getMessage());
                exceptionEncountered = true;
            }
        } while (exceptionEncountered);
    }

    private void editDescription(Ui ui) {
        ui.printMessage("Enter new description:");
        String newDescription = ui.getUserIn();
        parseDescription(newDescription);
    }

    private int getFieldToBeEdited(Ui ui, boolean exceptionEncountered, int fieldToBeEdited) {
        do {
            exceptionEncountered = false;
            try {
                fieldToBeEdited = Integer.parseInt(ui.getUserIn());
                if (fieldToBeEdited > 5 || fieldToBeEdited < 0) {
                    throw new Exception();
                }
            } catch (Exception exception) {
                ui.printMessage("Please enter a valid number");
                exceptionEncountered = true;
            }
        } while (exceptionEncountered);
        return fieldToBeEdited;
    }

    private void printUpdatedDetails(Ui ui) {
        ui.printMessage("Updated Details:");
        ui.printMessage(this.getTaskInformation());
        ui.printLine();
    }

    private void printOptionsToEdit(Ui ui) {
        ui.printLine();
        ui.printMessage("The event details are as follows:");
        ui.printMessage(this.getTaskInformation());
        ui.printMessage("Which field of the event to edit? (Enter Corresponding Number)");
        ui.printMessage("1. Description");
        ui.printMessage("2. Date");
        ui.printMessage("3. Start Time");
        ui.printMessage("4. End Time");
        ui.printMessage("5. Priority");
        ui.printEmptyLine();
    }
}
