package task;

import ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static task.Event.EDIT_DATE;
import static task.Event.EDIT_DESCRIPTION;
import static task.Event.ERROR_MESSAGE;

/**
 * Represents an deadline and contains the related functions.
 */
public class Deadline extends Task {

    private static final int EDIT_DUE_TIME = 3;
    public static final int EDIT_PRIORITY = 4;
    private String description;
    private LocalDate date;
    private LocalTime dueTime;
    private int priority;

    /**
     * Setter for priority of the deadline.
     *
     * @param priority The new priority of the deadline.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Constructor for Deadline Class.
     *
     * @param description Description of the deadline.
     * @param date Date of specified Deadline
     * @param dueTime Due time of specified deadline.
     * @param priority Priority of specified deadline.
     * @throws Exception If wrong format is used for date, time fields.
     */
    public Deadline(String description, String date, String dueTime, String priority) throws Exception {
        taskType = TaskType.Deadline;
        parseDescription(description);
        parseDate(date);
        parseDueTime(dueTime);
        parsePriority(priority);
        if (this.date.isBefore(LocalDate.now())) {
            throw new Exception("Date specified must be a current or a future date");
        }
    }


    /**
     * Returns the number of days left till the event.
     *
     * @return Number of days till deadline.
     */
    @Override
    public long numberOfDaysLeft() {
        return ChronoUnit.DAYS.between(LocalDate.now(),this.date);
    }

    private void parsePriority(String priority) throws Exception {
        try {
            this.priority = Integer.parseInt(priority.strip());
        } catch (NumberFormatException e) {
            throw new Exception("Priority should be an integer");
        }
    }

    private void parseDueTime(String dueTime) throws Exception {
        try {
            this.dueTime = LocalTime.parse(dueTime.strip());
        } catch (DateTimeParseException e) {
            throw new Exception("Due time provided is invalid or in wrong format (Should be HH:MM)");
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
     * @return deadlineInfo Contains information related to the deadline.
     */
    public String getTaskInformation() {
        String deadlineInfo = "[D] " + description + " on " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            + " at " + dueTime.toString() + " with priority " + priority;
        return deadlineInfo;
    }

    /**
     * Returns the priority of the specified deadline.
     *
     * @return Priority of deadline.
     */
    public int getPriority() {
        return priority;
    }


    /**
     * Returns the date of the deadline.
     *
     * @return Date of deadline.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Used to identify if the string contains the keyword specified in its description.
     *
     * @param keyword The keyword to be matched with the description.
     * @return containsKeyword Indicates the presence/absence of keyword in the deadline's description.
     * @throws Exception If no keyword is entered.
     */
    public boolean hasKeyword(String keyword) throws Exception {
        if (keyword.equals("")) {
            throw new Exception("Keyword is empty");
        }
        boolean containsKeyword = description.contains(keyword);
        return containsKeyword;
    }

    /**
     * Returns the deadline in the format used to store it in the data file.
     *
     * @return formattedDeadlineDetails Contains the deadline details in the required format.
     */
    public String getFormattedDetails() {
        String formattedDeadlineDetails = "D#" + description + "#" + date + "#" + dueTime + "#" + priority + "\n";
        return formattedDeadlineDetails;
    }

    /**
     * Edits the event at the index specified.
     *
     * @param ui Used to interact with the user.
     * @return Newly updated event.
     */
    public Deadline editDeadline(Ui ui) {
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
        case EDIT_DUE_TIME:
            editDueTime(ui);
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

    private void editDueTime(Ui ui) {
        boolean exceptionEncountered;
        do {
            exceptionEncountered = false;
            ui.printMessage("Enter new due Time:");
            String newStartTimeString = ui.getUserIn();
            try {
                parseDueTime(newStartTimeString);
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
                if (fieldToBeEdited > 4 || fieldToBeEdited < 0) {
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
        ui.printMessage("The deadline details are as follows:");
        ui.printMessage(this.getTaskInformation());
        ui.printMessage("Which field of the event to edit? (Enter Corresponding Number)");
        ui.printMessage("1. Description");
        ui.printMessage("2. Date");
        ui.printMessage("3. Due Time");
        ui.printMessage("4. Priority");
        ui.printEmptyLine();
    }
}