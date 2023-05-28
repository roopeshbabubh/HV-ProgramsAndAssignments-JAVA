import java.util.Scanner;

public class InventoryManagementSystem {

    Scanner scanner = new Scanner(System.in);

    String[] productNames;
    String[] specifications;
    int[] quantities;

    public InventoryManagementSystem(String[] productNames, String[] specifications, int[] quantities) {
        this.productNames = productNames;
        this.specifications = specifications;
        this.quantities = quantities;
    }

    public void displayMenu() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to the SmartPoint Electronics Store");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("1. View the complete list of our products");
        System.out.println("2. Check the available count for a specific product");
        System.out.println("3. View the specifications and details of a specific product");
        System.out.println("4. Modify the details of a specific product");
        System.out.println("5. Update the inventory for a specific product");
        System.out.println("6. Exit");
        System.out.println();
    }

    public void displayProductList() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("List of all Products");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("Product ID\tProduct Name");
        for (int i = 0; i < productNames.length; i++) {
            if (productNames[i] != null) {
                System.out.printf("%7d\t\t%s\n", i + 101, productNames[i]);
            }
        }
        System.out.print("-----------------------------------------------------------------------------------------------------");
    }

    public int productIdFromUser() {
        System.out.print("Enter the Product ID: ");
        return scanner.nextInt();
    }

    public void displayProductCount(int productId) {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println(productId + " " + productNames[productId - 101]);
        System.out.println("Total available count: " + quantities[productId - 101]);
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }

    public void displaySpecifications(int productId) {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println(productId + " " + productNames[productId - 101]);
        System.out.println("Total available count: " + quantities[productId - 101]);
        System.out.println("Specifications: " + specifications[productId - 101]);
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }

    public void editProductSpecifications(int productId) {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println(productId + " " + productNames[productId - 101]);
        System.out.println("Total available count: " + quantities[productId - 101]);
        System.out.println("Enter new specifications: ");
        specifications[productId - 101] = scanner.nextLine();
        System.out.println("Product Specifications updated successfully!");
        System.out.print("-----------------------------------------------------------------------------------------------------");
    }

    public void updateInventory(int productId) {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println(productId + " " + productNames[productId - 101]);
        System.out.println("Total available count: " + quantities[productId - 101]);
        System.out.println("1. Add inventory");
        System.out.println("2. Subtract inventory");
        System.out.print("Please choose an option from the above menu: ");
        int option = scanner.nextInt();
        System.out.print("Please enter the count: ");
        int count = scanner.nextInt();
        if (option == 1) {
            quantities[productId - 101] += count;
            System.out.println("Inventory updated successfully!");
        } else if (option == 2) {
            if (quantities[productId - 101] >= count) {
                quantities[productId - 101] -= count;
                System.out.println("Inventory updated successfully!");
            } else {
                System.out.println("Insufficient inventory!");
            }
        } else {
            System.out.println("Invalid option!");
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }

    public void run() {
        String choice;

        do {
            displayMenu();
            System.out.print("Please choose an option from the above menu: ");
            int option = scanner.nextInt();
            System.out.println();

            switch (option) {
                case 1:
                    displayProductList();
                    break;
                case 2:
                    displayProductCount(productIdFromUser());
                    break;
                case 3:
                    displaySpecifications(productIdFromUser());
                    break;
                case 4:
                    editProductSpecifications(productIdFromUser());
                    break;
                case 5:
                    updateInventory(productIdFromUser());
                    break;
                case 6:
                    thankNote();
                    return;
                default:
                    System.out.println("Invalid option!");
            }

            System.out.println();
            System.out.print("Enter \"Y\" to return to the main menu or \"N\" to Exit: ");
            choice = scanner.next();
            System.out.println();
        } while (choice.equalsIgnoreCase("Y"));
        thankNote();
    }

    public void thankNote() {
        System.out.println("Thank you for visiting SmartPoint!");
    }

    public static void main(String[] args) {

        String[] productNames = {"Mobile", "Laptop", "Smart-watch", "Portable HDD", "Bluetooth Headphone", "Tablet", "Digital Camera", "Portable Power bank", "Printer", "Wireless Router"};
        String[] specifications = {"Compatibility - iOS and Android | Water Resistance | Battery Life - 2 days | GPS, fitness tracking, sleep monitoring, step counting, and more",
                "Compatibility - Windows, macOS, Linux | Not Water Resistant | Battery Life - Varies by model | Dependent on the specific laptop model",
                "Compatibility - iOS and Android | Water Resistance | Battery Life - 2 days | GPS, fitness tracking, sleep monitoring, step counting, and more",
                "Compatibility - Windows, macOS, Linux | Water Resistance | No Battery | High storage capacity, USB connectivity, fast data transfer speeds, and more",
                "Compatibility - iOS and Android | Not Water Resistant | Battery Life - Varies by model | Wireless connectivity, noise cancellation, built-in microphone, ergonomic design, and more",
                "Compatibility - Supports both iOS and Android operating systems | Water Resistance - IPX7 water and dust resistance rating | Battery Life - 2 days | GPS, fitness tracking, sleep monitoring, step counting, and more",
                "Compatibility - Dependent on the camera model | Water Resistance - Varies by model | Battery Life - Dependent on the camera model | High-resolution sensor, optical zoom, manual controls, and more",
                "Compatibility - Compatible with devices that charge via USB | Not Water Resistant | Battery Life - Varies by power bank capacity | Portable charging solution for various devices, and more",
                "Compatibility - Dependent on the printer model | Not Water Resistant | No Battery | Wireless or wired connectivity, high-quality printing, and more",
                "Compatibility - Compatible with devices that connect to Wi-Fi | Not Water Resistant | No Battery | High-speed wireless connectivity, extended coverage,  and more"};
        int[] quantities = {25, 16, 58, 67, 45, 22, 34, 33, 54, 15};

        InventoryManagementSystem inventorySystem = new InventoryManagementSystem(productNames, specifications, quantities);
        inventorySystem.run();

    }
}
