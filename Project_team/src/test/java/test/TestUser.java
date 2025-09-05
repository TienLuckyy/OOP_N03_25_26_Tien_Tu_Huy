package test;

import model.User;

public class TestUser {
    public static void main(String[] args) {
        // Khởi tạo đối tượng User
        User user1 = new User();
        user1.setUserName("John Doe");
        user1.setUserAddress("123 Main Street");

        User user2 = new User("Jane Doe", "456 Elm Street");

        // In thông tin người dùng
        System.out.println(user1);  // In thông tin của user1
        System.out.println(user2);  // In thông tin của user2
    }
}
