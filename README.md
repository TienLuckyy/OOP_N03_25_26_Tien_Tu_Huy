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
| Thành phần | Công nghệ |
|-------------|------------|
| **Ngôn ngữ** | Java 17 |
| **Framework Backend** | Spring Boot 3 |
| **Frontend** | Thymeleaf, HTML5, CSS3 (Bootstrap 5) |
| **Database** | MySQL (Aiven Cloud) |
| **ORM** | JPA / Hibernate |
| **Build Tool** | Maven |
| **IDE** | IntelliJ IDEA |

---

## 🗄 Kiến trúc cơ sở dữ liệu
### Các entity chính:
#### 🏠 Phong
```java
String soPhong;
String toa;
double tienNha;
int soNguoiHienTai;
int soNguoiToiDa;
boolean trangThai;
