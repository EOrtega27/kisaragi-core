package com.kisaragi.app.productCategory;

import com.kisaragi.app.store.StoreModel;
import com.kisaragi.app.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    @Autowired
    ProductCategoryRepository proCatRepository;

    @Autowired
    StoreService storeService;

    public List<ProductCategoryModel> getAllCategories(){
        return proCatRepository.findAllByOrderByIdAsc();
    }

    public ProductCategoryModel findById(int categoryId) {
        return proCatRepository.findById(categoryId);
    }

    public List<ProductCategoryModel> getAllByStore(int storeId){
        StoreModel store = storeService.findStore(storeId);
        return proCatRepository.findAllByStore_ProductCategory(store);
    }

    public ProductCategoryModel createCategory(int storeId, String categoryName){
        StoreModel store = storeService.findStore(storeId);
        ProductCategoryModel newCategory = new ProductCategoryModel(store, categoryName);
        return proCatRepository.save(newCategory);
    }
}
