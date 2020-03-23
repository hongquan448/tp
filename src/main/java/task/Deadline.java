package task;

import exception.command.EmptyDescriptionException;
import exception.command.InvalidDateException;
import exception.command.InvalidDueTimeException;
import exception.command.SearchKeywordEmptyException;
import exception.command.TaskDateBeforeCurrentDateException;
import exception.command.TaskPriorityNotIntegerException;
import ui.Ui;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ui.Constants.AT;
import static ui.Constants.DATE_AFTER_CURRENT_DATE;
import static ui.Constants.DATE_PATTERN;
import static ui.Constants.DELIMITER;
import static ui.Constants.EDIT_DATE;
import static ui.Constants.EDIT_DESCRIPTION;
import static ui.Constants.EMPTY_STRING;
import static ui.Constants.ENTER_NEW_DATE_MESSAGE;
import static ui.Constants.ENTER_NEW_DESCRIPTION_MESSAGE;
import static ui.Constants.ENTER_NEW_PRIORITY_MESSAGE;
import static ui.Constants.ENTER_VALID_NUMBER_FROM_LIST_MESSAGE;
import static ui.Constants.ERROR_MESSAGE;
import static ui.Constants.INVALID_DATE;
import static ui.Constants.INVALID_DATE_ENTERED;
import static ui.Constants.INVALID_OPTION_ENTERED;
import static ui.Constants.INVALID_PRIORITY_VALUE;
import static ui.Constants.NEW_LINE_CHARACTER;
import static ui.Constants.OPTION_TO_EDIT_DATE;
import static ui.Constants.OPTION_TO_EDIT_DESCRIPTION;
import static ui.Constants.PRIORITY_NOT_INTEGER;
import static ui.Constants.SEARCH_KEYWORD_EMPTY;
import static ui.Constants.UPDATED_DETAILS;
import static ui.Constants.WITH_PRIORITY;
import static ui.Constants.WRONG_OPTION;


/**
 * Represents an deadline and contains the related functions.
 */
public class Deadline extends Task {

    private static final int EDIT_DUE_TIME = 3;
    public static final int EDIT_PRIORITY = 4;
    public static final String DEADLINE_IDENTIFIER = "D";
    public static final String DEADLINE_SYMBOL = "[D] ";
    public static final String ON = " on ";
    public static final String ENTER_NEW_DUE_TIME_MESSAGE = "Enter new due Time:";
    public static final String DEADLINE_DETAILS_AS_FOLLOWS_MESSAGE = "The deadline details are as follows:";
    public static final String ASK_FOR_OPTION_MESSAGE = "Which field of the deadline to edit? (Enter Corresponding "
            + "Number)";
    public static final String OPTION_TO_EDIT_DUE_TIME = "3. Due Time";
    public static final String OPTION_TO_EDIT_PRIORITY = "4. Priority";
    private static final String INVALID_DUE_TIME = "Invalid due time entered by user";
    private static final String INVALID_DUE_TIME_ENTERED = "Invalid due time entered by the user";
    private static final String EMPTY_DESCRIPTION_MESSAGE = "Description provided is empty";
    private String description;
    private LocalDate date;
    private LocalTime dueTime;
    private int priority;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


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

    /**
     * Parses the priority from the string entered by user for the priority field.
     *
     * @param priority String entered by user for the priority field.
     * @throws Exception If the provided priority isn't an integer.
     */
    private void parsePriority(String priority) throws Exception {
        try {
            this.priority = Integer.parseInt(priority.strip());
        } catch (NumberFormatException e) {
            LOGGER.log(Level.INFO, PRIORITY_NOT_INTEGER);
            throw new TaskPriorityNotIntegerException();
        }
    }

    /**
     * Parses the Due time from the string entered by user for the dueTime field.
     *
     * @param dueTime String entered by user for the dueTime field.
     * @throws Exception If the provided end time isn't valid.
     */
    private void parseDueTime(String dueTime) throws Exception {
        try {
            this.dueTime = LocalTime.parse(dueTime.strip());
        } catch (DateTimeParseException e) {
            LOGGER.log(Level.INFO, INVALID_DUE_TIME);
            throw new InvalidDueTimeException();
        }
    }

    /**
     * Parses the Date from the string entered by user for the date field.
     *
     * @param date String entered by user for the date field.
     * @throws Exception If the provided date isn't valid or is a past date.
     */
    private void parseDate(String date) throws Exception {
        try {
            this.date = LocalDate.parse(date.strip());
        } catch (DateTimeParseException e) {
            LOGGER.log(Level.INFO, INVALID_DATE);
            throw new InvalidDateException();
        }
        if (this.date.isBefore(LocalDate.now())) {
            LOGGER.log(Level.INFO, DATE_AFTER_CURRENT_DATE);
            throw new TaskDateBeforeCurrentDateException();
        }
    }

    /**
     * Parses the description from the string entered by user for the description field.
     *
     * @param description String entered by user for the description field.
     * @throws EmptyDescriptionException If the description of the task provided is empty.
     */
    private void parseDescription(String description) throws EmptyDescriptionException {
        if (description.trim().equals(Event.EMPTY_STRING)) {
            throw new EmptyDescriptionException();
        }
        this.description = description;
    }

    /**
     * Returns the information related to information in form of a string.
     *
     * @return deadlineInfo Contains information related to the deadline.
     */
    public String getTaskInformation() {
        String deadlineInfo = DEADLINE_SYMBOL + description + ON
                + date.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) + AT + dueTime.toString()
                + WITH_PRIORITY + priority;
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
        if (keyword.equals(EMPTY_STRING)) {
            LOGGER.log(Level.INFO, SEARCH_KEYWORD_EMPTY);
            throw new SearchKeywordEmptyException();
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
        String formattedDeadlineDetails = DEADLINE_IDENTIFIER + DELIMITER + description + DELIMITER + date + DELIMITER
                + dueTime + DELIMITER + priority + NEW_LINE_CHARACTER;
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
        int fieldToBeEdited;
        fieldToBeEdited = getFieldToBeEdited(ui);
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
            LOGGER.log(Level.SEVERE, WRONG_OPTION);
            ui.printMessage(ERROR_MESSAGE);
            break;
        }
        printUpdatedDetails(ui);
        return this;
    }

    /**
     * Used to edit the priority field of the deadline.
     *
     * @param ui Used to interact with the user.
     */
    private void editPriority(Ui ui) {
        boolean exceptionEncountered;
        do {
            exceptionEncountered = false;
            ui.printMessage(ENTER_NEW_PRIORITY_MESSAGE);
            String newPriorityString = ui.getUserIn();
            try {
                parsePriority(newPriorityString);
            } catch (Exception e) {
                LOGGER.log(Level.INFO, INVALID_PRIORITY_VALUE);
                ui.printMessage(e.getMessage());
                exceptionEncountered = true;
            }
        } while (exceptionEncountered);
    }

    /**
     * Used to edit the dueTime field of the deadline.
     *
     * @param ui Used to interact with the user.
     */
    private void editDueTime(Ui ui) {
        boolean exceptionEncountered;
        do {
            exceptionEncountered = false;
            ui.printMessage(ENTER_NEW_DUE_TIME_MESSAGE);
            String newStartTimeString = ui.getUserIn();
            try {
                parseDueTime(newStartTimeString);
            } catch (Exception e) {
                LOGGER.log(Level.INFO, INVALID_DUE_TIME_ENTERED);
                ui.printMessage(e.getMessage());
                exceptionEncountered = true;
            }
        } while (exceptionEncountered);
    }

    /**
     * Used to edit the date field of the deadline.
     *
     * @param ui Used to interact with the user.
     */
    private void editDate(Ui ui) {
        boolean exceptionEncountered;
        do {
            exceptionEncountered = false;
            ui.printMessage(ENTER_NEW_DATE_MESSAGE);
            String newDateString = ui.getUserIn();
            try {
                parseDate(newDateString);
                if (this.date.isBefore(LocalDate.now())) {
                    LOGGER.log(Level.INFO, DATE_AFTER_CURRENT_DATE);
                    throw new TaskDateBeforeCurrentDateException();
                }
            } catch (Exception e) {
                LOGGER.log(Level.INFO, INVALID_DATE_ENTERED);
                ui.printMessage(e.getMessage());
                exceptionEncountered = true;
            }
        } while (exceptionEncountered);
    }

    /**
     * Used to edit the description field of the deadline.
     *
     * @param ui Used to interact with the user.
     */
    private void editDescription(Ui ui) {
        boolean exceptionEncountered;
        do {
            exceptionEncountered = false;
            ui.printMessage(ENTER_NEW_DESCRIPTION_MESSAGE);
            String newDescription = ui.getUserIn();
            try {
                parseDescription(newDescription);
            } catch (Exception e) {
                LOGGER.log(Level.INFO, EMPTY_DESCRIPTION_MESSAGE);
                ui.printMessage(e.getMessage());
                exceptionEncountered = true;
            }
        } while (exceptionEncountered);
    }

    /**
     * Returns an integer denoting the field selected to be edited later.
     *
     * @param ui Used to interact with the user.
     * @return fieldToBeEdited Corresponds to the field to be edited.
     */
    private int getFieldToBeEdited(Ui ui) {
        boolean exceptionEncountered;
        int fieldToBeEdited = 0;
        do {
            exceptionEncountered = false;
            try {
                fieldToBeEdited = Integer.parseInt(ui.getUserIn());
                boolean isInvalidOption = fieldToBeEdited > 4 || fieldToBeEdited < 0;
                if (isInvalidOption) {
                    throw new Exception();
                }
            } catch (Exception exception) {
                LOGGER.log(Level.INFO, INVALID_OPTION_ENTERED);
                ui.printMessage(ENTER_VALID_NUMBER_FROM_LIST_MESSAGE);
                exceptionEncountered = true;
            }
        } while (exceptionEncountered);
        return fieldToBeEdited;
    }

    /**
     * Prints the updated details of the deadline after an edit command.
     *
     * @param ui Used to interact with user.
     */
    private void printUpdatedDetails(Ui ui) {
        ui.printMessage(UPDATED_DETAILS);
        ui.printMessage(this.getTaskInformation());
        ui.printLine();
    }

    /**
     * Prints the list of fields that could be edited as a list.
     *
     * @param ui Used to interact with user.
     */
    private void printOptionsToEdit(Ui ui) {
        ui.printLine();
        ui.printMessage(DEADLINE_DETAILS_AS_FOLLOWS_MESSAGE);
        ui.printMessage(this.getTaskInformation());
        ui.printMessage(ASK_FOR_OPTION_MESSAGE);
        ui.printMessage(OPTION_TO_EDIT_DESCRIPTION);
        ui.printMessage(OPTION_TO_EDIT_DATE);
        ui.printMessage(OPTION_TO_EDIT_DUE_TIME);
        ui.printMessage(OPTION_TO_EDIT_PRIORITY);
        ui.printEmptyLine();
    }
}