import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products; // List of products in the order
    private double totalPrice; // Total price of the order
    private String status; // Order status

    public Order(Cart cart) {
        this.products = new ArrayList<>(cart.getProducts()); // Copy products from cart
        this.totalPrice = cart.getTotalPrice();
        this.status = "New"; // Default status when the order is created
    }

    // Set the status of the order
    public void setStatus(String status) {
        this.status = status;
    }

    // Get the list of products in the order
    public List<Product> getProducts() {
        return products;
    }

    // Get the total price of the order
    public double getTotalPrice() {
        return totalPrice;
    }

    // Get the status of the order
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order details:\n");
        for (Product product : products) {
            sb.append(product.toString()).append("\n");
        }
        sb.append("Total price: ").append(totalPrice).append("\n");
        sb.append("Status: ").append(status);
        return sb.toString();
    }
}
