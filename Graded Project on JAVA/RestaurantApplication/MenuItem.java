public class MenuItem {

    private int menuID;
    private String name;
    private double price;

    public MenuItem(int menuID, String name, double price) {
        this.menuID = menuID;
        this.name = name;
        this.price = price;
    }

    public int getMenuID() {
        return menuID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return  "[ menuID=" + menuID +
                " name='" + name + '\'' +
                " price=" + price + "]";
    }
}
