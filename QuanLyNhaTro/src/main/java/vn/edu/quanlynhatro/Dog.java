package vn.edu.quanlynhatro;

import vn.edu.quanlynhatro.controller.Interfaces.Animal;

public class Dog extends Animal {
    @Override
    public void animalSound() {
        System.out.println("Dog is barking");
    }
}
