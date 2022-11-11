package com.kisaragi.app.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Integer>{
	public ProductModel findById(int id);
	
	public List<ProductModel> findAllByOrderByIdAsc();
}
