public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Bia.biaGiap =new Bia();
        biaGiap.tembia = "Bia cua giap";
        Bia biaVietAnh = new Bia();
        biaVietAnh.tembia = "Sai Gon";

        System.out.println("1::"+ biaVietAnh.getTen());
        System.out.println("2::"+ biaGiap.getTen());
    }
}
