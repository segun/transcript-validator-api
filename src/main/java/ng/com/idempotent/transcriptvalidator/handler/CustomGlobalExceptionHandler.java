package ng.com.idempotent.transcriptvalidator.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ng.com.idempotent.transcriptvalidator.exception.AlreadyExistsException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AlreadyExistsException.class, NotFoundException.class })
    public ResponseEntity<CustomErrorResponse> customHandleAlreadyExists(Exception ex, WebRequest request) {
        CustomErrorResponse errors = new CustomErrorResponse();
        HttpStatus status = HttpStatus.CONFLICT;
        System.out.println(ex.getClass().getSimpleName());
        switch (ex.getClass().getSimpleName()) {
            case "AlreadyExistsException":
                status = HttpStatus.CONFLICT;
                break;
            case "NotFoundException":
                status = HttpStatus.NOT_FOUND;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
        }

        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(status);
        return new ResponseEntity<>(errors, status);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        CustomErrorResponse error = new CustomErrorResponse();

        error.setError("Validation Failed: " + details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }    
}