import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products; // List of products in the cart

    public Cart() {
        this.products = new ArrayList<>();
    }

    // Add a product to the cart
    public void addProduct(Product product) {
        products.add(product);
    }

    // Remove a product from the cart by ID
    public boolean removeProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    // Get total price of all products in the cart
    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    // Get the list of products in the cart
    public List<Product> getProducts() {
        return new ArrayList<>(products); // Return a copy to avoid external modifications
    }

    // Clear all products from the cart
    public void clear() {
        products.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cart contains:\n");
        for (Product product : products) {
            sb.append(product.toString()).append("\n");
        }
        sb.append("Total price: ").append(getTotalPrice());
        return sb.toString();
    }
}
