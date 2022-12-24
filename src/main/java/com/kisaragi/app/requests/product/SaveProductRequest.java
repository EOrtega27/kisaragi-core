package com.kisaragi.app.requests.product;

public class SaveProductRequest {
    private String name;
    private String description;
    private float unitPrice;
    private int stock;
    private int categoryId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
