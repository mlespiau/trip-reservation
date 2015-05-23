package hotel;

import java.time.LocalDate;

public class TimeSlot {
    private int id;
    private LocalDate from;
    private LocalDate to;
    private boolean isBooked;
    
    public TimeSlot(LocalDate from, LocalDate to, boolean isBooked) {
        this.from = from;
        this.to = to;
        this.isBooked = isBooked;
    }
    
    public static TimeSlot create(LocalDate from, LocalDate to) {
        return new TimeSlot(from, to, false);
    }

    public int getId() {
        return id;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public boolean isBooked() {
        return isBooked;
    }
}
