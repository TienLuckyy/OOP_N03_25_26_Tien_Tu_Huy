package util;

public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // Phương thức tính thời gian
    public void addSeconds(int secondsToAdd) {
        this.second += secondsToAdd;
        if (this.second >= 60) {
            this.minute += this.second / 60;
            this.second %= 60;
        }
        if (this.minute >= 60) {
            this.hour += this.minute / 60;
            this.minute %= 60;
        }
        this.hour %= 24;
    }
    
    public void displayTime() {
        System.out.printf("%02d:%02d:%02d\n", this.hour, this.minute, this.second);
    }
}