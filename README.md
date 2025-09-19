# Đối tượng Sinh Viên

## Sơ đồ Class Diagram
<img width="334" height="700" alt="image" src="https://github.com/user-attachments/assets/cbedc905-7e75-4fe1-8cf5-746cb856e6c1" />


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
- **Create**: Input → MSSV = "SV001", Tên = "Nguyễn Văn A" → Output → Danh sách chứa sinh viên SV001. 
- **Read**: Tìm "SV001" → Trả về thông tin của sinh viên Nguyễn Văn A.
- **Update**: Cập nhật SĐT của SV001 = "090xxxxxxx" → Thay đổi thành công.
- **Delete**: Xóa "SV001" → Danh sách không còn sinh viên SV001.




