public class Car {
    private String model;
    public Car(String model) {
        this.model = model;
    }
    public class Engine {
        public void start() {
            System.out.println("Day la public inner class: xe " + model + " đã khoi dong!!");
        }
    }
    private class heThongDanhLua {
        public void canGat() {
            System.out.println("Day la private inner class: He thong đanh lua hoat dong.");
        }
    }
     public void drive() {
        System.out.println("Bat dau lai chiec " + model + "brwm brwm");
        heThongDanhLua danhLua = new heThongDanhLua();
        danhLua.canGat();
        System.out.println("Xe dang chay bon bon tren duong");
    }
}

