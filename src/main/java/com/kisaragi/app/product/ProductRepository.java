package com.kisaragi.app.product;

import java.util.List;

import com.kisaragi.app.productCategory.ProductCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Integer>{
	public ProductModel findById(int id);
	
	public List<ProductModel> findAllByOrderByIdAsc();

	public List<ProductModel> findAllByProductCategory(ProductCategoryModel productCategory);
}
