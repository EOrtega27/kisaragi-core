package com.kisaragi.app.dto.product;

public class UpdateProductRequest {
    private int id;
    private String name;
    private String desciption;
    private float unitPrice;
    private int stock;
    private int categoryId;

    public int getId() {
        return id;
    }

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
