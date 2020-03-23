package task;

import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Contains all the test related to EventList class.
 */
class TaskListTest {

    Ui ui = new Ui();
    task.TaskList taskList;

    public TaskListTest() throws Exception {
        taskList = new task.TaskList();
        taskList.addTask(new Event("Team Meeting", "2021-03-21", "09:00",
                "17:00", "1"),ui);
    }

    @Test
    void deleteEvent_invalidIndex_throwException() {
        try {
            taskList.deleteTask(2,ui);
        } catch (Exception e) {
            assertEquals("Invalid index entered. Please enter a valid index to be deleted",
                    e.getMessage());
        }
    }

    @Test
    void searchEvents_emptySearchKeyword_throwException() {
        try {
            taskList.searchTasks("",ui);
            fail("Empty string search not identified");
        } catch (Exception exception) {
            assertEquals("Keyword to be used for search is empty", exception.getMessage());
        }
    }

    @Test
    void clearCommand() {
        taskList.clearTasks(ui);
        assertEquals(0, taskList.getTaskListSize());
    }

}
