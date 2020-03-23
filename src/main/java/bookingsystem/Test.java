package bookingsystem;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Test {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Demonstrates a booking system for a study area.
     * Commands available are: book, check, cancel
     * @param args not in use
     */
    public static void main(String[] args) {
        BookingSystem studyArea = new BookingSystem("MacCommons", "ERC", "nil", true,
                true, 5);
        String command = null;
        String userId = null;
        Scanner input = new Scanner(System.in);

        while (!"bye".equals(command)) {
            System.out.println("\n_____________________________________________________________");
            System.out.println("Booking system for MacCommons");
            System.out.println("[book] to book the study area, [cancel] to cancel, [check] to "
                    + "check the " + "availability of the study area");
            System.out.println("_____________________________________________________________\n");

            command = input.nextLine();
            switch (command) {
            case "book":
                System.out.println("Please enter your id:");
                userId = input.nextLine();
                System.out.println("Hang in there... Processing your booking.");
                if (studyArea.bookStudyArea(userId)) {
                    System.out.println("Your booking has been confirmed.");
                } else if (!studyArea.isValidID(userId)) {
                    System.out.println("Please enter a valid matric number.");
                } else {
                    System.out.println("Sorry the study area is currently booked by someone else.");
                }
                break;
            case "check":
                if (studyArea.isBooked()) {
                    System.out.println("The study area is booked");
                } else {
                    System.out.println("It is free for booking");
                }
                break;
            case "cancel":
                if (studyArea.isBooked()) {
                    System.out.println("Please enter your id");
                    userId = input.nextLine();
                    System.out.println("Hang in there... Processing your request");
                    studyArea.removeBooking(userId);
                    if (studyArea.isBooked()) {
                        System.out.println("Sorry you are not the owner of the booking.");
                    } else {
                        System.out.println("Your booking has been cancelled.");
                    }
                } else {
                    System.out.println("The study area is not booked at the moment.");
                }
                break;
            default:
                LOGGER.log(Level.INFO, "Uncaught scenario");
                assert false;
                break;
            }
        }
    }
}
