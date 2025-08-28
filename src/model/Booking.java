package model;

public class Booking {
    private int bookingId;
    private Ticket ticket;
    private User user;

    public Booking(int bookingId, Ticket ticket, User user) {
        this.bookingId = bookingId;
        this.ticket = ticket;
        this.user = user;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", ticket=" + ticket +
                ", user=" + user +
                '}';
    }
}
