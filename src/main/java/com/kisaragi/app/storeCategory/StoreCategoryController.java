package com.kisaragi.app.storeCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kisaragi.app.dto.IdDTO;
import com.kisaragi.app.dto.StoreCategoryDTO;
import com.kisaragi.app.dto.UpdateStoreCategoryRequest;


@RestController
@RequestMapping("/store_categories")
public class StoreCategoryController {
	@Autowired
	StoreCategoryService storeCatService;
	
	@GetMapping(" ")
	public ResponseEntity<Object> getAllStoreCategories(){
		return new ResponseEntity<Object>(storeCatService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Object> getStoreCategory(@RequestBody IdDTO id){
		StoreCategoryModel stCat = storeCatService.findCategory(id.getId());
		return new ResponseEntity<Object>(stCat, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveStoreCategory(@RequestBody StoreCategoryDTO stCatData){
		StoreCategoryModel stCatTest = new StoreCategoryModel();
		stCatTest.setName(stCatData.getName());
		return new ResponseEntity<Object>(storeCatService.saveCategory(stCatTest), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> updateStoreCategory(@RequestBody UpdateStoreCategoryRequest stCatData){
		StoreCategoryModel stCatTest = new StoreCategoryModel(stCatData.getId(),stCatData.getNombre());
		return new ResponseEntity<Object>(storeCatService.saveCategory(stCatTest), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteStoreCategory(@RequestBody IdDTO id){
		if(storeCatService.deleteCategory(id.getId())) {
			return new ResponseEntity<Object>("Categoría borrada", HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>("No se encontró la categoria", HttpStatus.BAD_REQUEST);
		}
	}
}
