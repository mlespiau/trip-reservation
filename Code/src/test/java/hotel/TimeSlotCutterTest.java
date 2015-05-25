package hotel;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TimeSlotCutterTest {
    private TimeSlotCutter cutter;
    
    @Before
    public void setUp() {
        cutter = new TimeSlotCutter();
    }
    
    @After
    public void tearDown() {
        cutter = null;
    }

    @Test
    public void cutInTreeWhenTimeSlotIsGreaterThanBothDates() {
        TimeSlot timeSlotToCut = this.createTimeSlot(this.createDate("2015-01-01"), this.createDate("2015-01-10"));
        List<TimeSlot> cutByDates = cutter.cutByDates(timeSlotToCut, this.createDate("2015-01-03"), this.createDate("2015-01-06"));
        assertEquals(3, cutByDates.size());
        assertEquals("2015-01-01", cutByDates.get(0).getFrom().toString());
        assertEquals("2015-01-02", cutByDates.get(0).getTo().toString());
        assertEquals("2015-01-03", cutByDates.get(1).getFrom().toString());
        assertEquals("2015-01-06", cutByDates.get(1).getTo().toString());
        assertEquals("2015-01-06", cutByDates.get(2).getFrom().toString());
        assertEquals("2015-01-10", cutByDates.get(2).getTo().toString());
    }
    
    @Test
    public void cutInTwoWhenTimeSlotFromIsEqualToCheckIn() {
        LocalDate date = this.createDate("2015-01-01");
        TimeSlot timeSlotToCut = this.createTimeSlot(date, this.createDate("2015-01-10"));
        List<TimeSlot> cutByDates = cutter.cutByDates(timeSlotToCut, date, this.createDate("2015-01-06"));
        assertEquals(2, cutByDates.size());
        assertEquals("2015-01-01", cutByDates.get(0).getFrom().toString());
        assertEquals("2015-01-06", cutByDates.get(0).getTo().toString());
        assertEquals("2015-01-06", cutByDates.get(1).getFrom().toString());
        assertEquals("2015-01-10", cutByDates.get(1).getTo().toString());
    }
    
    @Test
    public void cutInTwoWhenTimeSlotToIsEqualToCheckOut() {
        LocalDate date = this.createDate("2015-01-10");
        TimeSlot timeSlotToCut = this.createTimeSlot(this.createDate("2015-01-01"), date);
        List<TimeSlot> cutByDates = cutter.cutByDates(timeSlotToCut, this.createDate("2015-01-03"), date);
        assertEquals(2, cutByDates.size());
        assertEquals("2015-01-01", cutByDates.get(0).getFrom().toString());
        assertEquals("2015-01-02", cutByDates.get(0).getTo().toString());
        assertEquals("2015-01-03", cutByDates.get(1).getFrom().toString());
        assertEquals("2015-01-10", cutByDates.get(1).getTo().toString());
    }
    
    @Test
    public void dontCutWhenFromIsEqualToCheckingAndToIsEqualToCheckOut() {
        LocalDate checkIn = this.createDate("2015-01-01");
        LocalDate checkOut = this.createDate("2015-01-10");
        TimeSlot timeSlotToCut = this.createTimeSlot(checkIn, checkOut);
        List<TimeSlot> cutByDates = cutter.cutByDates(timeSlotToCut, checkIn, checkOut);
        assertEquals(1, cutByDates.size());
        assertEquals("2015-01-01", cutByDates.get(0).getFrom().toString());
        assertEquals("2015-01-10", cutByDates.get(0).getTo().toString());
    }
    
    private TimeSlot createTimeSlot(LocalDate from, LocalDate to) {
        return TimeSlot.create(from, to);
    }
    
    private LocalDate createDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
