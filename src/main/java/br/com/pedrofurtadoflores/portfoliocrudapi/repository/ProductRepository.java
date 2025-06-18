package br.com.pedrofurtadoflores.portfoliocrudapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pedrofurtadoflores.portfoliocrudapi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
