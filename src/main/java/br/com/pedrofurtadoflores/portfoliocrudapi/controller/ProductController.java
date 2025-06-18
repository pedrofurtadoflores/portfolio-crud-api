package br.com.pedrofurtadoflores.portfoliocrudapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.pedrofurtadoflores.portfoliocrudapi.dto.request.ProductRequestDTO;
import br.com.pedrofurtadoflores.portfoliocrudapi.dto.response.ProductResponseDTO;
import br.com.pedrofurtadoflores.portfoliocrudapi.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista com todos os produtos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Buscar produto por ID", description = "Retorna os dados de um produto específico.")
    @ApiResponse(responseCode = "200", description = "Produto encontrado")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(
            @Parameter(description = "ID do produto") @PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto com os dados fornecidos.")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Atualizar um produto existente", description = "Atualiza os dados de um produto pelo ID.")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @Parameter(description = "ID do produto") @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Deletar um produto", description = "Remove um produto do banco de dados.")
    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do produto") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
