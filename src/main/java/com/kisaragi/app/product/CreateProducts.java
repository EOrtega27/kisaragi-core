package com.kisaragi.app.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class CreateProducts implements CommandLineRunner {
    @Autowired
    ProductService productService;
    @Override
    public void run(String... args) throws Exception {
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
    }
}
