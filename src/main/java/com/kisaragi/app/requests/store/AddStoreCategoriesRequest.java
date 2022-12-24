package com.kisaragi.app.requests.store;

import java.util.List;

public class AddStoreCategoriesRequest {
    private int storeId;
    private List<Integer> storeCategoriesIds;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public List<Integer> getStoreCategoriesIds() {
        return storeCategoriesIds;
    }

    public void setStoreCategoriesIds(List<Integer> storeCategoriesIds) {
        this.storeCategoriesIds = storeCategoriesIds;
    }
}
