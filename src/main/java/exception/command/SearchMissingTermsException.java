package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
public class SearchMissingTermsException extends CustomException {
    public SearchMissingTermsException() {
        super("The search string entered is empty.");
    }
}