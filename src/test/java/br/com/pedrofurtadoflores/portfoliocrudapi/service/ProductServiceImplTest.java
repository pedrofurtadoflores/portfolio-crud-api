package br.com.pedrofurtadoflores.portfoliocrudapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.pedrofurtadoflores.portfoliocrudapi.dto.request.ProductRequestDTO;
import br.com.pedrofurtadoflores.portfoliocrudapi.dto.response.ProductResponseDTO;
import br.com.pedrofurtadoflores.portfoliocrudapi.model.Product;
import br.com.pedrofurtadoflores.portfoliocrudapi.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private ProductRepository repository;
    private ProductServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(ProductRepository.class);
        service = new ProductServiceImpl(repository);
    }

    @Test
    void testGetAll() {
        List<Product> products = Arrays.asList(
            Product.builder()
                .name("A")
                .category("Cat")
                .price(BigDecimal.TEN)
                .stock(1)
                .build(),
            Product.builder()
                .name("B")
                .category("Cat")
                .price(BigDecimal.ONE)
                .stock(2)
                .build()
        );
        when(repository.findAll()).thenReturn(products);

        List<ProductResponseDTO> result = service.getAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetById_Success() {
        Product product = Product.builder()
                .name("A")
                .category("Cat")
                .price(BigDecimal.TEN)
                .stock(1)
                .build();
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        ProductResponseDTO result = service.getById(1L);

        assertEquals("A", result.getName());
        verify(repository).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getById(999L));
    }

    @Test
    void testCreate() {
        ProductRequestDTO dto = new ProductRequestDTO("A", "Cat", BigDecimal.TEN, 1);
        Product saved = Product.builder()
                .name("A")
                .category("Cat")
                .price(BigDecimal.TEN)
                .stock(1)
                .build();

        when(repository.save(any(Product.class))).thenReturn(saved);

        ProductResponseDTO result = service.create(dto);

        assertEquals("A", result.getName());
        verify(repository).save(any(Product.class));
    }

    @Test
    void testUpdate_Success() {
        Product existing = Product.builder()
                .name("A")
                .category("Cat")
                .price(BigDecimal.TEN)
                .stock(1)
                .build();
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProductRequestDTO dto = new ProductRequestDTO("New", "NewCat", BigDecimal.TEN, 5);
        ProductResponseDTO result = service.update(1L, dto);

        assertEquals("New", result.getName());
        assertEquals(BigDecimal.TEN, result.getPrice());
        verify(repository).save(existing);
    }

    @Test
    void testUpdate_NotFound() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        ProductRequestDTO dto = new ProductRequestDTO("New", "Cat", BigDecimal.TEN, 1);
        assertThrows(EntityNotFoundException.class, () -> service.update(999L, dto));
    }

    @Test
    void testDelete_Success() {
        when(repository.existsById(1L)).thenReturn(true);
        service.delete(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(repository.existsById(999L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> service.delete(999L));
    }
}
