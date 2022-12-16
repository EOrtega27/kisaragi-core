package com.kisaragi.app.storeCategory;

import com.kisaragi.app.store.StoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kisaragi.app.dto.IdRequest;
import com.kisaragi.app.dto.StoreCategoryDTO;
import com.kisaragi.app.dto.UpdateStoreCategoryRequest;

import java.util.Set;


@RestController
@RequestMapping("/store_categories")
public class StoreCategoryController {
	@Autowired
	StoreCategoryService storeCatService;
	
	@GetMapping(" ")
	public ResponseEntity<Object> getAllStoreCategories(){
		return new ResponseEntity<>(storeCatService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/id")
	public ResponseEntity<Object> getStoreCategory(@RequestBody IdRequest id){
		StoreCategoryModel stCat = storeCatService.findCategory(id.getId());
		return new ResponseEntity<>(stCat, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveStoreCategory(@RequestBody StoreCategoryDTO stCatData){
		StoreCategoryModel stCatTest = new StoreCategoryModel();
		stCatTest.setName(stCatData.getName());
		return new ResponseEntity<>(storeCatService.saveCategory(stCatTest), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> updateStoreCategory(@RequestBody UpdateStoreCategoryRequest stCatData){
		StoreCategoryModel stCatTest = new StoreCategoryModel(stCatData.getId(),stCatData.getNombre());
		return new ResponseEntity<>(storeCatService.saveCategory(stCatTest), HttpStatus.OK);
	}

	@PostMapping("/find_store")
	public ResponseEntity<Object> getStoreByStoreCategoryId(@RequestBody IdRequest id){
		Set<StoreModel> stores = storeCatService.getStoresById(id.getId());
		return new ResponseEntity<>(stores, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteStoreCategory(@RequestBody IdRequest id){
		if(storeCatService.deleteCategory(id.getId())) {
			return new ResponseEntity<>("Categoría borrada", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No se encontró la categoria", HttpStatus.BAD_REQUEST);
		}
	}
}
