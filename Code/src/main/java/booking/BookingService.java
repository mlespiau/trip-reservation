package booking;

import framework.DaoException;
import framework.Database;
import hotel.RoomTimeSlot;
import hotel.RoomTimeSlotService;
import hotel.TimeSlot;
import hotel.TimeSlotCutter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.jooq.exception.DataAccessException;
import org.jooq.types.UInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import security.Customer;
import test.generated.Tables;
import test.generated.tables.records.BookingRecord;

public class BookingService {
    Logger logger = LoggerFactory.getLogger(getClass()); 
    private TimeSlotCutter timeSlotCutter;
    private RoomTimeSlotService roomTimeSlotService;
    
    public BookingService(TimeSlotCutter timeSlotCutter, RoomTimeSlotService roomTimeSlotService) {
        this.timeSlotCutter = timeSlotCutter;
        this.roomTimeSlotService = roomTimeSlotService;
    }

    public RoomTimeSlot book(Customer customer, RoomTimeSlot roomTimeSlot,
            LocalDate checkIn, LocalDate checkOut) {
        List<TimeSlot> timeSlotPieces = this.timeSlotCutter.cutByDates(roomTimeSlot.getTimeSlot(), checkIn, checkOut);
        RoomTimeSlot bookedRoomTimeSlot = null;
        for (TimeSlot timeSlot : timeSlotPieces) {
            RoomTimeSlot newRoomTimeSlot = new RoomTimeSlot(roomTimeSlot.getRoom(), timeSlot);
            if (this.isBookedPart(timeSlot, checkIn, checkOut)) {
                Booking booking = this.saveNew(Booking.create(customer.getCode()));
                newRoomTimeSlot.setBooking(booking);
                bookedRoomTimeSlot = newRoomTimeSlot;
            }
            this.roomTimeSlotService.saveNew(newRoomTimeSlot);
        }
        this.roomTimeSlotService.delete(roomTimeSlot);
        return bookedRoomTimeSlot;
    }
    
    private Booking saveNew(Booking booking) {
        BookingRecord bookingRecord = Database.getInstance().getDslContext().newRecord(Tables.BOOKING);
        bookingRecord.setCustomercode(UInteger.valueOf(booking.getCustomerCode()));
        bookingRecord.setDate(Date.valueOf(booking.getDate()));
        try {
            bookingRecord.store();
        } catch (DataAccessException e) {
            logger.info("errorSavingHotel", e);
            throw new DaoException(e);
        }
        booking.setId(bookingRecord.getId().intValue());
        return booking;
    }
    
    private boolean isBookedPart(TimeSlot timeSlot, LocalDate checkIn, LocalDate checkOut) {
        return timeSlot.getFrom().isEqual(checkIn) && timeSlot.getTo().isEqual(checkOut);
    }

}
