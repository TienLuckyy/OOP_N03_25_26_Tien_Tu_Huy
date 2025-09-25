public class Caller {
    private Incrementable callbackReference;

    public Caller(Incrementable cbr) {
        callbackReference = cbr;
    }
    void go() {
        callbackReference.increment();
    }
}
