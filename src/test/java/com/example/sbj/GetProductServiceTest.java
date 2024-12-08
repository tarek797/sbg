package com.example.sbj;

import com.example.sbj.exceptions.ProductNotFoundException;
import com.example.sbj.product.ProductRepository;
import com.example.sbj.product.model.Product;
import com.example.sbj.product.model.ProductDTO;
import com.example.sbj.product.services.GetProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class GetProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductService getProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_get_product_service_return_product_dto() {

        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Product Description which is at least 20 chars");
        product.setPrice(9.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        ResponseEntity<ProductDTO> response = getProductService.execute(1);

        assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void given_product_not_exists_when_get_product_service_throw_product_not_found_exception() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> getProductService.execute(1));
        verify(productRepository, times(1)).findById(1);
    }


}
