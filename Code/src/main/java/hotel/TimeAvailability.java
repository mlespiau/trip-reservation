package hotel;

import java.time.LocalDate;

public class TimeAvailability {
    private int id;
    private LocalDate from;
    private LocalDate to;
    private boolean isBooked;
    
    public TimeAvailability(LocalDate from, LocalDate to, boolean isBooked) {
        this.from = from;
        this.to = to;
        this.isBooked = isBooked;
    }
    
    public static TimeAvailability create(LocalDate from, LocalDate to) {
        return new TimeAvailability(from, to, false);
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
