package com.kisaragi.app.productCategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryModel, Integer>{
	public ProductCategoryModel findById(int id);
}
