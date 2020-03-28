package task;

//@@author GanapathySanathBalaji

public class SchedulableTask {

    public String taskName;
    public int numberOfDaysLeft;
    public int numberOfDaysRequiredToFinishTask;


    /**
     * Sets the details of Schedulable Task object with the information provided in the splitDetails.
     *
     * @param splitDetails Contains all the details of the schedulable task.
     */
    public void setDetails(String[] splitDetails) {
        this.taskName = splitDetails[0];
        this.numberOfDaysRequiredToFinishTask = Integer.parseInt(splitDetails[1].substring(2)
                .trim());
        this.numberOfDaysLeft = Integer.parseInt(splitDetails[2].substring(2).trim());
    }

}
