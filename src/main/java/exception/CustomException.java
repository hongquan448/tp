package exception;

public abstract class CustomException extends Exception {
    public CustomException(String errorMessage) {
        super(errorMessage);
    }
}
