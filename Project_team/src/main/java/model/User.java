package model;

public class User {
    private String userName;
    private String userAddress;

    // Constructor mặc định
    public User() {}

    // Constructor có tham số
    public User(String name, String address) {
        this.userName = name;
        this.userAddress = address;
    }

    // Getter và Setter
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    // Phương thức toString để dễ dàng in thông tin người dùng
    @Override
    public String toString() {
        return "User Name: " + userName + ", Address: " + userAddress;
    }
}
