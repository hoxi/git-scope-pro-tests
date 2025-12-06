package net.tagpad.packageC;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String userId;
    private List<Product> products;
    private LocalDateTime orderDate;
    private OrderStatus status;

    public enum OrderStatus {
        PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    }

    public Order(String orderId, String userId) {
        this.orderId = orderId;
        this.userId = userId;
        this.products = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double calculateTotal() {
        return products.stream()
                .mapToDouble(Product::getTotalValue)
                .sum();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{orderId='" + orderId + "', userId='" + userId +
               "', productCount=" + products.size() + ", total=" + calculateTotal() +
               ", status=" + status + "}";
    }
}
