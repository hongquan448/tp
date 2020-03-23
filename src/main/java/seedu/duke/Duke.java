package seedu.duke;

import command.StudyAreaCommand;
import command.TaskCommand;
import parser.Parser;
import resourceloader.StudyAreaLoader;
import resourceloader.TaskLoader;
import exception.IllegalStudyAreaException;
import studyarea.StudyAreaList;
import task.TaskList;
import ui.Constants;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static ui.Constants.INTERMEDIATE_MESSAGE;
import static ui.Constants.WRONG_INPUT;

/**
 * This is Duke class, which forms the main class of the program.
 */
public class Duke {

    private static TaskLoader taskLoader;
    protected static StudyAreaLoader studyAreaLoader;
    private static TaskList taskList = new TaskList();
    private static StudyAreaList studyAreaList;
    private static Ui ui = new Ui();
    private static Parser parser;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * This is the constructor to create a new Duke program every time user runs the main loop.
     */
    public Duke()  {
        try {
            setupLogger();
            parser = new Parser();
            taskLoader = new TaskLoader(Constants.FILE_PATH_EVENTS);
            taskList = new TaskList(taskLoader.loadFile());
            studyAreaLoader = new StudyAreaLoader(Constants.FILE_PATH_STUDY_AREAS);
            studyAreaList = new StudyAreaList(studyAreaLoader.pushToDatabase());
        } catch (FileNotFoundException | IllegalStudyAreaException e) {
            LOGGER.log(Level.SEVERE, Constants.EXCEPTION_ENCOUNTERED_MESSAGE, e);
            ui.printLine();
            ui.printMessage(e.getMessage());
            ui.printLine();
        }
    }

    private void setupLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.INFO);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        ch.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(ch);
        try {
            FileHandler fh = new FileHandler(Constants.DUKE_LOGGER_LOG);
            fh.setLevel(Level.INFO);
            fh.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, Constants.FILE_LOGGER_NOT_WORKING_MESSAGE, e);
        }
    }


    /**
     * This method runs the program.
     */
    public void run() {
        ui.printWelcomeMessage();
        LOGGER.log(Level.INFO, Constants.APPLICATION_STARTED_EXECUTION);
        boolean status = true;
        while (status) {
            int mode = ui.getMode();
            switch (mode) {
            case Constants.EXIT:
                LOGGER.log(Level.INFO, Constants.APPLICATION_GOING_TO_EXIT);
                status = false;
                break;
            case Constants.TASK_MODE_SELECTED:
                LOGGER.log(Level.INFO, Constants.TASK_MODE);
                TaskCommand.runCommands(taskList, ui, parser);
                ui.printMessage(INTERMEDIATE_MESSAGE);
                break;
            case Constants.STUDY_AREA_MODE_SELECTED:
                LOGGER.log(Level.INFO, Constants.STUDY_AREA_MODE);
                StudyAreaCommand.runCommands(studyAreaList,ui);
                ui.printMessage(INTERMEDIATE_MESSAGE);
                break;
            default:
                LOGGER.log(Level.INFO, Constants.WRONG_COMMAND);
                ui.printLine();
                ui.printMessage(WRONG_INPUT);
                break;
            }
            ui.printLine();
        }
        taskLoader.saveTasks(taskList.tasks);
        ui.printByeMessage();
        ui.close();
        LOGGER.log(Level.INFO, Constants.APPLICATION_CLOSED_SUCCESSFULLY);
    }


    /**
     * Main entry-point for the java.duke.Duke application.
     *
     * @param args this is an optional argument.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
