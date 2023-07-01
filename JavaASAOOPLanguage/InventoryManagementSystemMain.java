import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private String specification;
    private double cost;
    private int quantity;

    public Product(String name, String specification, double cost, int quantity) {
        this.name = name;
        this.specification = specification;
        this.cost = cost;
        this.quantity = quantity;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public String getSpecification() {
        return specification;
    }

    public double getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class InventoryManagementSystemWithOops {
    private List<Product> products;

    public InventoryManagementSystemWithOops() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayProductList() {
        System.out.println("Product List:");
        for (Product product : products) {
            System.out.println("- " + product.getName());
        }
    }

    public int getProductCount(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product.getQuantity();
            }
        }
        return 0;
    }

    public void viewProductDetails(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                System.out.println("Product Details:");
                System.out.println("Name: " + product.getName());
                System.out.println("Specification: " + product.getSpecification());
                System.out.println("Cost: Rs." + product.getCost());
                System.out.println("Quantity available: " + product.getQuantity());
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void editProduct(String productName, String newSpecification, double newCost) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                product.setSpecification(newSpecification);
                product.setCost(newCost);
                System.out.println("Product details updated successfully.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void updateInventoryAdd(String productName, int quantity) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                int updatedQuantity = product.getQuantity() + quantity;
                if (updatedQuantity < 0) {
                    System.out.println("Invalid operation. Not enough quantity available.");
                    return;
                }
                product.setQuantity(updatedQuantity);
                System.out.println("Inventory updated successfully.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void updateInventorySubstract(String productName, int quantity) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                if(product.getQuantity() >= quantity) {
                    int updatedQuantity = product.getQuantity() - quantity;
                    product.setQuantity(updatedQuantity);
                    System.out.println("Inventory updated successfully.");
                    return;
                }
            }
        }
        System.out.println("Product not found or the product quantity is less then requested quantity to delete.");
    }
}

public class InventoryManagementSystemMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManagementSystemWithOops inventoryManagementSystem = new InventoryManagementSystemWithOops();

        // Data sample
        inventoryManagementSystem.addProduct(new Product("Laptop", "Gaming Laptop", 150000, 10));
        inventoryManagementSystem.addProduct(new Product("Phone", "Smartphone", 8000, 20));
        inventoryManagementSystem.addProduct(new Product("Headphones", "Wireless Headphones", 1000, 50));

        while (true) {
            System.out.println("\n----- Inventory Management System -----");
            System.out.println("1. Product List");
            System.out.println("2. Product Count");
            System.out.println("3. View Product Details");
            System.out.println("4. Edit Product");
            System.out.println("5. Update Inventory");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            System.out.println("\n---------------------------------------");

            switch (choice) {
                case 1:
                    inventoryManagementSystem.displayProductList();
                    break;
                case 2:
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    int count = inventoryManagementSystem.getProductCount(productName);
                    System.out.println("Product Count: " + count);
                    break;
                case 3:
                    System.out.print("Enter product name: ");
                    productName = scanner.nextLine();
                    inventoryManagementSystem.viewProductDetails(productName);
                    break;
                case 4:
                    System.out.print("Enter product name: ");
                    productName = scanner.nextLine();
                    System.out.print("Enter new specification: ");
                    String newSpecification = scanner.nextLine();
                    System.out.print("Enter new cost: ");
                    double newCost = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    inventoryManagementSystem.editProduct(productName, newSpecification, newCost);
                    break;
                case 5:
                    System.out.print("Enter product name: ");
                    productName = scanner.nextLine();
                    System.out.print("Select \"1\" to add quantity / Select \"2\" to delete quantity : ");
                    int updateOption = scanner.nextInt();
                    System.out.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    if (updateOption == 1) {
                        inventoryManagementSystem.updateInventoryAdd(productName, quantity);
                    } else if (updateOption == 2) {
                        inventoryManagementSystem.updateInventorySubstract(productName, quantity);
                    }
                    break;
                case 6:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
