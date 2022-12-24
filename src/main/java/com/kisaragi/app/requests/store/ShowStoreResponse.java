package com.kisaragi.app.requests.store;

import com.kisaragi.app.product.ProductModel;
import com.kisaragi.app.store.StoreModel;

import java.util.List;

public class ShowStoreResponse {
    StoreModel store_data;
    List<ProductModel> store_products;

    public StoreModel getStore_data() {
        return store_data;
    }

    public void setStore_data(StoreModel store_data) {
        this.store_data = store_data;
    }

    public List<ProductModel> getStore_products() {
        return store_products;
    }

    public void setStore_products(List<ProductModel> store_products) {
        this.store_products = store_products;
    }
}
