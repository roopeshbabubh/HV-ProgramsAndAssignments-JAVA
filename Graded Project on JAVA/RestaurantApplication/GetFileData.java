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

//    You can generate and save data in "menuItemData.csv" by calling the 'createMenuItemDataCSVFile()' method
//    after initializing it within the 'initializeFiles()' method.
//    public void createMenuItemDataCSVFile() {
//        String header = "ItemID,Item Name,Price\n";
//
//        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(menuItemData))) {
//
//            bufferedWriter.write(header);
//            bufferedWriter.write("1,Burger,250\n");
//            bufferedWriter.write("2,Pizza,650\n");
//            bufferedWriter.write("3,Fries,150\n");
//            bufferedWriter.write("4,Bhel Puri,100\n");
//            bufferedWriter.write("5,Sev Puri,70\n");
//            bufferedWriter.write("6,Aloo Tikki Chaat,85\n");
//            bufferedWriter.write("7,Dahi Puri,95\n");
//            bufferedWriter.write("8,Chana Chaat,50\n");
//            bufferedWriter.write("9,Ragda Pattice,55\n");
//            bufferedWriter.write("10,Pav Bhaji,125\n");
//            bufferedWriter.write("11,Chole Samosa Chaat,130\n");
//            bufferedWriter.write("12,Kachori,30\n");
//            bufferedWriter.write("13,Papdi,40\n");
//            bufferedWriter.write("14,Dabeli,55\n");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

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

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(orderDataFileName, false))) {
            bufferedWriter.write(header);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveOrderDataToCSVFile(Order order) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append(order.getOrderID()).append(",");

        StringBuilder itemDetails = new StringBuilder();
        List<MenuItem> items = order.getItems();
        for (MenuItem item : items) {
            itemDetails.append(item);
        }
        orderDetails.append(itemDetails);
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
        // Read the existing orders from the CSV file
        List<String> existingOrders = readOrderDataCSVFile();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(orderDataFileName, true))) {
            createOrderDataCSVFile();  // Rewrite the header

            // Append existing orders except those with matching dates
            for (String existingOrder : existingOrders) {
                bufferedWriter.write(existingOrder+"\n");
            }

            // Append new orders
            for (Order order : orders) {
                saveOrderDataToCSVFile(order);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readOrderDataCSVFile() {
        List<String> lines = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(orderDataFileName))) {
            String line = bufferedReader.readLine(); // Consume the header
            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");

                // Ensure that there are at least 5 fields in the CSV line
                if (fields.length >= 5) {
                    String oldDate = fields[2];


                    // Check if the order date is not equal to the current date
                    if (!oldDate.equals(currentDate)) {
                        lines.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
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
//        createMenuItemDataCSVFile();
        return getMenuDataFromCSVFile();
    }
}
