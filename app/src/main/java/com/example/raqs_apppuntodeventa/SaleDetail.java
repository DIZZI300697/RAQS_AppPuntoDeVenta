package com.example.raqs_apppuntodeventa;

public class SaleDetail {
    private int productId;
    private int quantity;
    private double price;
    private double amount;

    public SaleDetail(int productId, int quantity, double price, double amount) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
    }

    public int getProductId() {
        return productId;
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
}
