package br.com.pedrofurtadoflores.portfoliocrudapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO de saída para retorno de dados do produto")
public class ProductResponseDTO {

    @Schema(description = "ID do produto", example = "1")
    private Long id;

    @Schema(description = "Nome do produto", example = "Smartphone Galaxy S21")
    private String name;

    @Schema(description = "Categoria do produto", example = "Eletrônicos")
    private String category;

    @Schema(description = "Preço do produto", example = "2499.90")
    private BigDecimal price;

    @Schema(description = "Quantidade disponível em estoque", example = "15")
    private Integer stock;

    @Schema(description = "Data de criação do produto")
    private LocalDateTime createdAt;

    @Schema(description = "Data de última atualização do produto")
    private LocalDateTime updatedAt;
}
