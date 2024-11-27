import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize the application logic
        Category electronics = new Category(1, "Electronics");
        Category smartphones = new Category(2, "Smartphones");
        Category accessories = new Category(3, "Accessories");

        Product product1 = new Product(1, "Laptop", 19999.99, "High-performance laptop for work and gaming", electronics);
        Product product2 = new Product(2, "Smartphone", 12999.50, "Smartphone with a large screen and high battery life", smartphones);
        Product product3 = new Product(3, "Headphones", 2499.00, "Wireless noise-canceling headphones", accessories);

        Cart cart = new Cart();
        OrderHistory orderHistory = new OrderHistory();
        List<Product> productList = List.of(product1, product2, product3);

        Scanner scanner = new Scanner(System.in);

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
                        Order order = new Order(cart);
                        order.setStatus("Completed");
                        orderHistory.addOrder(order);
                        System.out.println("Order placed:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;
                case 6:
                    orderHistory.displayHistory();
                    break;
                case 7:
                    System.out.println("Enter search query (name or category):");
                    String searchQuery = scanner.nextLine();
                    for (Product product : productList) {
                        if (product.getName().equalsIgnoreCase(searchQuery) || product.getCategory().getName().equalsIgnoreCase(searchQuery)) {
                            System.out.println(product);
                        }
                    }
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
