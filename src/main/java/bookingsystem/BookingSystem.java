package bookingsystem;

import studyarea.StudyArea;
import java.util.logging.Logger;

public class BookingSystem extends StudyArea {
    private boolean isAvailable = true;
    private String bookedBy = null;

    /**
     * Constructs a StudyArea class for a study area with its information
     * Precondition: 'All variables have to be filled.
     *
     * @param name     name of StudyArea
     * @param address  address of StudyArea
     * @param faculty  faculty of StudyArea
     * @param hasPort  true for availability of ports, else false
     * @param isIndoor true for indoor, else false
     * @param maxPax   maximum number of seats for this StudyArea
     */
    public BookingSystem(String name, String address, String faculty, boolean hasPort, boolean isIndoor, int maxPax) {
        super(name, address, faculty, hasPort, isIndoor, maxPax);
        Logger logger = Logger.getLogger(BookingSystem.class.getName());
        logger.warning("This is a warning");
    }

    /**
     * Method for booking study area.
     * @param bookedBy id supplied by user in the form of matric number
     * @return true when successfully booked
     */
    public boolean bookStudyArea(String bookedBy) {
        if (isBooked()) {
            return false;
        } else {
            if (isValidID(bookedBy)) {
                isAvailable = false;
                this.bookedBy = bookedBy;
                return true;
            }
        }
        return false;
    }

    /**
     * Check if study area is available.
     * @return true if available
     */
    public boolean isBooked() {
        return !(isAvailable);
    }

    /**
     * Remove booking made by existing user.
     * @param id matric number of user that booked the system
     */
    public void removeBooking(String id) {
        if (idMatchesBookedBy(id)) {
            this.isAvailable = true;
            bookedBy = null;
        }
    }

    private boolean idMatchesBookedBy(String id) {
        return bookedBy.equals(id);
    }

    /**
     * Check if id is a valid matric number.
     * @param id matric number
     * @return true when it is valid
     */
    public boolean isValidID(String id) {
        if (id.length() != 9) {
            return false;
        }
        if (!(Character.isAlphabetic(id.charAt(0)) && Character.isAlphabetic(id.charAt(id.length() - 1)))) {
            return false;
        }
        return true;
    }
}
