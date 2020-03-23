package exception.command;

import exception.CustomException;

public class SearchKeywordEmptyException extends CustomException {
    public SearchKeywordEmptyException() {
        super("Keyword to be used for search is empty");
    }
}