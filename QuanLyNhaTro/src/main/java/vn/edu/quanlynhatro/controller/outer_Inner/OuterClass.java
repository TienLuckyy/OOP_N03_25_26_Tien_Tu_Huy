package vn.edu.quanlynhatro.controller.outer_Inner;

public class OuterClass {
    private String outerField = "Outer field";

    // Inner class
    public class InnerClass {
        public void showOuterField() {
            // Inner class có thể truy cập private field của Outer
            System.out.println("Accessing from InnerClass: " + outerField);
        }
    }

    // Phương thức tạo InnerClass
    public InnerClass createInner() {
        return new InnerClass();
    }
}
