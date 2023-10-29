package color.service.exception.handler;

import color.service.exception.error.BusinessException;
import color.service.exception.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse businessExceptionHandler(BusinessException exception) {
        return new ErrorResponse(exception.getErrorMessage());
    }
}
