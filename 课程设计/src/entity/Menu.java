package entity;

public class Menu {
    private int id;
    private String userName;
    private String path;
    private String menuName;
    private double price;

    public Menu(String userName, String path, String menuName, double price) {
        this.userName = userName;
        this.path = path;
        this.menuName = menuName;
        this.price = price;
    }

    public Menu(int id, String userName, String path, String menuName, double price) {
        this.id = id;
        this.userName = userName;
        this.path = path;
        this.menuName = menuName;
        this.price = price;
    }

    public Menu(int id, String path, String menuName, double price) {
        this.id = id;
        this.path = path;
        this.menuName = menuName;
        this.price = price;
    }

    public Menu() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", path='" + path + '\'' +
                ", menuName='" + menuName + '\'' +
                ", price=" + price +
                '}';
    }
}
