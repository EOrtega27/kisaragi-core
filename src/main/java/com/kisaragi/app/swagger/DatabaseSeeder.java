package com.kisaragi.app.swagger;

import com.kisaragi.app.order.OrderService;
import com.kisaragi.app.product.ProductService;
import com.kisaragi.app.productCategory.ProductCategoryService;
import com.kisaragi.app.store.StoreModel;
import com.kisaragi.app.store.StoreRepository;
import com.kisaragi.app.store.StoreService;
import com.kisaragi.app.storeCategory.StoreCategoryModel;
import com.kisaragi.app.storeCategory.StoreCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    @Autowired
    StoreCategoryRepository storeCategoryRepository;
    @Autowired
    StoreService storeService;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @Override
    public void run(String... args) throws Exception {

        //STORE CATEGORIES
        List<StoreCategoryModel> categories = new ArrayList<>();
        categories.add(new StoreCategoryModel("Bodega"));
        categories.add(new StoreCategoryModel("Minimarket"));
        categories.add(new StoreCategoryModel("Panadería"));
        storeCategoryRepository.saveAll(categories);

        //STORES
        StoreModel newStore1 = new StoreModel(
                "usertest123456",
                "Panaderia Estación",
                "Av. Argentina 123",
                123.34f,
                123.34f,
                "984233112",
                "panadería@estacion.com"
        );
        storeRepository.save(newStore1);

        storeService.addCategory(
                1,
                3
        );

        StoreModel newStore2 = new StoreModel(
                "usertest987654",
                "Bodega Pepito",
                "Av. Av. La Molina 123",
                123.34f,
                123.34f,
                "984233112",
                "bodega@bepito.com"
        );
        storeRepository.save(newStore2);

        storeService.addCategory(
                2,
                1
        );

        //PRODUCT CATEGORIES
        productCategoryService.createCategory(1,"Panes");
        productCategoryService.createCategory(1,"Lacteos");
        productCategoryService.createCategory(2,"Gaseosas");
        productCategoryService.createCategory(2,"Snacks");

        //PRODUCTS
        productService.createProduct(
                "Pan Frances",
                "Pan de forma alargada",
                3.5f,
                25,
                1
        );
        productService.createProduct(
                "Pan de Molde",
                "Pan de corteza blanda",
                3.5f,
                25,
                1
        );
        productService.createProduct(
                "Leche Gloria",
                "Lata de leche marca Gloria regular",
                3.5f,
                25,
                2
        );
        productService.createProduct(
                "Inca Kola 3L",
                "Botella de 3 litros de Inca Kola",
                3.5f,
                25,
                3
        );

        //ORDERS
        orderService.createOrder(
                1,
                "trackingTest123",
                "testUser123",
                "Av. Argentina"
        );

        orderService.createOrder(
                1,
                "trackingTest456",
                "testUser123",
                "Av. Argentina"
        );

        orderService.createOrder(
                2,
                "trackingTest789",
                "testUser123",
                "Av. Argentina"
        );

        orderService.addOrderProduct("trackingTest123",1,5);
        orderService.addOrderProduct("trackingTest123",2,10);
        orderService.addOrderProduct("trackingTest789",4,2);
    }
}
