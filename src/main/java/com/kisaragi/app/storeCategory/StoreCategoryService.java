package com.kisaragi.app.storeCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kisaragi.app.store.StoreModel;

@Service
public class StoreCategoryService {
	@Autowired
	StoreCategoryRepository storeCatRepository;
	
	public StoreCategoryModel findCategory(int id) {
		return storeCatRepository.findById(id);
	}
	
	public StoreCategoryModel saveCategory(StoreCategoryModel stCat) {
		stCat = storeCatRepository.save(stCat);
		return stCat;
	} 
	
	public boolean deleteCategory(int id_cat) {
		if(existCategory(id_cat)) {
			StoreCategoryModel cat = findCategory(id_cat);
			storeCatRepository.delete(cat);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean existCategory(int id) {
		return storeCatRepository.existsById(id);
	}
}
