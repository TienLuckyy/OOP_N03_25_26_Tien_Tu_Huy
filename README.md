# Đối tượng Phòng

## Sơ đồ Class Diagram
<img width="300" height="300" alt="image" src="https://github.com/user-attachments/assets/8fe2b891-0bb0-430d-9c46-ff0c68ccf414" />

## Thuộc tính
- mssv: String
- ten: String
- queQuan: String
- ngaySinh: Date
- gioiTinh: String
- sdt: String
- cccd: String
- lop: String
- nganhHoc: String
## Chức năng CRUD
- **Create**: Thêm mới sinh viên với thông tin đầy đủ.
- **Read**: Lấy danh sách tất cả sinh viên hoặc tìm kiếm theo MSSV.
- **Update**: Chỉnh sửa thông tin sinh viên (chỉ cập nhật các trường có dữ liệu mới).
- **Delete**: Xóa sinh viên theo MSSV.
## Activity Diagram

### Create
<img width="300" height="500" alt="image" src="https://github.com/user-attachments/assets/5d06140d-2c05-4538-9668-96e4684dc5a0" />

### Read
<img width="300" height="300" alt="image" src="https://github.com/user-attachments/assets/55429d0b-5a3e-486b-92c8-bda9806a2a14" />

### Update
<img width="300" height="500" alt="image" src="https://github.com/user-attachments/assets/ef801fbe-f503-44b2-8713-bcab906a223c" />

### Delete
<img width="300" height="300" alt="image" src="https://github.com/user-attachments/assets/18cc43a6-ebe4-4ee3-9b74-475b88b34e73" />

## Ví dụ minh họa CRUD
- **Create**: Input → Số phòng = P101 → Output → Danh sách có P101 (trạng thái trống).  
- **Read**: Tìm P101 → Trả về thông tin phòng P101.  
- **Update**: Cập nhật tiền điện nước của P101 = 500.000 → Thay đổi thành công.  
- **Delete**: Xóa P101 → Danh sách không còn phòng P101.  



