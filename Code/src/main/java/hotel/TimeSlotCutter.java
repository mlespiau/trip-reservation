package hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotCutter {

    public List<TimeSlot> cutByDates(TimeSlot timeSlot,
            LocalDate checkIn, LocalDate checkOut) {
        List<TimeSlot> result = new ArrayList<TimeSlot>();
        if (timeSlot.getFrom().isBefore(checkIn)) {
            result.add(TimeSlot.create(timeSlot.getFrom(), checkIn.minusDays(1)));
            if (checkOut.isBefore(timeSlot.getTo())) {
                result.add(TimeSlot.create(checkIn, checkOut));
                result.add(TimeSlot.create(checkOut, timeSlot.getTo()));
            } else {
                result.add(TimeSlot.create(checkIn, checkOut));
            }
        } else {
            result.add(TimeSlot.create(checkIn, checkOut));
            if (checkOut.isBefore(timeSlot.getTo())) {
                result.add(TimeSlot.create(checkOut, timeSlot.getTo()));
            }
        }
        return result;
    }

}
