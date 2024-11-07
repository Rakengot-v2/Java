package com.example.myApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public class Product {
        private int id;
        private String name;
        private double price;
        private String description;
        private Category category;

        // Constructor
        public Product(int id, String name, double price, String description, Category category) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.description = description;
            this.category = category;
        }

        // Getters and Setters
        public Category getCategory() {
            return category;
        }
        public void setCategory(Category category) {
            this.category = category;
        }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public double getPrice() {
            return price;
        }
        public void setPrice(double price) {
            this.price = price;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' + 
                ", category='" + category.getName() + '\'' +
                '}';
        }       
    }

    public class Category {
        private int id; // Unique category ID
        private String name; // Category name

        // Constructor
        public Category(int id, String name) {
            this.id = id;
            this.name = name;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Category{" +
                   "id=" + id +
                   ", name='" + name + '\'' +
                   '}';
        }
    }

    public class Cart {
        private List<Product> products; // List of products in the cart

        // Constructor
        public Cart() {
            this.products = new ArrayList<>();
        }

        // Method to add a product to the cart
        public void addProduct(Product product) {
            products.add(product);
        }

        // Method to remove a product from the cart by ID
        public boolean removeProductById(int id) {
            for (Product product : products) {
                if (product.getId() == id) {
                    products.remove(product);
                    return true; // Product removed successfully
                }
            }
            return false; // Product with given ID not found
        }

        // Method to get the total price of products in the cart
        public double getTotalPrice() {
            double total = 0;
            for (Product product : products) {
                total += product.getPrice();
            }
            return total;
        }

        public List<Product> getProducts() {
            return new ArrayList<>(products); // Return a copy to prevent external modification
        }

        // Clear the product list in the cart
        public void clear() {
            products.clear();
        }

        // Method to print all products in the cart
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("The cart contains:\n");
            for (Product product : products) {
                sb.append(product.toString()).append("\n");
            }
            sb.append("Total price: ").append(getTotalPrice());
            return sb.toString();
        }
    }

    public class Order {
        private List<Product> products; // List of products in the order
        private double totalPrice; // Total price of the order
        private String status; // Order status
    
        // Constructor
        public Order(Cart cart) {
            this.products = new ArrayList<>(cart.getProducts()); // Copy products from the cart
            this.totalPrice = cart.getTotalPrice();
            this.status = "New"; // Default status when the order is created
        }
    
        // Method to change the order status
        public void setStatus(String status) {
            this.status = status;
        }
        // Getters
        public List<Product> getProducts() {
            return products;
        }
        public double getTotalPrice() {
            return totalPrice;
        }
        public String getStatus() {
            return status;
        }
        // Method to output order information
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Order:\n");
            for (Product product : products) {
                sb.append(product.toString()).append("\n");
            }
            sb.append("Total price: ").append(totalPrice).append("\n");
            sb.append("Status: ").append(status);
            return sb.toString();
        }
    }

    public class OrderHistory {
        private List<Order> orders; // List to store orders

        // Constructor
        public OrderHistory() {
            this.orders = new ArrayList<>();
        }

        // Add an order to the history
        public void addOrder(Order order) {
            orders.add(order);
        }

        // Get the list of all orders
        public List<Order> getOrders() {
            return new ArrayList<>(orders); // Return a copy to prevent external modification
        }

        // Display the order history
        public void displayHistory() {
            if (orders.isEmpty()) {
                System.out.println("No orders placed yet.");
            } else {
                System.out.println("Order History:");
                for (Order order : orders) {
                    System.out.println(order);
                }
            }
        }
    }

    // Unified search method (search by both name and category simultaneously)
    public void searchProducts(String query, List<Product> productList) {
        boolean found = false;
        for (Product product : productList) {
            // Check if the query matches either the product name or the category name (case-insensitive)
            if (product.getName().toLowerCase().contains(query.toLowerCase()) ||
                product.getCategory().getName().toLowerCase().contains(query.toLowerCase())) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products found.");
        }
    }

    // Static Main class with main method
    public static class Main {
        public static void main(String[] args) {
            App app = new App(); // Creating an instance of App

            Scanner scanner = new Scanner(System.in);
            
            // Creating categories
            Category electronics = app.new Category(1, "Electronics");
            Category smartphones = app.new Category(2, "Smartphones");
            Category accessories = app.new Category(3, "Accessories");

            // Creating Product objects with specified category
            Product product1 = app.new Product(1, "Laptop", 19999.99, "High-performance laptop for work and gaming", electronics);
            Product product2 = app.new Product(2, "Smartphone", 12999.50, "Smartphone with a large screen and high battery life", smartphones);
            Product product3 = app.new Product(3, "Headphones", 2499.00, "Wireless noise-canceling headphones", accessories);

            // Creating a cart and order history
            Cart cart = app.new Cart();
            OrderHistory orderHistory = app.new OrderHistory();
            List<Product> productList = new ArrayList<>();
            productList.add(product1);
            productList.add(product2);
            productList.add(product3);

            while (true) {
                System.out.println("\nSelect an option:");
                System.out.println("1 - View product list");
                System.out.println("2 - Add product to cart");
                System.out.println("3 - View cart");
                System.out.println("4 - Remove product from cart");
                System.out.println("5 - Place order");
                System.out.println("6 - View order history");
                System.out.println("7 - Search products");
                System.out.println("0 - Exit");
    
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left by nextInt()
                switch (choice) {
                    case 1:
                        System.out.println(product1);
                        System.out.println(product2);
                        System.out.println(product3);
                        break;
                    case 2:
                        System.out.println("Enter product ID to add to cart:");
                        int id = scanner.nextInt();
                        // Simple logic for adding product using ID
                        if (id == 1) cart.addProduct(product1);
                        else if (id == 2) cart.addProduct(product2);
                        else if (id == 3) cart.addProduct(product3);
                        else System.out.println("Product with this ID not found");
                        break;
                    case 3:
                        System.out.println(cart);
                        break;
                    case 4:
                        System.out.println("Enter product ID to remove from cart:");
                        int removeId = scanner.nextInt();
                        if (cart.removeProductById(removeId)) {
                            System.out.println("Product removed successfully.");
                        } else {
                            System.out.println("Product not found in cart.");
                        }
                        break;
                    case 5:
                        if (cart.getProducts().isEmpty()) {
                            System.out.println("Your cart is empty. Please add items before placing an order.");
                        } else {
                            Order order = app.new Order(cart);
                            orderHistory.addOrder(order); // Add order to history
                            System.out.println("Order placed:");
                            System.out.println(order);
                            cart.clear(); // Clear the cart after placing the order
                        }
                        break;
                    case 6:
                        orderHistory.displayHistory();
                        break;
                    case 7:
                        System.out.println("Enter search query (name or category):");
                        String searchQuery = scanner.nextLine();
                        app.searchProducts(searchQuery, productList); // Search by both name and category (Я подумав, що так буде зручніше для користувача)
                        break;
                    case 0:
                        System.out.println("Thank you for using our store!");
                        return;
                    default:
                        System.out.println("Unknown option. Please try again.");
                        break;
                }
            }
        }
    }   
}