package model;

public class Ticket {
    private int id;
    private String eventName;
    private String holderName;
    private double price;
    private boolean isUsed;

    public Ticket(int id, String eventName, String holderName, double price) {
        this.id = id;
        this.eventName = eventName;
        this.holderName = holderName;
        this.price = price;
        this.isUsed = false;
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getPrice() {
        return price;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", holderName='" + holderName + '\'' +
                ", price=" + price +
                ", isUsed=" + isUsed +
                '}';
    }
}