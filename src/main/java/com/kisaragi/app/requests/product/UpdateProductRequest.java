package com.kisaragi.app.requests.product;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
