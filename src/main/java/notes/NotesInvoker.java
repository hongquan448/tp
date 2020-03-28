package notes;

import java.util.Scanner;



public class NotesInvoker {

    /**
     * Constructor to invoke Notes app.
     */
    public NotesInvoker() {
        Notes notes = new Notes();
        Scanner input = new Scanner(System.in);
        String choice;
        do {
            System.out.println("[add] to add a module");
            System.out.println("[remove] to remove a module");
            System.out.println("[enter] to choose a module to enter notes");
            System.out.println("[list] to list modules");
            System.out.println("[exit] to exit");
            String userInput = input.nextLine();
            choice = userInput.split(" ")[0];
            String code;
            switch (choice) {
            case "add":
                code = userInput.split(" ")[1];
                notes.createModule(code);
                break;
            case "remove":
                code = userInput.split(" ")[1];
                notes.deleteModule(code);
                break;
            case "enter":
                code = userInput.split(" ")[1];
                notes.enterModule(code);
                break;
            case "list":
                notes.listModules();
                break;
            case "exit":
                break;
            default:
                System.out.println("Please enter a valid command.");
            }

        } while (!choice.equals("exit"));
    }
}
