package vn.edu.quanlynhatro.baitap06;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Interfaces Demo ===");
        QuanLy ql = new BanQuanLyService();
        ql.them();
        ql.sua();
        ql.xoa();

        System.out.println("\n=== Callback Demo ===");
        Caller caller = new Caller();
        caller.go(i -> System.out.println("Callback gọi lần " + i));

        System.out.println("\n=== Public Inner Class Demo ===");
        OuterPublic outerPub = new OuterPublic();
        OuterPublic.InnerPublic innerPub = outerPub.new InnerPublic();
        innerPub.show();

        System.out.println("\n=== Private Inner Class Demo ===");
        OuterPrivate outerPri = new OuterPrivate();
        outerPri.go();
    }
}
