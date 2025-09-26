public class Callee implements Incrementable {
    private int i = 0;

    public void increment() {
        i++;
        System.out.println("Callee increments i to " + i);
    }
}