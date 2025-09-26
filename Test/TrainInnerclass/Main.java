public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Honda Toyota");
        Car.Engine myEngine = myCar.new Engine();
        myEngine.start();
        myCar.drive();
    }
}