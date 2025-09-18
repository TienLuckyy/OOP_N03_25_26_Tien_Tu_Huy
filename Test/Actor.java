public abstract class Actor {
    abstract void act();
}
class HappyActor extends Actor {
    public void act(){
        System.out.println("Happy Actor");
    }
}
class SadActor extends Actor {
    public void act(){
        System.out.println("Sad Actor");
    }
}
class Stage {
    Actor a = new HappyActor();
    void change(){
        a = new SadActor();
    }
    void go(){
        a.act();
    }
}
