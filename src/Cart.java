import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean removeProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

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
