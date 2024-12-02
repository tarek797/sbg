package com.example.sbj.product;

import com.example.sbj.product.services.CreateProductService;
import com.example.sbj.product.services.DeleteProductService;
import com.example.sbj.product.services.GetProductsService;
import com.example.sbj.product.services.UpdateProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    final GetProductsService getProductsService;
    final CreateProductService createProductService;
    final UpdateProductService updateProductService;
    final DeleteProductService deleteProductService;

    public ProductController(GetProductsService getProductsService,
                             CreateProductService createProductService,
                             UpdateProductService updateProductService,
                             DeleteProductService deleteProductService) {

        this.getProductsService = getProductsService;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
    }


    @PostMapping
    public ResponseEntity<String> createProduct() {
        return createProductService.execute(null
        );
    }

    @GetMapping
    public ResponseEntity<String> getProducts() {
        return getProductsService.execute(null);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct() {
        return updateProductService.execute(null);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct() {
        return deleteProductService.execute(null);
    }
}
