package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.controller.call.Caller;
import vn.edu.quanlynhatro.controller.call.Callee;

public class TestCallback {
    public static void main(String[] args) {
        Callee callee = new Callee();
        
        Caller caller = new Caller(callee);
       
        caller.go();  
    }
}
