package bg.bilet4e.prototype.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GenericExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex) {
        String msg = "An unexpected error occured. Try again in 60 seconds";
        ErrorResponseDTO error = new ErrorResponseDTO(msg);

        LOGGER.error("Unexpected exception occured", ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ErrorResponseDTO> handleBadCredentialsException(
            BadCredentialsException ex) {
        String msg = "Invalid credentials";
        ErrorResponseDTO error = new ErrorResponseDTO(msg);

        LOGGER.error(msg, ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // TODO : fix message
    public final ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream() //
                .map(this::createFieldErrorMessage) //
                .collect(Collectors.toList());

        ErrorResponseDTO error = new ErrorResponseDTO(errorMessages.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private String createFieldErrorMessage(FieldError fieldError) {
        String fieldName = fieldError.getField();
        Object fieldValue = fieldError.getRejectedValue();
        String reason = fieldError.getDefaultMessage();

        return "Field [" + fieldName + "] with value [" + fieldValue + "] is not valid because: "
                + reason;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<ErrorResponseDTO> handleResponseStatusException(
            ResponseStatusException ex) {
        String msg = ex.getReason();
        ErrorResponseDTO error = new ErrorResponseDTO(msg);

        LOGGER.error("Failed request",ex);
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<ErrorResponseDTO> handleUsernameNotFoundException(
            UsernameNotFoundException ex) {
        String msg = ex.getMessage();
        ErrorResponseDTO error = new ErrorResponseDTO(msg);

        LOGGER.error("Invalid username given for request", ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}