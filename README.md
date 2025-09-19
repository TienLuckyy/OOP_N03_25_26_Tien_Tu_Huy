# Đối tượng Ban Quản Lý

## Sơ đồ Class Diagram
<img width="334" height="700" alt="image" src="https://github.com/user-attachments/assets/cbedc905-7e75-4fe1-8cf5-746cb856e6c1" />


## Thuộc tính
- cccd: String
- soDienThoai: String
- gioiTinh: String
- hoTen: String
- ngaySinh: Date
- diaChi: String
## Chức năng CRUD
- **Create**: Thêm thành viên vào BQL với thông tin đầy đủ.
- **Read**: Hiển thị toàn bộ danh sách BQL và tìm kiếm theo MSSV.
- **Update**: Chỉnh sửa thông tin thành viên BQL (chỉ cập nhật các trường có dữ liệu mới).
- **Delete**: Xóa thành viên BQL theo CCCD.
## Activity Diagram

### Create
<img width="433" height="385" alt="addBQL" src="https://github.com/user-attachments/assets/2e83c70e-70c0-44d2-9bba-18401971311b" />

### Read
<img width="472" height="277" alt="readBQL" src="https://github.com/user-attachments/assets/6995f967-96bc-404f-b0db-461ea42a9cb0" />

### Update
<img width="520" height="493" alt="updateBQL" src="https://github.com/user-attachments/assets/d2ab402a-0ea5-404e-b825-229dbc2e2f2b" />

### Delete
<img width="485" height="385" alt="dltBQL" src="https://github.com/user-attachments/assets/b7c727e1-9fb6-493f-afc6-edff9b98bc81" />

## Ví dụ minh họa CRUD
- **Create**: Input → Họ tên = "Nguyễn Văn A", CCCD = "0123456789"
Output → Danh sách Ban Quản Lý có thêm "Nguyễn Văn A".
- **Read**: Input → Tìm theo CCCD = "0123456789"
Output → Trả về thông tin của "Nguyễn Văn A".
- **Update**: Input → Cập nhật số điện thoại của "Nguyễn Văn A" thành "0909123456"
Output → Thay đổi thành công, thông tin mới được lưu.
- **Delete**: Input → Xóa thành viên có CCCD = "0123456789"
Output → Danh sách không còn "Nguyễn Văn A".





