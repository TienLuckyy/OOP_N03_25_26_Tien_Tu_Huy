public class Recursion {
    int[] danhSachPhong;

    public Recursion(int[] danhSachPhong) {
        this.danhSachPhong = danhSachPhong;
    }

    public int timPhongTrong(int viTri) {
        if(viTri >= danhSachPhong.length) 
            return -1;
        if(danhSachPhong[viTri] == 0) 
            return viTri;
        return timPhongTrong(viTri + 1);
    }
}
