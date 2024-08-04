package com.example.raqs_apppuntodeventa;

public class SaleDetail {
    private int productId;
    private String productName;
    private int quantity;
    private double price;
    private double amount;

    public SaleDetail(int productId, String productName, int quantity, double price, double amount) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getAmount() {
        return amount;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

