package vn.edu.quanlynhatro.baitap06;

public class Caller {
    public void go(Callback callback) {
        for (int i = 1; i <= 10; i++) {
            callback.onCall(i);
        }
    }
}
