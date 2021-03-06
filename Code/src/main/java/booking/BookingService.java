package booking;

import framework.DaoException;
import framework.Database;
import framework.RequestParameters;
import generated.Tables;
import generated.tables.records.BookingRecord;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.jooq.exception.DataAccessException;
import org.jooq.types.UInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roomtimeslot.RoomTimeSlot;
import roomtimeslot.RoomTimeSlotRepository;
import roomtimeslot.RoomTimeSlotService;
import roomtimeslot.TimeSlot;
import roomtimeslot.TimeSlotCutter;
import security.Customer;

public class BookingService {
    Logger logger = LoggerFactory.getLogger(getClass()); 
    private TimeSlotCutter timeSlotCutter;
    private RoomTimeSlotService roomTimeSlotService;
    private RoomTimeSlotRepository roomTimeSlotRepository;
    
    public BookingService(TimeSlotCutter timeSlotCutter, RoomTimeSlotService roomTimeSlotService, RoomTimeSlotRepository roomTimeSlotRepository) {
        this.timeSlotCutter = timeSlotCutter;
        this.roomTimeSlotService = roomTimeSlotService;
        this.roomTimeSlotRepository = roomTimeSlotRepository;
    }

    public RoomTimeSlot book(Customer customer, RequestParameters requestParameters) {
        this.assertHasRequiredParameters(requestParameters);
        RoomTimeSlot roomTimeSlot = this.roomTimeSlotRepository.findById(requestParameters.getAsInteger("roomTimeSlotId"));
        LocalDate checkIn = requestParameters.getAsLocalDate("checkIn");
        LocalDate checkOut = requestParameters.getAsLocalDate("checkOut");
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
    
    private void assertHasRequiredParameters(RequestParameters requestParameters) {
        requestParameters.assertHasValue("roomTimeSlotId");
        requestParameters.assertHasValue("checkIn");
        requestParameters.assertHasValue("checkOut");
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

    public List<RoomTimeSlot> findByRoomId(int roomId) {
        return this.roomTimeSlotRepository.findBookedByRoomId(roomId);
    }

}
