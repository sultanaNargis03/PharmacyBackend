package com.pharma.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pharma.model.CheckOut;
import com.pharma.model.UserEntity;


public interface ICheckoutRepository extends JpaRepository<CheckOut, Integer> 
{
	Optional<List<CheckOut>> findByUser(UserEntity user);
}
