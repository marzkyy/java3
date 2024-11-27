package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderID;
    private Customer customer;
    private List<Product> products;
    private double orderTotal;
    private String status = "Pending";

    // Constructor
    public Order(String orderID, Customer customer) {
        this.orderID = orderID;
        this.customer = customer;
        this.products = new ArrayList<>(customer.getShoppingCart());
        this.orderTotal = customer.calculateTotalCost();
    }

    // Getters
    public String getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public String getStatus() {
        return status;
    }

    // Update order status
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Order status updated to: " + newStatus);
    }

    // Generate order summary
    public String getOrderSummary() {
        StringBuilder summary = new StringBuilder("Order Summary:\n");
        summary.append("Order ID: ").append(orderID).append("\n");
        summary.append("Customer: ").append(customer.getName()).append("\n");
        summary.append("Products:\n");
        for (Product product : products) {
            summary.append(" - ").append(product.getName())
                   .append(" ($").append(product.getPrice()).append(")\n");
        }
        summary.append("Total: $").append(orderTotal).append("\n");
        return summary.toString();
    }
}
