import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> orders; // List of all placed orders

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    // Add a new order to the history
    public void addOrder(Order order) {
        orders.add(order);
    }

    // Get the list of all orders
    public List<Order> getOrders() {
        return new ArrayList<>(orders); // Return a copy to avoid external modifications
    }

    // Display all orders in the history
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
