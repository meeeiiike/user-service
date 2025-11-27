package ie.atu.user_service.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDetails>> showErrorDetails(MethodArgumentNotValidException mae){
        List<ExceptionDetails> errorList = new ArrayList<>();
        for(FieldError fieldError : mae.getBindingResult().getFieldErrors()){
            errorList.add(new ExceptionDetails(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }

    @ExceptionHandler(DuplicateExceptionHandling.class)
    public ResponseEntity<ExceptionDetails> duplicateException(DuplicateExceptionHandling dupe){
        ExceptionDetails errorDetails = new ExceptionDetails();
        errorDetails.setFieldname("[ERROR] UserID ");
        errorDetails.setFieldValue(dupe.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> notFoundException(NotFoundException nfe){
        ExceptionDetails errorDetails = new ExceptionDetails();
        errorDetails.setFieldname("[ERROR] UserID ");
        errorDetails.setFieldValue(nfe.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }
}
