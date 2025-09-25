package vn.edu.quanlynhatro.controller.call;

public class Callee implements Incrementable {
    @Override
    public void increment() {
        System.out.println("Other operation from Callee");
    }
}
