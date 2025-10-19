# 🏠 ỨNG DỤNG QUẢN LÝ NHÀ TRỌ SINH VIÊN PHENIKAA

### 👨‍💻 Nhóm: N03_25_26_Tiến_Tú_Huy  
> Dự án môn **Lập trình hướng đối tượng – Đại học Phenikaa**

---

## 📋 Giới thiệu
**Ứng dụng Quản lý Nhà trọ Sinh viên Phenikaa** là hệ thống web được phát triển bằng **Spring Boot**, giúp **ban quản lý theo dõi và quản lý thông tin phòng trọ, sinh viên thuê trọ và nhân sự quản lý từng tòa nhà**.

---

## 🎯 Tính năng chính

### 🏠 Quản lý Phòng (Tú phụ trách)
- Thêm, sửa, xóa, tìm kiếm phòng  
- Quản lý số người hiện tại / tối đa trong phòng  
- Gán sinh viên vào phòng  
- Theo dõi trạng thái phòng (trống / đã thuê)  

### 👨‍🎓 Quản lý Sinh viên (Huy phụ trách)
- CRUD thông tin sinh viên  
- Tìm kiếm sinh viên theo MSSV  
- Quản lý thông tin cá nhân, lớp, ngành, quê quán  

### 👨‍💼 Quản lý Ban quản lý (Tiến phụ trách)
- CRUD nhân sự ban quản lý  
- Phân công quản lý theo tòa nhà  
- Quản lý thông tin liên hệ  

---
## 🛠 Công nghệ sử dụng
- **Spring Boot 3**
- **Thymeleaf**
- **MySQL (Aiven Cloud)**
- **JPA / Hibernate**
- **HTML, Tailwind CSS**
---
## 🗄 Kiến trúc cơ sở dữ liệu
### Các entity chính:
#### 🏠 Phong
String soPhong;
String toa;
double tienNha;
int soNguoiHienTai;
int soNguoiToiDa;
boolean trangThai;
#### 👨‍🎓 SinhVien
Long id;
String mssv;
String hoTen;
String lop;
String nganhHoc;
String queQuan;
####👨‍💼 BanQuanLy
Long id;
String chucVu;
String toaPhuTrach;
#### Nguoi(Lớp cha trừu tượng)
String hoTen;
String gioiTinh;
String cccd;
String soDienThoai;
LocalDate ngaySinh;
String diaChi;
#### 🧩 Yêu cầu hệ thống
Java 17+
MySQL 8.0+
Maven 3.6+
#### ⚙️ Cách chạy
// Clone repository

##### git clone https://github.com/TienLuckyy/OOP_N03_25_26_Tien_Tu_Huy.git

// Di chuyển vào thư mục dự án

##### cd OOP_N03_25_26_Tien_Tu_Huy

// Cấu hình database trong file application.properties

##### spring.datasource.url=jdbc:mysql://mysql-134e11f2-app-phenikaa.j.aivencloud.com:28575/defaultdb?useSSL=true&requireSSL=true&serverTimezone=UTC
##### spring.datasource.username=avnadmin
##### spring.datasource.password=AVNS_36Lxj4Cy2KHfvBdsLN6

# Chạy ứng dụng

mvn spring-boot:run


👥 Thành viên nhóm
| Họ tên             | Vai trò         | Phụ trách           |
| ------------------ | --------------- | ------------------- |
| **Dương Ngọc Tú**  | Lập trình chính | Quản lý Phòng       |
| **Nguyễn Văn Huy** | Thành viên      | Quản lý Sinh viên   |
| **Phạm Văn Tiến**  | Thành viên      | Quản lý Ban quản lý |

#### 📞 Liên hệ

GitHub nhóm: 👉 TienLuckyy/OOP_N03_25_26_Tien_Tu_Huy
Link Yt demo:
Link báo cáo: 

Trường Đại học Phenikaa – Khoa CNTT

Dự án phát triển phục vụ học tập môn Lập trình hướng đối tượng
Tú Bổ sung:
## Sơ đồ Class Diagram
<img width="247" height="1014" alt="image" src="https://github.com/user-attachments/assets/6772d3f3-1c30-4787-bf42-0e83e76f0f45" />

### Thêm phòng
<img width="927" height="855" alt="image" src="https://github.com/user-attachments/assets/1e2a57d8-e238-4533-ab4c-0dcf21a415c8" />

### Sửa
<img width="635" height="801" alt="image" src="https://github.com/user-attachments/assets/5ac9ee4c-7e46-45cf-919f-818352432f9a" />

### Xóa
<img width="910" height="563" alt="image" src="https://github.com/user-attachments/assets/0e85d785-5674-47a6-bda3-d83439a56a56" />

### Tìm
<img width="894" height="733" alt="image" src="https://github.com/user-attachments/assets/26e6bc8e-ea4f-431f-9d4b-287c4abdaaab" /> 

### Gán sinh viên vào Phòng
<img width="852" height="940" alt="image" src="https://github.com/user-attachments/assets/9f960119-1cfe-421a-b98d-66ce13bcf827" />

 



