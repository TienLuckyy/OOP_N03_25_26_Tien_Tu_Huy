# Đối tượng Phòng

## Sơ đồ Class Diagram

<img width="334" height="831" alt="image" src="https://github.com/user-attachments/assets/7343c0ae-5dc2-4c13-bd71-786c76740d26" />

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
<img width="329" height="335" alt="image" src="https://github.com/user-attachments/assets/38b7f90f-43c4-475e-b81d-d83c9f92ac8a" />


### Read
<img width="626" height="371" alt="image" src="https://github.com/user-attachments/assets/de415a3a-f4e5-4c0e-b63c-d8e471cad69a" />


### Update
<img width="324" height="321" alt="image" src="https://github.com/user-attachments/assets/b30b9420-eaa2-4d98-82d1-be4403474625" />

### Delete
<img width="321" height="253" alt="image" src="https://github.com/user-attachments/assets/a21bfb76-5c0d-4cf7-9120-2c6f509b4fa5" />


## Ví dụ minh họa CRUD
- **Create**: Input → Số phòng = P101 → Output → Danh sách có P101 (trạng thái trống).  
- **Read**: Tìm P101 → Trả về thông tin phòng P101.  
- **Update**: Cập nhật tiền điện nước của P101 = 500.000 → Thay đổi thành công.  
- **Delete**: Xóa P101 → Danh sách không còn phòng P101.  


