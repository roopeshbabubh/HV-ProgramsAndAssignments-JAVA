import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetFileData {
    private static List<MenuItem> menuItems = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    String orderDataFileName = "OrderData.csv";
    String menuItemData = "menuItemData.csv";
    String collectionReportData = "collection_report.csv";

    public void createMenuItemDataCSVFile() {
        String header = "ItemID,Item Name,Price\n";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(menuItemData))) {

            bufferedWriter.write(header);
            bufferedWriter.write("1,Burger,250\n");
            bufferedWriter.write("2,Pizza,650\n");
            bufferedWriter.write("3,Fries,150\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void createOrderDataCSVFile() {
        String header = "OrderID,Items Details,Date,TotalAmount,Status\n";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(orderDataFileName))) {
            bufferedWriter.write(header);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveOrderDataToCSVFile(Order order) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateOrderDataToCSVFile(List<Order> orders) {
        try (FileWriter fileWriter = new FileWriter(orderDataFileName, false)) {
            fileWriter.write("");
            createOrderDataCSVFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Order order: orders) {
            saveOrderDataToCSVFile(order);
        }
    }

    public void createCollectionReportCSVFile() {
        String header = "Date,TotalAmount\n";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(collectionReportData))) {
            bufferedWriter.write(header);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CollectionReport> readCollectionReportCSVFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<CollectionReport> collectionReports = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(collectionReportData))) {
            String line = bufferedReader.readLine();  // To consume the header

            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                try {
                    Date date = dateFormat.parse(fields[0]);
                    double totalCollection = Double.parseDouble(fields[1]);
                    collectionReports.add(new CollectionReport(date, totalCollection));
                } catch (ParseException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collectionReports;
    }

    public void saveCollectionReportToCSV(CollectionReport report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(collectionReportData, true))) {
            List<CollectionReport> collectionReports = readCollectionReportCSVFile();
            bufferedWriter.write("");
            createCollectionReportCSVFile();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(report.getDate());
            boolean updated = false;

            for (CollectionReport oldReport : collectionReports) {
                String oldFormattedDate = dateFormat.format(oldReport.getDate());

                if (oldFormattedDate.equals(formattedDate)) {
                    bufferedWriter.append(formattedDate);
                    bufferedWriter.append(",");
                    bufferedWriter.append(String.valueOf(report.getTotalCollection()));
                    bufferedWriter.append("\n");
                    updated = true;
                } else {
                    bufferedWriter.append(oldFormattedDate);
                    bufferedWriter.append(",");
                    bufferedWriter.append(String.valueOf(oldReport.getTotalCollection()));
                    bufferedWriter.append("\n");
                }
            }
            if (!updated) {
                bufferedWriter.append(formattedDate);
                bufferedWriter.append(",");
                bufferedWriter.append(String.valueOf(report.getTotalCollection()));
                bufferedWriter.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<MenuItem> initializeFiles() {
        createMenuItemDataCSVFile();
        createOrderDataCSVFile();
        return getMenuDataFromCSVFile();
    }
}