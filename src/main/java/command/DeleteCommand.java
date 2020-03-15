package command;

import exception.command.DeleteCommandMissingIndexException;
import exception.command.IndexNotIntegerException;
import task.TaskList;
import ui.Ui;

/**
 * Command is used to delete a task at a specific index.
 */
public class DeleteCommand extends Command {

    /** Denotes the index at which the task should be deleted at. */
    int indexToDelete;

    /**
     * Constructor for the DeleteCommand object.
     * Creates a new DeleteCommand object if the correct command is used.
     *
     * @param commandSplit Contains information with regards to the index to be deleted at.
     * @param isOneWordCommand Denotes whether the user given input is single or multi worded.
     * @throws Exception If the index specified is invalid/ The wrong format is used for the command.
     */
    public DeleteCommand(String[] commandSplit, boolean isOneWordCommand) throws Exception {
        if (isOneWordCommand) {
            throw new DeleteCommandMissingIndexException();
        }
        try {
            indexToDelete = Integer.parseInt(commandSplit[1]);
        } catch (NumberFormatException e) {
            throw new IndexNotIntegerException();
        }
    }


    @Override
    public void executeCommand(TaskList taskList, Ui ui) throws Exception {
        taskList.deleteTask(indexToDelete, ui);
    }
}
