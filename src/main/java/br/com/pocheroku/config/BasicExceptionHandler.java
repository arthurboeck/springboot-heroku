package br.com.pocheroku.config;

import br.com.pocheroku.dto.ErrorApiDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class, ValidationException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorApiDTO> exceptionHandler(Exception exception) {
        ErrorApiDTO error = ErrorApiDTO.builder()
                .error(exception.toString())
                .timestamp(LocalDateTime.now().toString())
                .build();

        return ResponseEntity.badRequest().body(error);
    }
}
