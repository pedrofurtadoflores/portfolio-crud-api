package br.com.pedrofurtadoflores.portfoliocrudapi.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Schema(description = "Objeto de erro retornado pela API em caso de exceções")
public class ApiException {

    @Schema(description = "Código HTTP do erro", example = "404")
    private int status;

    @Schema(description = "Mensagem de erro", example = "Produto não encontrado")
    private String message;

    @Schema(description = "Momento em que o erro ocorreu", example = "2025-06-13T15:42:00")
    private LocalDateTime timestamp;
}
