# Đối tượng Phòng

## Sơ đồ Class Diagram
<img width="451" height="230" alt="image" src="https://github.com/user-attachments/assets/40885c8a-58ed-459a-be70-062510f4a285" />

## Thuộc tính
- soPhong: String  
- trangThai: boolean (true = đang sử dụng, false = trống)  
- tienDienNuoc: double  

## Chức năng CRUD
- **Create**: thêm phòng mới (mặc định trạng thái trống)  
- **Read**: tìm theo số phòng hoặc hiển thị danh sách phòng trống  
- **Update**: sửa thông tin (tiền điện nước, trạng thái)  
- **Delete**: xóa phòng theo số phòng  

## Activity Diagram

### Create
<img width="224" height="243" alt="image" src="https://github.com/user-attachments/assets/3a475d1a-2fa4-489e-9ed2-decf8d4ffdae" />

### Read
<img width="378" height="253" alt="image" src="https://github.com/user-attachments/assets/f1ca2d1c-9217-4420-adb1-a233236b6c4b" />

### Update
<img width="373" height="307" alt="image" src="https://github.com/user-attachments/assets/7dce3bf0-1baa-4597-801a-2b8d55fc1849" />

### Delete
<img width="280" height="307" alt="image" src="https://github.com/user-attachments/assets/135044b2-5880-4db5-ac73-e585d6b73171" />

## Ví dụ minh họa CRUD
- **Create**: Input → Số phòng = P101 → Output → Danh sách có P101 (trạng thái trống).  
- **Read**: Tìm P101 → Trả về thông tin phòng P101.  
- **Update**: Cập nhật tiền điện nước của P101 = 500.000 → Thay đổi thành công.  
- **Delete**: Xóa P101 → Danh sách không còn phòng P101.  
