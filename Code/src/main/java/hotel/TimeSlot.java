package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import spark.QueryParamsMap;

public class TimeSlot {
    private int id;
    private LocalDate from;
    private LocalDate to;
    
    public TimeSlot(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }
    
    public static TimeSlot create(LocalDate from, LocalDate to) {
        return new TimeSlot(from, to);
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

    public static TimeSlot fromQueryParams(QueryParamsMap queryMap) {
        return TimeSlot.create(
            LocalDate.parse(queryMap.get("availableFrom").value(), DateTimeFormatter.ISO_LOCAL_DATE),
            LocalDate.parse(queryMap.get("availableTo").value(), DateTimeFormatter.ISO_LOCAL_DATE)
        );
    }

    public void setId(int id) {
        this.id = id;
    }
}
