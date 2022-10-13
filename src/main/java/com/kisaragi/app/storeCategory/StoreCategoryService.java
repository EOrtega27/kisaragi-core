package com.kisaragi.app.storeCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreCategoryService {
	@Autowired
	StoreCategoryRepository storeCatRepository;
	
	public StoreCategoryModel findCategory(int id) {
		return storeCatRepository.findById(id);
	}
	
	public List<StoreCategoryModel> findAll(){
		return storeCatRepository.findAllByOrderById();
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
