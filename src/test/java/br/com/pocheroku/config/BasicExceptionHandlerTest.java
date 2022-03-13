package br.com.pocheroku.config;

import br.com.pocheroku.dto.ErrorApiDTO;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class BasicExceptionHandlerTest {

    @Spy
    BasicExceptionHandler basicExceptionHandler;

    private static final String CONSTRAINT_VIOLATION_MSG = "javax.validation.ConstraintViolationException: textToChatBot.message: Message can't be null or empty";

    @Test
    void mustReturnErrorWhenSimplePropertyPath() {
        // given
        ConstraintViolationException ex = getConstraintViolationException("message");

        // when
        ErrorApiDTO errorDto = basicExceptionHandler.exceptionHandler(ex).getBody();

        // then
        assertNotNull(errorDto);
        assertEquals(CONSTRAINT_VIOLATION_MSG, errorDto.getError());
        assertEquals(LocalDateTime.now().toString().substring(0, 20), errorDto.getTimestamp().substring(0, 20));
    }

    @Test
    void mustReturnErrorWhenComposePropertyPath() {
        // given
        ConstraintViolationException ex = getConstraintViolationException("object.message");

        // when
        ErrorApiDTO errorDto = basicExceptionHandler.exceptionHandler(ex).getBody();

        // then
        assertNotNull(errorDto);
        assertEquals(CONSTRAINT_VIOLATION_MSG, errorDto.getError());
        assertEquals(LocalDateTime.now().toString().substring(0, 20), errorDto.getTimestamp().substring(0, 20));
    }

    private ConstraintViolationException getConstraintViolationException(String value) {
        ConstraintViolation<Object> violation = ConstraintViolationImpl.forParameterValidation(
                null, null, null,
                null,
                null, null, null, null,
                PathImpl.createPathFromString(value),
                null, null, null);

        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        violations.add(violation);

        return new ConstraintViolationException("textToChatBot.message: Message can't be null or empty", violations);
    }
}
