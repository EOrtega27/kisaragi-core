package com.kisaragi.app.storeCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateStoreCategory implements CommandLineRunner {
    @Autowired
    StoreCategoryRepository storeCategoryRepository;

    @Override
    public void run(String... args) throws Exception {
        List<StoreCategoryModel> categories = new ArrayList<>();
        categories.add(new StoreCategoryModel("Bodega"));
        categories.add(new StoreCategoryModel("Minimarket"));
        categories.add(new StoreCategoryModel("Panadería"));
        storeCategoryRepository.saveAll(categories);
    }
}
