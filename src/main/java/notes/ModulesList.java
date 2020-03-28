package notes;

import notes.modules.ModuleManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ModulesList {
    static ModulesList instance = null;
    private HashMap<String, ArrayList<String>> modules;

    private ModulesList() {
        this.modules = new HashMap<>();
    }

    /**
     * Singleton check for null instance before initializing.
     * @return ModuleList
     */
    public static ModulesList getInstance() {
        if (instance == null) {
            instance = new ModulesList();
        }
        return instance;
    }

    /**
     * Create a module with code.
     *
     * @param code Module Code.
     */
    public void createModule(String code) {
        modules.putIfAbsent(code, new ArrayList<>());

        String success = String.format("%s has been created\n", code);
        System.out.println(success);
    }

    /**
     * Delete a module using its code.
     *
     * @param code Module code.
     */
    public void deleteModule(String code) {
        if (modules.containsKey(code)) {
            modules.remove(code);
            System.out.println(code + " has been removed");
        } else {
            System.out.println("Module is not found.");
        }
    }

    /**
     * Retrieves a module's content from the module given the module code.
     * @param code module code
     * @return module's content
     */
    public ArrayList<String> getModule(String code) {
        if (!modules.containsKey(code)) {
            return null;
        }
        return modules.get(code);
    }

    /**
     * Enters the module interface of moduleManager.
     * @param code module code
     */
    public void enterModule(String code) {
        if (getModule(code) == null) {
            System.out.println("Module not found.");
        } else {
            new ModuleManager(code, getModule(code));
        }
    }

    /**
     * Print all saved modules.
     */
    public void listModules() {
        if (modules.isEmpty()) {
            System.out.println("No modules have been added yet.");
        }
        System.out.println(modules.keySet());
    }
}
