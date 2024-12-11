package com.example.sbj;

import com.example.sbj.product.ProductRepository;
import com.example.sbj.product.model.Product;
import com.example.sbj.product.model.ProductDTO;
import com.example.sbj.product.services.SearchProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class SearchProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SearchProductService searchProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_search_product_service_return_product_dto() {
        List<Product> Products = new ArrayList<Product>();
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Product Description which is at least 20 chars");
        product.setPrice(9.99);
        Products.add(product);

        when(productRepository.findByNameOrDescriptionContaining("Product Name")).thenReturn(Products);
        ResponseEntity<List<ProductDTO>> response = searchProductService.execute("Product Name");
    }
}
