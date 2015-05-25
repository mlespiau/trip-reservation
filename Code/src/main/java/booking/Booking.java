package booking;

import java.time.LocalDate;

public class Booking {
    private LocalDate date;
    private int customerCode;
    private int id;
    
    public Booking(int customerCode, LocalDate date) {
        this.customerCode = customerCode;
        this.date = date;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public int getCustomerCode() {
        return customerCode;
    }

    public int getId() {
        return id;
    }

    public static Booking create(int customerCode) {
        return new Booking(customerCode, LocalDate.now());
    }
}
