package com.pharma.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma.model.Cart;

public interface ICartRepository extends JpaRepository<Cart, Integer>{

}
