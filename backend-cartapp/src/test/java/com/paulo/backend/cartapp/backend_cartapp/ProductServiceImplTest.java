package com.paulo.backend.cartapp.backend_cartapp;

import com.paulo.backend.cartapp.backend_cartapp.models.entities.Product;
import com.paulo.backend.cartapp.backend_cartapp.repositories.ProductRepository;
import com.paulo.backend.cartapp.backend_cartapp.services.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Teclado Mecánico");
        product.setPrice(1000L);
        product.setDescription("Teclado con luces RGB");
    }

    @Test
    void findAll_ReturnsAllProducts() {
        when(repository.findAll()).thenReturn(Arrays.asList(product));

        var result = service.findAll();

        assertEquals(1, result.size());
        assertEquals("Teclado Mecánico", result.get(0).getName());
        verify(repository).findAll();
    }

    @Test
    void save_SavesAndReturnsProduct() {
        when(repository.save(any(Product.class))).thenReturn(product);

        Product result = service.save(product);

        assertNotNull(result);
        assertEquals("Teclado Mecánico", result.getName());
        verify(repository).save(product);
    }

    @Test
    void update_ExistingProduct_UpdatesSuccessfully() {
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(repository.save(any(Product.class))).thenReturn(product);

        Product updatedData = new Product();
        updatedData.setName("Teclado Actualizado");
        updatedData.setPrice(1200L);
        updatedData.setDescription("Actualizado");

        Product result = service.update(1L, updatedData);

        assertEquals("Teclado Actualizado", result.getName());
        assertEquals(1200L, result.getPrice());
        verify(repository).findById(1L);
        verify(repository).save(any(Product.class));
    }

    @Test
    void update_NonExistentProduct_ThrowsException() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.update(999L, product);
        });

        assertEquals("Producto no encontrado con id: 999", exception.getMessage());
        verify(repository).findById(999L);
        verify(repository, never()).save(any());
    }

    @Test
    void deleteById_DeletesProduct() {
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }
}