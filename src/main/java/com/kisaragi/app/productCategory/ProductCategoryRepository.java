package com.kisaragi.app.productCategory;

import com.kisaragi.app.store.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryModel, Integer>{
	public ProductCategoryModel findById(int id);

	public List<ProductCategoryModel> findAllByOrderByIdAsc();
	public List<ProductCategoryModel> findAllByStoreProductCategory(StoreModel storeProductCategory);
}
