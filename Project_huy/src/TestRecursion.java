public class TestRecursion {
    public static void main(String[] args) {
        int[] danhSach = {2, 0, 1, 0};
        Recursion kyTucXa = new Recursion(danhSach);

        int phongTrong = kyTucXa.timPhongTrong(0);

        if(phongTrong != -1) {
            System.out.println("Phong trong dau tien: " + phongTrong);
        }else {
            System.out.println("Tat ca cac phong deu da day");
        }
    }
}
