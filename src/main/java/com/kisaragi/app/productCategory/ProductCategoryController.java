package com.kisaragi.app.productCategory;

import com.kisaragi.app.requests.IdRequest;
import com.kisaragi.app.requests.productCategory.SaveProductCategoryRequest;
import com.kisaragi.app.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product_categories")
public class ProductCategoryController {
    @Autowired
    ProductCategoryService proCatService;
    @Autowired
    StoreService storeService;

    @GetMapping(" ")
    private ResponseEntity<Object> getAllCategories(){
        return new ResponseEntity<>(proCatService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping("/by_store")
    private ResponseEntity<Object> getCategoriesByStore(@RequestBody IdRequest storeId){
        return new ResponseEntity<>(proCatService.getAllByStore(storeId.getId()),HttpStatus.OK);
    }

    @PostMapping("/id")
    private ResponseEntity<Object> getCategoryById(@RequestBody IdRequest catId){
        return new ResponseEntity<>(proCatService.findById(catId.getId()),HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<Object> saveCategory(@RequestBody SaveProductCategoryRequest req){
        if(storeService.existStore(req.getStoreId())){
            ProductCategoryModel newCategory = proCatService.createCategory(req.getStoreId(), req.getProductCategoryName());
            return new ResponseEntity<>(newCategory,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Store Not Found", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    private ResponseEntity<Object> deleteCategory(@RequestBody IdRequest catId){
        boolean del = proCatService.deleteCategory(catId.getId());
        if(del){
            return new ResponseEntity<>("Product Category Deleted",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Product Category Not Found", HttpStatus.BAD_REQUEST);
        }
    }

}
