package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Product findByProductNameIgnoreCase(String productName);
	
	public Product findByCoorxAndCoory(int coorx,int coory);
}
