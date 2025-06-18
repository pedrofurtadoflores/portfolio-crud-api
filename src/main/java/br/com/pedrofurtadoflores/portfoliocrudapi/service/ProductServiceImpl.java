package br.com.pedrofurtadoflores.portfoliocrudapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pedrofurtadoflores.portfoliocrudapi.dto.request.ProductRequestDTO;
import br.com.pedrofurtadoflores.portfoliocrudapi.dto.response.ProductResponseDTO;
import br.com.pedrofurtadoflores.portfoliocrudapi.model.Product;
import br.com.pedrofurtadoflores.portfoliocrudapi.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        return toResponseDTO(product);
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO dto) {
        Product saved = repository.save(toEntity(dto));
        return toResponseDTO(saved);
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        existing.setName(dto.getName());
        existing.setCategory(dto.getCategory());
        existing.setPrice(dto.getPrice());
        existing.setStock(dto.getStock());

        return toResponseDTO(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Produto não encontrado");
        }
        repository.deleteById(id);
    }

    // ==== Conversões ====

    private ProductResponseDTO toResponseDTO(Product entity) {
        return ProductResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private Product toEntity(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
    }
}
