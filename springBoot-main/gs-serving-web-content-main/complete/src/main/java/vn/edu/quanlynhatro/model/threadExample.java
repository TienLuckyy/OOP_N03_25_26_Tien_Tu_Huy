package vn.edu.quanlynhatro.model;

public class threadExample extends Thread{
public static int count = 0;
public static void main(String[] args){
threadExample threadExp =new threadExample();
threadExp.start();
System.out.println("count "+count);//0
count ++;
System.out.println("count after ++"+count);
}
public void run(){
count ++;
}
}