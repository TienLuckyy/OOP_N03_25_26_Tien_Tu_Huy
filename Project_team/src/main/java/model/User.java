package model;


public class User {
    // Biến mô tả User
    private String studentId;
    private String fullName;
    private String email;

    // Hàm khởi tạo
    public User(String studentId, String fullName, String email) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
    }

    // Phương thức Set và Get
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    // Phương thức để hiển thị thông tin
    public void displayInfo() {
        System.out.println("ID: " + this.studentId + ", Name: " + this.fullName + ", Email: " + this.email);
    }
}