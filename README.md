Tính năng chính 🎯
Dành cho Khách hàng (Customer):

       1. Đăng ký, đăng nhập/đăng xuất tài khoản.

       2. Tìm kiếm chuyến bay theo điểm đi, điểm đến và ngày.

        3.Thực hiện đặt vé và thanh toán.

        4.Xem lịch sử các vé đã đặt.

        5.Hủy vé.

Dành cho Quản trị viên (Admin):

    1.Quản lý chuyến bay (thêm, sửa, xóa).

    2.Quản lý vé và các lượt đặt chỗ.

    3.Quản lý tài khoản khách hàng.

    4.Xem thống kê và báo cáo doanh thu.
1. Nhóm Đối tượng Con người
User (Lớp Cha Trừu tượng) 
```
{
    - String userId;
    - String username;
    - String password;
    - String email;
    - String phone;
    + login()
    + logout()
    + updateProfile()
}
```
Admin kế thừa từ User 
```
{
    // Kế thừa các thuộc tính của User
    + manageFlights()
    + manageTickets()
    + manageCustomers()
    + viewReports()
}
```
Customer kế thừa từ User 
```
{
    // Kế thừa các thuộc tính của User
    - String name;
    - String passportNumber;
    + searchFlights()
    + bookTicket()
    + cancelTicket()
    + viewBookingHistory()
}
```
2. Nhóm Đối tượng Chuyến bay

Flight 
```
{
    - String flightId;
    - String airline;        // Hãng hàng không
    - String departure;      // Điểm đi
    - String destination;    // Điểm đến
    - LocalDateTime departureTime;
    - LocalDateTime arrivalTime;
    - int seatCapacity;      // Tổng số ghế
    - int availableSeats;    // Số ghế còn trống
    + updateFlightInfo()
    + checkAvailability()
}
```
3. Nhóm Đối tượng Giao dịch

Booking 
```
{
    - String bookingId;
    - LocalDateTime bookingDate;
    - BookingStatus status; // (Confirmed, Pending, Canceled)
    // Quan hệ: 1 Booking có thể chứa nhiều Ticket [cite: 27]
    - List<Ticket> tickets;
    + addTicket()
    + removeTicket()
    + confirmBooking()
}
```
Ticket 

```

{
    - String ticketId;
    - String seatNumber;   // Số ghế
    - double price;        // Giá vé
    - TicketStatus status;
    + cancel()
    + confirm()
}
```
Payment 
```
{
    - String paymentId;
    - double amount;
    - String method;       // (Credit Card, PayPal, Cash)
    - PaymentStatus status;
    + processPayment()
    + refund()
}
```