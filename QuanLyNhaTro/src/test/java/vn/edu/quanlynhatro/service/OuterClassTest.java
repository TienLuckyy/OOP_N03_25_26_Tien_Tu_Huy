package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.controller.outer_Inner.OuterClass;

public class OuterClassTest {
    public static void main(String[] args) {
        // Tạo OuterClass
        OuterClass outer = new OuterClass();

        // Tạo InnerClass từ OuterClass
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.showOuterField();

        // Hoặc dùng method factory
        OuterClass.InnerClass inner2 = outer.createInner();
        inner2.showOuterField();
    }
}
