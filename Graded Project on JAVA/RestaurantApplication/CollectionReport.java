import java.util.Date;

public class CollectionReport {
    private Date date;
    private double totalCollection;

    public CollectionReport(Date date, double totalCollection) {
        this.date = date;
        this.totalCollection = totalCollection;
    }

    public Date getDate() {
        return date;
    }

    public double getTotalCollection() {
        return totalCollection;
    }
}
