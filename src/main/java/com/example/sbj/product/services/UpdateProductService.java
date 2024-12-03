package com.example.sbj.product.services;

import com.example.sbj.Command;
import com.example.sbj.exceptions.ProductNotFoundException;
import com.example.sbj.product.ProductRepository;
import com.example.sbj.product.model.Product;
import com.example.sbj.product.model.ProductDTO;
import com.example.sbj.product.model.UpdateProductCommand;
import com.example.sbj.validators.ProductValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {

    private final ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand updateProductCommand) {
        Optional<Product> productOptional = productRepository.findById(updateProductCommand.getId());

        if (productOptional.isPresent()) {
            Product product = updateProductCommand.getProduct();
            ProductValidator.execute(product);
            product.setId(productOptional.get().getId());
            productRepository.save(product);
            return ResponseEntity.ok(new ProductDTO(product));
        }

        throw new ProductNotFoundException();
    }
}
