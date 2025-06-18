package br.com.pedrofurtadoflores.portfoliocrudapi.exception;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ApiResponse(
            responseCode = "404",
            description = "Recurso não encontrado",
            content = @Content(schema = @Schema(implementation = ApiException.class))
    )
    public ResponseEntity<ApiException> handleEntityNotFound(EntityNotFoundException ex) {
        ApiException error = new ApiException(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ApiResponse(
            responseCode = "400",
            description = "Erro de validação nos dados enviados",
            content = @Content(schema = @Schema(implementation = ApiException.class))
    )
    public ResponseEntity<ApiException> handleValidation(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(" | "));

        ApiException error = new ApiException(
                HttpStatus.BAD_REQUEST.value(),
                errors,
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno não esperado",
            content = @Content(schema = @Schema(implementation = ApiException.class))
    )
    public ResponseEntity<ApiException> handleGeneral(Exception ex) {
        ApiException error = new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
