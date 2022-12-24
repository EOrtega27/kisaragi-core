package com.kisaragi.app.requests.order;

public class ProductsInOrderDTO {
    private int idProduct;
    private int quantity;

    public int getIdProduct() {
        return idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public ProductsInOrderDTO(int idProduct, int quantity) {
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public ProductsInOrderDTO() {
    }
}
