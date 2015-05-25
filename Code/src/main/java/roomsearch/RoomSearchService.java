package roomsearch;

import java.util.List;

import roomtimeslot.RoomTimeSlot;
import roomtimeslot.RoomTimeSlotRepository;

public class RoomSearchService {
    private RoomTimeSlotRepository roomTimeSlotRepository;
    
    public RoomSearchService(RoomTimeSlotRepository roomTimeSlotRepository) {
        this.roomTimeSlotRepository = roomTimeSlotRepository;
    }

    public List<RoomTimeSlot> search(RoomSearchSpecification roomSearchSpecification) {
        return this.roomTimeSlotRepository.find(roomSearchSpecification);
    }
}
