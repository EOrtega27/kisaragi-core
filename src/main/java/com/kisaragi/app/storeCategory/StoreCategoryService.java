package com.kisaragi.app.storeCategory;

import java.util.List;
import java.util.Set;

import com.kisaragi.app.store.StoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreCategoryService {
	@Autowired
	StoreCategoryRepository storeCatRepository;
	
	public StoreCategoryModel findCategory(int id_cat) {
		if(existCategory(id_cat)) {
			return storeCatRepository.findById(id_cat);
		}else {
			return null;
		}

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
			storeCatRepository.deleteById(id_cat);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean existCategory(int id) {
		return storeCatRepository.existsById(id);
	}

	public Set<StoreModel> getStoresById(int cat_id){
		return findCategory(cat_id).getStores();
	}
}
