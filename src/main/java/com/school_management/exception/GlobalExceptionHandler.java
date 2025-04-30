package com.school_management.exception;

import com.school_management.dto.ResponseDTO;
import com.school_management.util.Constant;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleStudentNotFoundException(final UserNotFoundException exception) {
        exception.printStackTrace();
        ResponseDTO responseDTO = new ResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "Requested Resource Not Found", exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ResponseDTO> handleUnauthorizedException(final AuthorizationDeniedException exception) {
        exception.printStackTrace();
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.UNAUTHORIZED.value(), Constant.UNAUTHORIZED, exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseDTO> handleNoHandlerFoundException(final NoResourceFoundException exception) {
        exception.printStackTrace();
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.NOT_FOUND.value(), "Endpoint not found", exception.getResourcePath());
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResponseDTO> handleSignatureException(final SignatureException exception, WebRequest request) {
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.FORBIDDEN.value(), exception.getMessage(), request.getDescription(false));
        exception.printStackTrace();
        return new ResponseEntity<>(responseDTO, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ResponseDTO> handleExpiredJwtException(final SecurityException exception, WebRequest request) {
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.FORBIDDEN.value(), exception.getMessage(), request.getDescription(false));
        exception.printStackTrace();
        return new ResponseEntity<>(responseDTO, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SchoolNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleSchoolNotFoundException(final SchoolNotFoundException exception, WebRequest request) {
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getDescription(false));
        exception.printStackTrace();
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(StaleStateException.class)
//    public ResponseEntity<ResponseDTO>handleStaleStateException(final StaleStateException exception,WebRequest request){
//        ResponseDTO responseDTO=new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage(), request.getDescription(false));
//        exception.printStackTrace();
//        return new ResponseEntity<>(responseDTO,HttpStatus.NOT_ACCEPTABLE);
//    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleStudentException(final Exception exception, WebRequest request) {
        exception.printStackTrace();
        ResponseDTO responseDTO = new ResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationErrors(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getFieldErrors();
        String errorDetails = "";
        if (!fieldErrors.isEmpty()) {
            FieldError firstError = fieldErrors.get(fieldErrors.size() - 1);
            errorDetails = firstError.getField() + " : " + firstError.getDefaultMessage();
        }

        ResponseDTO responseDTO = new ResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                errorDetails,
                null
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
