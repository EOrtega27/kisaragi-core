package com.kisaragi.app.store;

import java.io.IOException;
import java.util.List;

import com.kisaragi.app.requests.store.AddStoreCategoriesRequest;
import com.kisaragi.app.requests.store.AddStoreCategoryRequest;
import com.kisaragi.app.requests.store.GetByAdminIdRequest;
import com.kisaragi.app.requests.store.ShowStoreResponse;
import com.kisaragi.app.product.ProductModel;
import com.kisaragi.app.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kisaragi.app.requests.IdRequest;
import com.kisaragi.app.requests.StoreDTO;
import com.kisaragi.app.requests.UpdateStoreRequest;
import com.kisaragi.app.storeCategory.StoreCategoryService;

@RestController
@RequestMapping("/stores")
public class StoreController {
	@Autowired
	StoreService storeService;
	@Autowired
	StoreCategoryService stCategoryService;
	@Autowired
	ProductService productService;

	@GetMapping(" ")
	public ResponseEntity<Object> getAllStores(){
		return new ResponseEntity<Object>(storeService.getAllStores(),HttpStatus.OK);
	}


	@PostMapping("/id")
	public ResponseEntity<Object> getStore(@RequestBody IdRequest id){
		if(storeService.existStore(id.getId())) {
			StoreModel store = storeService.findStore(id.getId());
			List<ProductModel> products = productService.getAllProductByStore(id.getId());
			ShowStoreResponse response = new ShowStoreResponse();
			response.setStore_data(store);
			response.setStore_products(products);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>("No se encontró la tienda", HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@PostMapping("/admin_id")
	public ResponseEntity<Object> getStoreByAdminId(@RequestBody GetByAdminIdRequest id){
		return new ResponseEntity<Object>(storeService.findStoreByAdminId(id.getAdminId()), HttpStatus.OK);
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
	
	@PostMapping("/add_category")
	@ResponseBody
	public ResponseEntity<Object> addCategory(@RequestBody AddStoreCategoryRequest request){
		return new ResponseEntity<Object>(storeService.addCategory(request.getStoreId(), request.getStoreCategoryId()), HttpStatus.OK);
	}

	@PostMapping("/set_categories")
	@ResponseBody
	public ResponseEntity<Object> setCategories(@RequestBody AddStoreCategoriesRequest request){
		return new ResponseEntity<Object>(storeService.addCategories(request.getStoreId(), request.getStoreCategoriesIds()), HttpStatus.OK);
	}
}
