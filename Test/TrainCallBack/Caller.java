public class Caller {
    private Incrementable callbackReference;

    Caller(Incrementable cbr) {
        this.callbackReference = cbr;
    }

    void go() {
        callbackReference.increment();
    }
}