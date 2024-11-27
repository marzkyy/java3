package com.ecommerce;

public class Product {
    private String productID;
    private String name;
    private double price;

    // Constructor
    public Product(String productID, String name, double price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString method
    @Override
    public String toString() {
        return "Product{" +
                "ID='" + productID + '\'' +
                ", Name='" + name + '\'' +
                ", Price=" + price +
                '}';
    }
}
