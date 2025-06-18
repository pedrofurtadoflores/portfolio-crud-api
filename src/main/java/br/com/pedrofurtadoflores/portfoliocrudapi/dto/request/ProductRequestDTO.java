package br.com.pedrofurtadoflores.portfoliocrudapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO de entrada para criação ou atualização de produto")
public class ProductRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    @Schema(description = "Nome do produto", example = "Smartphone Galaxy S21")
    private String name;

    @NotBlank(message = "A categoria é obrigatória")
    @Schema(description = "Categoria do produto", example = "Eletrônicos")
    private String category;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    @Schema(description = "Preço do produto", example = "2499.90")
    private BigDecimal price;

    @NotNull(message = "O estoque é obrigatório")
    @Min(value = 0, message = "O estoque não pode ser negativo")
    @Schema(description = "Quantidade disponível em estoque", example = "15")
    private Integer stock;
}
