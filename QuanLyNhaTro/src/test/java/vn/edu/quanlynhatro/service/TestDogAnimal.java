package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.Dog;
import vn.edu.quanlynhatro.controller.Abstract.Hand;
import vn.edu.quanlynhatro.controller.Abstract.Leg;
import vn.edu.quanlynhatro.controller.Interfaces.Animal;
import vn.edu.quanlynhatro.controller.Interfaces.Handinterface;
import vn.edu.quanlynhatro.controller.Interfaces.Leginterface;

public class TestDogAnimal {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        myDog.animalSound();

        Handinterface hand = new Hand();
        hand.moveHand();

        Leginterface leg = new Leg();
        leg.moveLeg();
    }
}
