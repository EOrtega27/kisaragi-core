package com.kisaragi.app.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class CreateStores implements CommandLineRunner {

    @Autowired
    StoreService storeService;
    @Autowired
    StoreRepository storeRepository;

    @Override
    public void run(String... args) throws Exception {
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
    }
}
