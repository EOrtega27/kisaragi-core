package com.kisaragi.app.requests.product;

public class SaveProductRequest {
    private String name;
    private String desciption;
    private float unitPrice;
    private int stock;
    private int categoryId;

    public String getName() {
        return name;
    }

    public String getDesciption() {
        return desciption;
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
