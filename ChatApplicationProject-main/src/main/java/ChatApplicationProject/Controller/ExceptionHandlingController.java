package ChatApplicationProject.Controller;

import ChatApplicationProject.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> generateBadRequestResponse() {
        return new ResponseEntity<String>(" User not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> generateBadCredentialException() {
        return new ResponseEntity<String>(" Credentials Invalid !!", HttpStatus.BAD_REQUEST);
    }
}