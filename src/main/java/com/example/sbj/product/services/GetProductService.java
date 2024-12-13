package com.example.sbj.product.services;

import com.example.sbj.Query;
import com.example.sbj.product.ProductRepository;
import com.example.sbj.product.model.Product;
import com.example.sbj.product.model.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductService implements Query<Integer, ProductDTO> {

    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(GetProductsService.class);

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable("productCache")
    public ResponseEntity<ProductDTO> execute(Integer input) {
        logger.info("Excuting " + getClass() + " input " + input);
        Optional<Product> productOptional = productRepository.findById(input);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(new ProductDTO(productOptional.get()));
        }
        return null;
    }
}
