package task;

import java.util.Comparator;

//@@author GanapathySanathBalaji

public class TaskComparator implements Comparator<SchedulableTask> {

    @Override
    public int compare(SchedulableTask schedulableTask1, SchedulableTask schedulableTask2) {
        return  schedulableTask1.numberOfDaysLeft - schedulableTask2.numberOfDaysLeft;
    }
}
