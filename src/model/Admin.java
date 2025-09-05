package model;

public class Admin extends User {
    public Admin(String userId, String username, String password, String email, String phone) {
        super(userId, username, password, email, phone);
    }

    public void manageFlights() {
        // Logic to manage flights
    }

    public void manageTickets() {
        // Logic to manage tickets
    }

    public void manageCustomers() {
        // Logic to manage customers
    }

    public void viewReports() {
        // Logic to view reports
    }
}

