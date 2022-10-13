package com.kisaragi.app.storeCategory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreCategoryRepository extends JpaRepository<StoreCategoryModel, Integer>{
	public StoreCategoryModel findById(int id);
	
	public List<StoreCategoryModel> findAllByOrderById();

}
