package com.kisaragi.app.storeCategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreCategoryRepository extends JpaRepository<StoreCategoryModel, Integer>{
	public StoreCategoryModel findById(int id);

}
