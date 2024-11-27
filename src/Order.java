import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    private List<Product> products;
    private double totalPrice;

    @Setter
    private String status;

    public Order(Cart cart) {
        this.products = new ArrayList<>(cart.getProducts()); // Copy products from cart
        this.totalPrice = cart.getTotalPrice();
        this.status = "New";
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
