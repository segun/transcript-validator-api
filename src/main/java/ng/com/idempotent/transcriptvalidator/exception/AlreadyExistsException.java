package ng.com.idempotent.transcriptvalidator.exception;

public class AlreadyExistsException extends Exception {
    private static final long serialVersionUID = 961671544469761224L;

    public AlreadyExistsException(String message) {
        super(message);
    }
}
