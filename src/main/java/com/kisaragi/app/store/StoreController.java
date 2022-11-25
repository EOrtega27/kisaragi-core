package com.kisaragi.app.store;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kisaragi.app.dto.IdRequest;
import com.kisaragi.app.dto.StoreDTO;
import com.kisaragi.app.dto.UpdateStoreRequest;
import com.kisaragi.app.storeCategory.StoreCategoryModel;
import com.kisaragi.app.storeCategory.StoreCategoryService;

@RestController
@RequestMapping("/stores")
public class StoreController {
	@Autowired
	StoreService storeService;
	@Autowired
	StoreCategoryService stCategoryService;
	
	@GetMapping(" ")
	public ResponseEntity<Object> getAllStores(){
		return new ResponseEntity<Object>(storeService.getAllStores(),HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Object> getStore(@RequestBody IdRequest id){
		if(storeService.existStore(id.getId())) {
			StoreModel store = storeService.findStore(id.getId());
			return new ResponseEntity<Object>(store, HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>("No se encontró la tienda", HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@GetMapping("/admin_id")
	public ResponseEntity<Object> getStoreByAdminId(@RequestBody IdRequest id){
		return new ResponseEntity<Object>(storeService.findStoreByAdminId(id.getId()), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Object> saveStore(@RequestBody StoreDTO storeData){
		StoreModel store = new StoreModel(storeData);
		return new ResponseEntity<Object>(storeService.saveStore(store), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> updateStore(@RequestBody UpdateStoreRequest storeData){
		StoreModel store = new StoreModel(storeData.getNewData());
		store.setId(storeData.getId());
		return new ResponseEntity<Object>(storeService.saveStore(store), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteStore(@RequestBody IdRequest id){
		if(storeService.deleteStore(id.getId())) {
			return new ResponseEntity<Object>("Tienda borrada", HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>("No se encontró la tienda", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/{storeId}/add_image")
	public ResponseEntity<Object> addImage(@RequestParam MultipartFile file, @PathVariable int storeId) throws IOException{
		return new ResponseEntity<Object>(storeService.saveStoreImage(file, storeId), HttpStatus.OK);
	}
	
	@PostMapping("/{storeId}/add_category")
	@ResponseBody
	public ResponseEntity<Object> addCategory(@RequestBody IdRequest stCatId, @PathVariable int storeId){
		StoreCategoryModel stCat = stCategoryService.findCategory(stCatId.getId());
		return new ResponseEntity<Object>(storeService.addCategory(storeId, stCat), HttpStatus.OK);
	}
}
