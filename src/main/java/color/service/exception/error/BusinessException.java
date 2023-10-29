package color.service.exception.error;

public class BusinessException extends RuntimeException {

    private ErrorMessage message;

    public BusinessException(ErrorMessage message) {
        this.message = message;
    }

    public ErrorMessage getErrorMessage() {
        return message;
    }
}
