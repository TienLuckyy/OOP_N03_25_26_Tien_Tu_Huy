# ỨNG DỤNG QUẢN LÝ NHÀ TRỌ CHO SINH VIÊN PHENIKAA

## 👥 Thành viên nhóm
- TÚ – Quản lý Phòng (Phong)
- HUY HUY – Quản lý Sinh viên (SinhVien)
- TIẾN – Quản lý Ban Quản Lý (BanQuanLy)
- 
### 🧱 Mô tả đối tượng
- **Phong:** Quản lý thông tin phòng trọ (số phòng, tòa, số người, trạng thái, tiền nhà, v.v.)
- **SinhVien:** Quản lý thông tin sinh viên thuê trọ (mssv, tên, lớp, ngành học,quê quán,sđt,lop,v.v.)
- **BanQuanLy:** Quản lý thông tin nhân viên ban quản lý khu trọ (cccd, tên, sô điện thoại, giới tính,ngày sinh, v.v. )

Mục tiêu là hỗ trợ Ban quản lý theo dõi phòng, sinh viên và nhân sự quản lý.
Ứng dụng cung cấp các chức năng chính:
- Quản lý phòng trọ (thêm, sửa, xóa, tìm kiếm, xuất file)
- Quản lý sinh viên (thêm, sửa, xóa, tìm kiếm, xuất file)
- Quản lý ban quản lý (thêm, sửa, xóa, tìm kiếm, xuất file)

## 🛠️ Công nghệ sử dụng
- **Spring Boot 3**
- **Thymeleaf**
- **MySQL (Aiven Cloud)**
- **JPA / Hibernate**
- **HTML, Tailwind CSS**

## Sơ đồ Class Diagram
<img width="334" height="700" alt="image" src="https://github.com/user-attachments/assets/cbedc905-7e75-4fe1-8cf5-746cb856e6c1" />

## Activity diagram

## TÚ-PHÒNG

### Create 
<img width="380" height="577" alt="image" src="https://github.com/user-attachments/assets/e442a7d4-09a0-4223-80bb-5113bd496c7e" />

### Read 
<img width="267" height="405" alt="image" src="https://github.com/user-attachments/assets/4d92d237-2c3f-4bc4-b05e-1dd40e97e23a" />

### Update 
<img width="267" height="567" alt="image" src="https://github.com/user-attachments/assets/be907bdf-31c5-4661-8bd2-ea0b7c6da177" />

### Delete 
<img width="337" height="469" alt="image" src="https://github.com/user-attachments/assets/c727e2d5-797c-4a1b-9a2c-3386e9d0d5cd" />

## Class Diagram cho Phong
<img width="321" height="727" alt="image" src="https://github.com/user-attachments/assets/fbda7e2a-837b-4754-98c4-29826a204631" />

## HUY-SINHVIEN

### Create: Thêm mới sinh viên với thông tin đầy đủ.
<img width="300" height="500" alt="image" src="https://github.com/user-attachments/assets/5d06140d-2c05-4538-9668-96e4684dc5a0" />

### Read : Lấy danh sách tất cả sinh viên hoặc tìm kiếm theo MSSV.
<img width="300" height="400" alt="image" src="https://github.com/user-attachments/assets/55429d0b-5a3e-486b-92c8-bda9806a2a14" />

### Update: Chỉnh sửa thông tin sinh viên (chỉ cập nhật các trường có dữ liệu mới).
<img width="300" height="500" alt="image" src="https://github.com/user-attachments/assets/ef801fbe-f503-44b2-8713-bcab906a223c" />

### Delete : Xóa sinh viên theo MSSV.
<img width="300" height="400" alt="image" src="https://github.com/user-attachments/assets/18cc43a6-ebe4-4ee3-9b74-475b88b34e73" />

## TIẾN-BANQUANLY

### Create:Thêm thành viên vào BQL với thông tin đầy đủ.
<img width="433" height="385" alt="addBQL" src="https://github.com/user-attachments/assets/2e83c70e-70c0-44d2-9bba-18401971311b" />

### Read:Hiển thị toàn bộ danh sách BQL và tìm kiếm theo MSSV.
<img width="472" height="277" alt="readBQL" src="https://github.com/user-attachments/assets/6995f967-96bc-404f-b0db-461ea42a9cb0" />

### Update : Chỉnh sửa thông tin thành viên BQL (chỉ cập nhật các trường có dữ liệu mới).
<img width="520" height="493" alt="updateBQL" src="https://github.com/user-attachments/assets/d2ab402a-0ea5-404e-b825-229dbc2e2f2b" />

### Delete: Xóa thành viên BQL theo CCCD.
<img width="485" height="385" alt="dltBQL" src="https://github.com/user-attachments/assets/b7c727e1-9fb6-493f-afc6-edff9b98bc81" />

---
## 📎 Liên kết nộp bài
- **Repo GitHub nhóm:** https://github.com/TienLuckyy/OOP_N03_25_26_Tien_Tu_Huy

  


