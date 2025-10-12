package com.paulo.backend.cartapp.backend_cartapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.Arrays;
import java.util.Optional;



import com.paulo.backend.cartapp.backend_cartapp.models.entities.Product;
import com.paulo.backend.cartapp.backend_cartapp.repositories.ProductRepository;
import com.paulo.backend.cartapp.backend_cartapp.services.ProductServiceImpl;

public class ProductServiceImplTest {
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    @Test
    public void testFindAll() {
        Product p = new Product();
        when(repository.findAll()).thenReturn(Arrays.asList(p));

        var result = service.findAll();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        service.deleteById(id);
        verify(repository).deleteById(id);
    }

    @Test
    public void testUpdate_ProductFound() {
        Long id = 1L;
        Product existing = new Product();
        existing.setId(id);
        existing.setName("Viejo");

        Product updatedData = new Product();
        updatedData.setName("Nuevo");
        updatedData.setPrice(100L);
        updatedData.setDescription("Desc");

        when(repository.findById(id)).thenReturn(Optional.of(existing));
        when(repository.save(any(Product.class))).thenReturn(updatedData);

        Product result = service.update(id, updatedData);

        assertEquals("Nuevo", result.getName());
        verify(repository).findById(id);
        verify(repository).save(any(Product.class));
    }
    
}
