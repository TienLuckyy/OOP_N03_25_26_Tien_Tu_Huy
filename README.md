# Đối tượng Phòng

## Sơ đồ Class Diagram

[<img width="451" height="230" alt="image" src="https://github.com/user-attachments/assets/40885c8a-58ed-459a-be70-062510f4a285" />
](https://private-user-images.githubusercontent.com/212072504/491518480-dba6f49e-696c-42b7-ad98-e5d78fc77492.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTgyNzcwOTIsIm5iZiI6MTc1ODI3Njc5MiwicGF0aCI6Ii8yMTIwNzI1MDQvNDkxNTE4NDgwLWRiYTZmNDllLTY5NmMtNDJiNy1hZDk4LWU1ZDc4ZmM3NzQ5Mi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwOTE5JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDkxOVQxMDEzMTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT02NjgyNGI3ZmI2YmFlODgyNWIxZDU4Njk2YzA3ZmY3YzM0NzJmYzA2MDZlNDAxNzU5NGM5ODQyMjMxZGI4YjNmJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.7ch6dj9CVRDQeozOdR58aBaptBjzy8yW1sbeegHLRwc)
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

