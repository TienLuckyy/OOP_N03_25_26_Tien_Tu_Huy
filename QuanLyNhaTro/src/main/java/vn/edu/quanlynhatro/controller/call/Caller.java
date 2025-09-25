package vn.edu.quanlynhatro.controller.call;

public class Caller {
    private Incrementable callbackReference;

    public Caller(Incrementable cbr) {
        this.callbackReference = cbr;
    }

    public void go() {
        callbackReference.increment();
    }
}
