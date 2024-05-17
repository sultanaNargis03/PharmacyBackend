package com.pharma.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma.model.Cart;
import com.pharma.model.UserEntity;

public interface ICartRepository extends JpaRepository<Cart, Integer>
{
	boolean existsByItemName(String itemName);
	Cart findByItemName(String itemName);
	List<Cart> findByUser(UserEntity user);
	Cart findByUserAndItemName(UserEntity user,String itemName);
}
