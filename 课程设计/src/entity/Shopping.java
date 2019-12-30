package entity;

public class Shopping {
    int id;
    private String username;
    private String sUsername;
    private String menuName;
    private double price;
    private double num;
    private double sum;

    public Shopping(int id, String username, String sUsername, String menuName, double price, double num, double sum) {
        this.id = id;
        this.username = username;
        this.sUsername = sUsername;
        this.menuName = menuName;
        this.price = price;
        this.num = num;
        this.sum = sum;
    }

    public Shopping(String username, String sUsername, String menuName, double price, double num, double sum) {
        this.username = username;
        this.sUsername = sUsername;
        this.menuName = menuName;
        this.price = price;
        this.num = num;
        this.sum = sum;
    }

    public Shopping(String sUsername, String menuName, double price, double num, double sum) {
        this.sUsername = sUsername;
        this.menuName = menuName;
        this.price = price;
        this.num = num;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getsUsername() {
        return sUsername;
    }

    public void setsUsername(String sUsername) {
        this.sUsername = sUsername;
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

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sUsername='" + sUsername + '\'' +
                ", menuName='" + menuName + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", sum=" + sum +
                '}';
    }
}
