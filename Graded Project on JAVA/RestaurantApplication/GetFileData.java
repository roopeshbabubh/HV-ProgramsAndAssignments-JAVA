import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GetFileData {
    private static List<MenuItem> menuItems = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private boolean isInitialized = false;
    String orderDataFileName = "OrderData.csv";
    String menuItemData = "menuItemData.csv";

    public boolean createMenuItemDataCSVFile() {
        String header = "ItemID,Item Name,Price\n";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(menuItemData))) {

            bufferedWriter.write(header);
            bufferedWriter.write("1,Burger,250\n");
            bufferedWriter.write("2,Pizza,650\n");
            bufferedWriter.write("3,Fries,150\n");

            isInitialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isInitialized;
    }

    public List<MenuItem> getMenuDataFromCSVFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(menuItemData))) {
            String line = bufferedReader.readLine();  // To consume the header

            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                menuItems.add(new MenuItem(Integer.parseInt(fields[0]), fields[1], Double.parseDouble(fields[2])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    public boolean createOrderDataCSVFile() {
        String header = "OrderID,Items Details,Date,TotalAmount,Status\n";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(orderDataFileName))) {
            bufferedWriter.write(header);
            isInitialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isInitialized;
    }

    public void setOrderDataToCSVFile(Order order) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append(order.getOrderID()).append(",");

        List<MenuItem> items = order.getItems();
        orderDetails.append("\"");
        for (MenuItem item : items) {
            orderDetails.append("Item : ").append(item.getName()).append("   Price: Rs.").append(item.getPrice()).append("\n");
        }
        orderDetails.append("\"");

        orderDetails.append(",").append(dateFormat.format(order.getDate())).append(",");
        orderDetails.append("Rs." + order.getTotalAmount()).append(",");
        orderDetails.append(order.getStatus()).append("\n");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(orderDataFileName, true))) {
            bufferedWriter.write(orderDetails.toString());
            System.out.println("Data written to CSV successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateOrderDataToCSVFile(List<Order> orders) {
        try (FileWriter fileWriter = new FileWriter(orderDataFileName, false)) {
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Order order: orders) {
            setOrderDataToCSVFile(order);
        }
    }


    public List<MenuItem> initializeFiles() {
        if (!createMenuItemDataCSVFile() && !createOrderDataCSVFile()) {
            System.out.println("CSV file is not created.");
        }
        return getMenuDataFromCSVFile();
    }
}
