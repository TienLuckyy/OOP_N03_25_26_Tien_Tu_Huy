// package vn.edu.quanlynhatro.service;

// import vn.edu.quanlynhatro.util.FileUtil;
// import vn.edu.quanlynhatro.model.BanQuanLy;

// import java.util.ArrayList;
// import java.util.List;


// public class BQLService {

    
//     private List<BanQuanLy> danhSachBQL;
    
//     private static final String FILE_NAME = "data/banquanly.dat";

    
//     @SuppressWarnings("unchecked")
//     public BQLService() {
//         this.danhSachBQL = (List<BanQuanLy>) FileUtil.docFile(FILE_NAME);
//         if (this.danhSachBQL == null) {
//             this.danhSachBQL = new ArrayList<>();
//         }
//     }

    
//     public List<BanQuanLy> getAllBQL() {
//         return this.danhSachBQL;
//     }

    
//     public void themBQL(BanQuanLy bql) {
        
//         boolean isExist = danhSachBQL.stream().anyMatch(member -> member.getCccd().equals(bql.getCccd()));
//         if (isExist) {
//             System.out.println("Lỗi: CCCD " + bql.getCccd() + " đã tồn tại!");
//             return;
//         }
        
//         danhSachBQL.add(bql);
//         luuFile();
//         System.out.println("Thêm thành viên BQL thành công.");
//     }


//     public boolean suaBQL(BanQuanLy bqlDaSua) {
//         for (int i = 0; i < danhSachBQL.size(); i++) {
//             if (danhSachBQL.get(i).getCccd().equals(bqlDaSua.getCccd())) {
//                 danhSachBQL.set(i, bqlDaSua); 
//                 luuFile();
//                 System.out.println("Cập nhật thông tin thành viên BQL thành công.");
//                 return true;
//             }
//         }
//         System.out.println("Lỗi: Không tìm thấy thành viên BQL có CCCD " + bqlDaSua.getCccd());
//         return false;
//     }


//     public boolean xoaBQL(String cccd) {
//         boolean isRemoved = danhSachBQL.removeIf(bql -> bql.getCccd().equals(cccd));
//         if (isRemoved) {
//             luuFile();
//             System.out.println("Xóa thành viên BQL thành công.");
//             return true;
//         } else {
//             System.out.println("Lỗi: Không tìm thấy thành viên BQL có CCCD " + cccd);
//             return false;
//         }
//     }

   
//     public BanQuanLy timKiemTheoCccd(String cccd) {
//         return danhSachBQL.stream()
//                 .filter(bql -> bql.getCccd().equals(cccd))
//                 .findFirst()
//                 .orElse(null);
//     }

   
//     private void luuFile() {
//         FileUtil.ghiFile(FILE_NAME, this.danhSachBQL);
//     }
// }
