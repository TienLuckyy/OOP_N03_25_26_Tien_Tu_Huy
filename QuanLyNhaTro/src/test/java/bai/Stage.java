package bai;

public class Stage {
    Actor a=new SadActor();
    public void change(){
        a=new HappyActor();
    }
    public void go(){
        a.act();
    }
}
