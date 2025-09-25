public class Callee extends MyIncrement{
    private int i = 0;
    public void incr() {
        i++;
        System.out.println("Callee i incremented to " + i);
    }
    private class Closure implements Incrementable {
        @Override
        public void increment() {
            Callee.this.incr();
        }
    }
    Incrementable getCallbackReference() {
        return new Closure();
    }
    
}
