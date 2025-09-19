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
<img width="333" height="297" alt="image" src="https://github.com/user-attachments/assets/348b1a09-19a6-465d-b0d2-528af00a2f76" />


### Read
<img width="905" height="455" alt="image" src="https://github.com/user-attachments/assets/78bba150-6019-4dd6-891f-9824cc9bca95" />


### Update
<img width="563" height="415" alt="image" src="https://github.com/user-attachments/assets/9a11e03a-dacd-4e05-9753-03369c737e1e" />

### Delete
![Uploading image.png…]()


## Ví dụ minh họa CRUD
- **Create**: Input → Số phòng = P101 → Output → Danh sách có P101 (trạng thái trống).  
- **Read**: Tìm P101 → Trả về thông tin phòng P101.  
- **Update**: Cập nhật tiền điện nước của P101 = 500.000 → Thay đổi thành công.  
- **Delete**: Xóa P101 → Danh sách không còn phòng P101.  



