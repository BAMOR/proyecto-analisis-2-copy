package com.paulo.backend.cartapp.backend_cartapp.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import com.paulo.backend.cartapp.backend_cartapp.models.entities.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{

    List<ShoppingCart> findByUserId(String userId);
    @Modifying
    @Transactional
    @Query("DELETE FROM ShoppingCart s WHERE s.userId = ?1")
    void deleteByUserId(String userId);
    

}
