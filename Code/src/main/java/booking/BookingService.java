package booking;

import hotel.RoomTimeSlot;
import hotel.RoomTimeSlotService;
import hotel.TimeSlot;
import hotel.TimeSlotCutter;

import java.time.LocalDate;
import java.util.List;

import security.Customer;

public class BookingService {
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
                Booking create = Booking.create(customer.getCode());
                newRoomTimeSlot.setBooking(create);
                bookedRoomTimeSlot = newRoomTimeSlot;
            }
            this.roomTimeSlotService.saveNew(newRoomTimeSlot);
        }
        this.roomTimeSlotService.delete(roomTimeSlot);
        return bookedRoomTimeSlot;
    }
    
    private boolean isBookedPart(TimeSlot timeSlot, LocalDate checkIn, LocalDate checkOut) {
        return timeSlot.getFrom().isEqual(checkIn) && timeSlot.getTo().isEqual(checkOut);
    }

}
