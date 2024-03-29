import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class RestaurantApp {
    private static List<MenuItem> menuItems = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static GetFileData getFileData = new GetFileData();
    private static int orderIDCounter = generateOrderId();

    public static void main(String[] args) {
        menuItems = getFileData.initializeFiles();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nRestaurant Management System");
                System.out.println("1. Place Order");
                System.out.println("2. Cancel Order");
                System.out.println("3. Generate Daily Collection Report");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            placeOrder(scanner);
                            break;
                        case 2:
                            cancelOrder(scanner);
                            break;
                        case 3:
                            generateCollectionReport();
                            break;
                        case 4:
                            System.out.println("Exiting the application.");
                            scanner.close();
                            getFileData.updateOrderDataToCSVFile(orders);
                            getCollectionReport();
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid integer choice.");
                    scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void placeOrder(Scanner scanner) {
        System.out.println("\nMenu Items:");
        for (MenuItem item : menuItems) {
            System.out.println(item.getMenuID() + ". " + item.getName() + " - Rs." + item.getPrice());
        }

        System.out.print("Enter menu item IDs (comma-separated): ");
        String[] itemIDs = scanner.nextLine().split(",");
        List<MenuItem> selectedItems = new ArrayList<>();

        for (String itemID : itemIDs) {
            int id = Integer.parseInt(itemID.trim());
            MenuItem menuItem = findMenuItemByID(id);
            if (menuItem != null) {
                selectedItems.add(menuItem);
            }
        }

        if (selectedItems.isEmpty()) {
            System.out.println("Invalid item IDs. Order not placed.");
            return;
        }

        Order order = new Order(orderIDCounter++, selectedItems);
        orders.add(order);
        getFileData.saveOrderDataToCSVFile(order);
        System.out.println("Order placed successfully, Food will delivered soon. Order ID: " + order.getOrderID());
        getCollectionReport();
    }

    private static void cancelOrder(Scanner scanner) {
        System.out.print("Enter Order ID to cancel: ");
        int orderID = scanner.nextInt();
        scanner.nextLine();

        Order order = findOrderByID(orderID);
        if (order != null) {
            if (!order.isCancelled()) {
                order.setStatus("Canceled");
                order.setTotalAmount(0);
                order.cancelOrder();
                getFileData.updateOrderDataToCSVFile(orders);
                System.out.println("Order " + order.getOrderID() + " has been canceled.");
            } else {
                System.out.println("Order " + order.getOrderID() + " is already canceled.");
            }
        } else {
            System.out.println("Order not found.");
        }
        System.out.println(order);
        getCollectionReport();
    }

    private static MenuItem findMenuItemByID(int id) {
        for (MenuItem item : menuItems) {
            if (item.getMenuID() == id) {
                return item;
            }
        }
        return null;
    }

    private static Order findOrderByID(int id) {
        for (Order order : orders) {
            if (order.getOrderID() == id) {
                return order;
            }
        }
        return null;
    }

    private static void generateCollectionReport() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CollectionReport report = getCollectionReport();
        System.out.println("Daily Collection Report for " + dateFormat.format(report.getDate()) + ": Total Collection: Rs." + report.getTotalCollection());
    }

    private static CollectionReport getCollectionReport() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        double totalCollection = 0;

        for (Order order : orders) {
            if (!order.isCancelled() && dateFormat.format(order.getDate()).equals(dateFormat.format(today))) {
                totalCollection += order.getTotalAmount();
            }
        }

        CollectionReport report = new CollectionReport(today, totalCollection);
        getFileData.saveCollectionReportToCSV(report);
        return report;
    }

//    OrderId is generated using the month, day, and an initial value
    private static int generateOrderId() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();
        int value = 1;
        String result = month + "" + day + "" + value;
        return Integer.parseInt(result);
    }
}

