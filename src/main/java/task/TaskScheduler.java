package task;

import ui.Constants;
import ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Handles creation of schedules based on user's requirements.
 */
public class TaskScheduler {

    public static final String NINE_AM = "09:00";
    public static final String NINE_PM = "21:00";
    public static final String PRIORITY_FOR_SCHEDULED_TASKS = "0";
    private int numberOfTasksToSchedule;
    private PriorityQueue<SchedulableTask> taskQueue;
    private ArrayList<Task> toBeAddedToList;

    /**
     * Constructor for TaskScheduler.
     *
     * @param numberOfTasksToSchedule Denotes the number of tasks to be scheduled.
     */
    public TaskScheduler(int numberOfTasksToSchedule) {
        this.numberOfTasksToSchedule = numberOfTasksToSchedule;
        this.taskQueue = new PriorityQueue<>(numberOfTasksToSchedule, new TaskComparator());
        toBeAddedToList = new ArrayList<>();
    }

    /**
     * Used to get details of the tasks to be scheduled from the user.
     *
     * @param ui Used to interact with the user.
     */
    public void getTasks(Ui ui) {
        ui.printLine();
        ui.printMessage(Constants.ENTER_TASKS_IN_THE_FOLLOWING_FORMAT);
        ui.printMessage(Constants.SCHEDULABLE_TASK_FORMAT);
        for (int taskNumber = 1; taskNumber <= numberOfTasksToSchedule; taskNumber++) {
            SchedulableTask newSchedulableTask = new SchedulableTask();
            ui.printMessage(Constants.ENTER_DETAILS + taskNumber + Constants.COLON);
            try {
                String taskDetails = ui.getUserIn();
                String[] splitDetails = taskDetails.split(Constants.FORWARD_SLASH);
                newSchedulableTask.taskName = splitDetails[0];
                newSchedulableTask.numberOfDaysRequiredToFinishTask = Integer.parseInt(splitDetails[1].substring(2)
                        .trim());
                newSchedulableTask.numberOfDaysLeft = Integer.parseInt(splitDetails[2].substring(2).trim());
                taskQueue.add(newSchedulableTask);
            } catch (Exception e) {
                ui.printMessage(e.getMessage());
                ui.printMessage(Constants.WRONG_FORMAT_MESSAGE);
                taskNumber--;
            }
        }
        ui.printMessage(Constants.DETAILS_CAPTURED_SUCCESSFULLY);
    }

    /**
     * Creates a schedule based on the user's requirement if it's possible.
     *
     * @param ui Used to interact with the user.
     * @return isFeasible Denotes whether the user provided task is schedulable.
     */
    public boolean scheduleTasks(Ui ui) {
        int currentDay = 0;
        LocalDate startDate;
        LocalDate endDate;
        String startOfDay = NINE_AM;
        String endOfDay = NINE_PM;
        String defaultPriority = PRIORITY_FOR_SCHEDULED_TASKS;
        StringBuilder scheduleCreated = new StringBuilder();
        boolean isFeasible = true;
        while (!taskQueue.isEmpty() && isFeasible) {
            SchedulableTask taskToBeScheduledNext = taskQueue.poll();
            scheduleCreated.append(taskToBeScheduledNext.taskName).append(Constants.FROM)
                    .append(LocalDate.now().plusDays(currentDay + 1));
            startDate = LocalDate.now().plusDays(currentDay);
            if (currentDay + taskToBeScheduledNext.numberOfDaysRequiredToFinishTask
                    > taskToBeScheduledNext.numberOfDaysLeft) {
                isFeasible = false;
            } else {
                currentDay += taskToBeScheduledNext.numberOfDaysRequiredToFinishTask;
            }
            scheduleCreated.append(Constants.TO).append(LocalDate.now().plusDays(currentDay))
                    .append(System.lineSeparator()).append(Constants.TAB);
            endDate = LocalDate.now().plusDays(currentDay);
            for (LocalDate iterator = startDate.plusDays(1); !iterator.isAfter(endDate);
                 iterator = iterator.plusDays(1)) {
                try {
                    toBeAddedToList.add(new Event(taskToBeScheduledNext.taskName, iterator.toString(), startOfDay,
                            endOfDay, defaultPriority));
                } catch (Exception e) {
                    ui.printMessage("Unexpected error");
                }
            }
        }
        ui.printMessage(scheduleCreated.toString());
        return isFeasible;
    }

    /**
     * Add the scheduled tasks to the current list.
     *
     * @param taskList Contains the current list of tasks.
     * @param ui Used to interact with the user.
     */
    public void addScheduleToList(TaskList taskList, Ui ui) {
        taskList.tasks.addAll(toBeAddedToList);
    }
}

