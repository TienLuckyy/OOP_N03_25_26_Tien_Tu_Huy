
public class User {
    
    private String studentId;
    private String fullName;
    private String email;

    // Hàm khởi tạo (Constructor) để gán giá trị ban đầu cho đối tượng
    public User(String studentId, String fullName, String email) {
        // 'this' được dùng để phân biệt biến của lớp và tham số đầu vào
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
    }


    public String getStudentId() {
        return this.studentId;
    }

    // Setter cho studentId
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    // Getter cho fullName
    public String getFullName() {
        return this.fullName;
    }

    // Setter cho fullName
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter cho email
    public String getEmail() {
        return this.email;
    }

    // Setter cho email
    public void setEmail(String email) {
        this.email = email;
    }

    // Một phương thức để hiển thị thông tin người dùng
    public void displayInfo() {
        System.out.println("Student ID: " + this.studentId);
        System.out.println("Full Name: " + this.fullName);
        System.out.println("Email: " + this.email);
    }
}