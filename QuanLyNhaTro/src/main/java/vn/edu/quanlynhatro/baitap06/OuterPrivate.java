package vn.edu.quanlynhatro.baitap06;

public class OuterPrivate {
    private class InnerPrivate {
        public void show() {
            System.out.println("Đây là Private Inner Class");
        }
    }

    public void go() {
        InnerPrivate inner = new InnerPrivate();
        inner.show();
    }
}
