public class TestTime {
    public static void main(String[] args) {
        Time t1 = new Time(22, 30, 0);
        System.out.print("Gio dong cua: ");
        t1.hienThi();

        Time t2 = new Time(50, 70, -5);
        System.out.print("Thoi gian tu dong sua: ");
        t2.hienThi();

        t2.setGio(7);
        t2.setPhut(45);
        t2.setGiay(59);
        System.out.print("Sau khi sua: ");
        t2.hienThi();
    }
}
