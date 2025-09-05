public class Time {
    private int gio;
    private int phut;
    private int giay;

    public Time(int gio, int phut, int giay) {
        setGio(gio);
        setPhut(phut);
        setGiay(giay);
    }

    public int getGio() {
        return gio;
    }

    public int getPhut() {
        return phut;
    }

    public int getGiay() {
        return giay;
    }

    public void setGio(int gio) {
        if(gio >= 0 && gio < 24) {
            this.gio = gio;
        }else {
            this.gio = 0;
        }
    }

    public void setPhut(int phut) {
        if(phut >= 0 && phut < 60) {
            this.phut = phut;
        }else {
            this.phut = 0;
        }
    }

    public void setGiay(int giay) {
        if(giay >= 0 && giay < 60) {
            this.giay = giay;
        }else {
            this.giay = 0;
        }
    }

    public void hienThi() {
        System.out.printf("%02d:%02d:%02d\n", gio, phut, giay);
    }
}
