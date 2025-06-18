package br.com.pedrofurtadoflores.portfoliocrudapi.service;

import java.util.List;

import br.com.pedrofurtadoflores.portfoliocrudapi.dto.request.ProductRequestDTO;
import br.com.pedrofurtadoflores.portfoliocrudapi.dto.response.ProductResponseDTO;

public interface ProductService {

    List<ProductResponseDTO> getAll();

    ProductResponseDTO getById(Long id);

    ProductResponseDTO create(ProductRequestDTO dto);

    ProductResponseDTO update(Long id, ProductRequestDTO dto);

    void delete(Long id);
}
