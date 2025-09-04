package com.dormitorymanagement;

import model.User;

public class TestUser {
    public static void main(String[] args) {
        System.out.println("--- Testing User Class ---");
        User user = new User("22010052", "Duong Ngoc Tu", "tu.dn@email.com");
        user.displayInfo();
    }
}