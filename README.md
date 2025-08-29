# 🏫 Ứng dụng Quản lý Ký túc xá

## 📌 Giới thiệu
Đây là dự án **OOP** được xây dựng bằng **Java Spring Boot**, nhằm hỗ trợ công tác quản lý ký túc xá sinh viên.  
Hệ thống cung cấp các chức năng quản lý sinh viên, phòng ở, tài khoản và ban quản lý, đồng thời hỗ trợ các nghiệp vụ như phân phòng, chuyển phòng và thanh toán điện nước.  

---

## 🚀 Công nghệ sử dụng
- **Ngôn ngữ:** Java 11+  
- **Framework:** Spring Boot  
- **Cấu trúc dữ liệu:** Java Collections (ArrayList, HashMap)  
- **Lưu trữ dữ liệu:** File nhị phân (`.dat`, `.bin`)  

---

## ⚙️ Chức năng chính

### 1. Quản lý Sinh viên
- Thêm, sửa, xóa sinh viên  
- Lọc & tìm kiếm theo ngành học, lớp, quê quán  
- Nghiệp vụ:
  - Vào ở (gán sinh viên vào phòng)  
  - Chuyển ra (rời khỏi phòng)  
  - Thanh toán tiền điện nước  

### 2. Quản lý Phòng
- Thêm, sửa, xóa phòng  
- Quản lý trạng thái (trống/đang sử dụng)  
- Quản lý tiền điện nước  
- Gán sinh viên vào phòng  

### 3. Quản lý Tài khoản
- Đăng ký & đăng nhập hệ thống  
- Quản lý tài khoản (email, mật khẩu)  

### 4. Quản lý Ban Quản lý (BQL)
- Thêm, sửa, xóa thông tin BQL  
- Quyền hạn: quản lý sinh viên, phân phòng, xử lý thanh toán  

---

## 📂 Cấu trúc dữ liệu & lưu trữ
- Dữ liệu được lưu trữ trong file nhị phân  
- Các lớp chính:  
  - `SinhVien`  
  - `Phong`  
  - `TaiKhoan`  
  - `BanQuanLy`  

---

## 🌟 Tính năng mở rộng (tùy chọn)
- Thống kê số lượng sinh viên theo ngành, lớp, phòng  
- Tìm kiếm sinh viên theo tên, MSSV, quê quán  
- Quản lý lịch sử chuyển phòng  
- Gửi thông báo email cho sinh viên  
- Mã hóa mật khẩu khi lưu file  

---

## 📖 Kịch bản hệ thống (Use-case)
1. Sinh viên đăng nhập  
2. Ban quản lý thêm mới sinh viên  
3. Sinh viên thanh toán tiền phòng  

---

## 🛠️ Cách cài đặt & chạy dự án

```bash
# Clone project
git clone https://github.com/<your-username>/<your-repo>.git

# Mở bằng IDE (IntelliJ, Eclipse, VS Code) và chạy project Spring Boot
