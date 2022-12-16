package com.kisaragi.app.productCategory;

import com.kisaragi.app.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class CreateProductsCategories implements CommandLineRunner {
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    StoreService storeService;

    @Override
    public void run(String... args) throws Exception {
        productCategoryService.createCategory(1,"Panes");
        productCategoryService.createCategory(1,"Lacteos");
        productCategoryService.createCategory(2,"Gaseosas");
        productCategoryService.createCategory(2,"Snacks");
    }
}
