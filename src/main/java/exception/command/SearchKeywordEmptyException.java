package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
public class SearchKeywordEmptyException extends CustomException {
    public SearchKeywordEmptyException() {
        super("Keyword to be used for search is empty");
    }
}