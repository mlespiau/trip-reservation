package hotel;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;
import java.time.LocalDate;

import org.junit.Test;

public class RoomTimeSlotServiceTest {
    @Test
    public void testCreateSetsIdToTimeSlot() {
        RoomTimeSlotDao mockedRoomTimeSlotDao = mock(RoomTimeSlotDao.class);
        when(mockedRoomTimeSlotDao.save((RoomTimeSlot) any())).thenReturn(1);
        RoomTimeSlotService roomTimeSlotService = new RoomTimeSlotService(
            mockedRoomTimeSlotDao
        );
        RoomTimeSlot savedRoomTimeSlot = roomTimeSlotService.create(
            new Room(1, new Hotel(1, 1, 1, true), 1, 0),
            TimeSlot.create(LocalDate.now(), LocalDate.now())
        );
        assertNotEquals(0, savedRoomTimeSlot.getId());
    }
    
}
