
public class Time {
    private int hour;
    private int minute;
    private int second;

    // Hàm khởi tạo để thiết lập thời gian ban đầu
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // Phương thức để cộng thêm giây vào thời gian hiện tại
    public void addSeconds(int secondsToAdd) {
        this.second += secondsToAdd;

        // Xử lý khi giây vượt quá 60
        if (this.second >= 60) {
            int minutesToAdd = this.second / 60;
            this.second %= 60; // Lấy phần dư
            this.minute += minutesToAdd;
        }

        // Xử lý khi phút vượt quá 60
        if (this.minute >= 60) {
            int hoursToAdd = this.minute / 60;
            this.minute %= 60;
            this.hour += hoursToAdd;
        }

        // Xử lý khi giờ vượt quá 24 (quay về 0)
        this.hour %= 24;
    }

    // Phương thức để hiển thị thời gian
    public void displayTime() {
        // String.format để đảm bảo các số luôn có 2 chữ số (vd: 09, 05)
        System.out.printf("%02d:%02d:%02d\n", this.hour, this.minute, this.second);
    }
}