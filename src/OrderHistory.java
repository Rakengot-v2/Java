import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderHistory {
    private List<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void displayHistory() {
        if (orders.isEmpty()) {
            System.out.println("No orders have been placed yet.");
            return;
        }
        System.out.println("Order History:");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("Order #" + (i + 1));
            System.out.println(orders.get(i));
            System.out.println("---------------------");
        }
    }
}
