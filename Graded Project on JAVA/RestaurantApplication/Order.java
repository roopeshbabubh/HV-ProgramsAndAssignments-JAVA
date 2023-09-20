import java.util.Date;
import java.util.List;

public class Order {

    private int orderID;
    private List<MenuItem> items;
    private Date date;
    private double totalAmount;
    private String status;
    private boolean isCancelled;

    public Order(int orderID, List<MenuItem> items) {
        this.orderID = orderID;
        this.items = items;
        this.date = new Date();
        this.status = "Placed";
        this.isCancelled = false;
        calculateTotalAmount();
    }

    public int getOrderID() {
        return orderID;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void cancelOrder() {
        this.isCancelled = true;
    }

    private void calculateTotalAmount() {
        totalAmount = 0;
        for (MenuItem item : items) {
            totalAmount += item.getPrice();
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", items=" + items +
                ", date=" + date +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", isCancelled=" + isCancelled +
                '}';
    }
}
