package vn.edu.quanlynhatro.controller.Abstract;

import vn.edu.quanlynhatro.controller.Interfaces.Leginterface;

public class Leg implements Leginterface {
    @Override
    public void moveLeg() {
        System.out.println("Leg is moving");
    }
}
