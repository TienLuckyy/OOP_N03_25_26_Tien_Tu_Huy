package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.HopDong;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HopDongTest{
    public static void main(String[]args ){
        Calendar cal = Calendar.getInstance();

        cal.set(2025,Calendar.SEPTEMBER,1);
        Date ngayBatDau1 = cal.getTime();
        cal.set(2026,Calendar.AUGUST,31);
        Date ngayKetThuc1 = cal.getTime();

        cal.set(2025,Calendar.JANUARY,15);
        Date ngayBatDau2 = cal.getTime();
        cal.set(2027,Calendar.JULY,15);
        Date ngayKetThuc2 = cal.getTime();

        HopDong hd1 = new HopDong("HD001","SV001","P101", ngayBatDau1,ngayKetThuc1,2000000,"Dang hieu luc");
        HopDong hd2 = new HopDong("HD002","SV002","P102", ngayBatDau2,ngayKetThuc2,2500000,"Da het han");
        
        ArrayList<HopDong> listHD=new ArrayList<>();
        listHD.add(hd1);
        listHD.add(hd2);
    
        for (HopDong hd : listHD){
            System.out.println(hd.toString());
        }}
}
