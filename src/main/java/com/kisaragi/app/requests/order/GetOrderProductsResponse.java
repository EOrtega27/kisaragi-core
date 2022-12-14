package com.kisaragi.app.requests.order;

public class GetOrderProductsResponse {
    private int product_id;
    private String product_name;
    private int quantity;

    public GetOrderProductsResponse(int product_id, String product_name, int quantity) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.quantity = quantity;
    }

    public GetOrderProductsResponse() {
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
