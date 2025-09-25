package vn.edu.quanlynhatro.controller.Abstract;

import vn.edu.quanlynhatro.controller.Interfaces.Handinterface;

public class Hand implements Handinterface {
    @Override
    public void moveHand() {
        System.out.println("Hand is moving");
    }
}
