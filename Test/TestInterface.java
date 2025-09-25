public class TestInterface {
    public interface Animal {
        public void animalSound();
    }
    class Dog implements Animal {
        @Override
        public void animalSound() {
            System.out.println("gau gau");
        }
    }
    public interface Hand {
        public void hand();
    }
    public interface Leg {
        public void leg();
    }
    class ConCho implements Hand, Leg {
        @Override
        public void hand() {
            System.out.println("con cho co 2 tay");
        }
        @Override
        public void leg() {
            System.out.println("Con cho di bang 4 chan");
        }
    }
    public static void main(String[] args) {
        Animal dog = new TestInterface().new Dog();
        dog.animalSound();
        Hand hand = new TestInterface().new ConCho();
        Leg leg = new TestInterface().new ConCho();
        hand.hand();
        leg.leg();
    }
}
