package br.com.fiap.Projeto_Mercado_Presentation.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {

    // Record que representa o erro de validação
    record ValidationErrorMessage(String field, String message) {
        public ValidationErrorMessage(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    // Captura erros de validação e retorna lista dos campos com mensagem
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationErrorMessage> handler(MethodArgumentNotValidException exception) {
        return exception.getFieldErrors()
                .stream()
                .map(ValidationErrorMessage::new)
                .toList();
    }
}
