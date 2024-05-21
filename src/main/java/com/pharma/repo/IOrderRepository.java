package com.pharma.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma.model.CheckOut;


public interface IOrderRepository extends JpaRepository<CheckOut, Integer> {

}
