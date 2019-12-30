package entity;

public class User {

    private int id;
    private String path;
    private String username;
    private String name;
    private String password;
    private int age;
    private String sex;
    private String type;
    private String data;

    public User(int id, String username, String name, String password, int age, String sex, String type) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.type = type;
    }

    public User() {

    }
    public User(int id, String username, String name, String sex, int age, String data) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.data = data;
    }

    public User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(int id, String path, String username, String name, String sex, int age, String data) {
        this.id = id;
        this.path = path;
        this.username = username;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.data = data;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", type='" + type + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
