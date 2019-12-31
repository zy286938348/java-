package entity;

public class Order {
    private int id;
    private String username;
    private String  merchant;
    private String menuName;
    private double price;
    private int num;
    private double sum;
    private String datatime;

    public Order(int id, String username, String merchant, String menuName, double price, int num, double sum) {
        this.id = id;
        this.username = username;
        this.merchant = merchant;
        this.menuName = menuName;
        this.price = price;
        this.num = num;
        this.sum = sum;
    }

    public Order(int id, String username, String merchant, String menuName, double price, int num, double sum, String datatime) {
        this.id = id;
        this.username = username;
        this.merchant = merchant;
        this.menuName = menuName;
        this.price = price;
        this.num = num;
        this.sum = sum;
        this.datatime = datatime;
    }

    public Order(int id, String username, String menuName, double price, int num, double sum, String datatime) {
        this.id = id;
        this.username = username;
        this.menuName = menuName;
        this.price = price;
        this.num = num;
        this.sum = sum;
        this.datatime = datatime;
    }

    public Order(String username, String merchant, String menuName, double price, int num) {
        this.username = username;
        this.merchant = merchant;
        this.menuName = menuName;
        this.price = price;
        this.num = num;
    }

//    public Order(String username, String merchant, String menuName, double price, int num, double sum) {
//        this.username = username;
//        this.merchant = merchant;
//        this.menuName = menuName;
//        this.price = price;
//        this.num = num;
//        this.sum = sum;
//    }

    //    public Order(String username, String merchant, String menuName) {
//        this.username = username;
//        this.merchant = merchant;
//        this.menuName = menuName;
//    }
    public Order(String username,String menuName,double price,String datatime){
        this.menuName = menuName;
        this.username = username;
        this.price = price;
        this.datatime = datatime;
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

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", merchant='" + merchant + '\'' +
                ", menuName='" + menuName + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", sum=" + sum +
                ", datatime='" + datatime + '\'' +
                '}';
    }
}
