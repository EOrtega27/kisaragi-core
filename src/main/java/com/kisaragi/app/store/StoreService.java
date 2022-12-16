package com.kisaragi.app.store;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.kisaragi.app.storeCategory.StoreCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kisaragi.app.cloudinary.cloudinaryService;
import com.kisaragi.app.storeCategory.StoreCategoryModel;

@Service
public class StoreService {
	@Autowired
	StoreRepository storeRepo;
	@Autowired
	StoreCategoryRepository storeCategoryRepository;
	@Autowired
	cloudinaryService cloudinary;
	
	public StoreModel findStore(int id) {
		return storeRepo.findById(id);
	}

	public List<StoreModel> findStoreByAdminId(String id){
		return storeRepo.findAllByAdminId(id);
	}
	
	public List<StoreModel> getAllStores(){
		return storeRepo.findAllByOrderByIdAsc();
	}
	
	public StoreModel saveStore(StoreModel store) {
		return storeRepo.save(store);
	}
	
	public StoreModel updateStore(StoreModel store) {
		return storeRepo.save(store);
	}
	
	public boolean deleteStore(int id_store) {
		if(existStore(id_store)) {
			StoreModel store = findStore(id_store);
			storeRepo.delete(store);
			return true;
		}else {
			return false;
		}
	}
	
	public StoreModel saveStoreImage(MultipartFile multipartFile, int id) throws IOException {
		Map result = cloudinary.upload(multipartFile);
		StoreModel store = findStore(id);
		store.setImage(result.get("url").toString());
		return saveStore(store);
	}
	
	public boolean existStore(int id) {
		return storeRepo.existsById(id);
	}
	
	public StoreModel addCategory(int storeId, int storeCategoryId) {
		StoreModel store = findStore(storeId);
		StoreCategoryModel storeCategoryModel = storeCategoryRepository.findById(storeCategoryId);
		store.getStoreCategories().add(storeCategoryModel);
		return storeRepo.save(store);
	}
}
