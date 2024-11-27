package com.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerID;
    private String name;
    private List<Product> shoppingCart;

    // Constructor
    public Customer(String customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.shoppingCart = new ArrayList<>();
    }

    // Getters
    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    // Methods to manage shopping cart
    public void addToCart(Product product) {
        shoppingCart.add(product);
        System.out.println(product.getName() + " added to cart.");
    }

    public void removeFromCart(Product product) {
        shoppingCart.remove(product);
        System.out.println(product.getName() + " removed from cart.");
    }

    // Calculate total cost of shopping cart
    public double calculateTotalCost() {
        return shoppingCart.stream().mapToDouble(Product::getPrice).sum();
    }
}
