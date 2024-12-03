package com.example.sbj.product;

import com.example.sbj.product.model.Product;
import com.example.sbj.product.model.ProductDTO;
import com.example.sbj.product.model.UpdateProductCommand;
import com.example.sbj.product.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    final GetProductsService getProductsService;
    final CreateProductService createProductService;
    final UpdateProductService updateProductService;
    final DeleteProductService deleteProductService;
    final GetProductService getProductService;

    public ProductController(GetProductsService getProductsService,
                             CreateProductService createProductService,
                             UpdateProductService updateProductService,
                             DeleteProductService deleteProductService,
                             GetProductService getProductService) {

        this.getProductsService = getProductsService;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
        this.getProductService = getProductService;
    }


    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return createProductService.execute(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getProductsService.execute(null);
    }

    @GetMapping
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
        return getProductService.execute(id);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return updateProductService.execute(new UpdateProductCommand(id, product));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct() {
        return deleteProductService.execute(null);
    }
}
