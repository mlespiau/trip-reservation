package hotel;

import java.util.ArrayList;
import java.util.List;

import org.jooq.Record;
import org.jooq.Result;

public class RoomTimeSlotRepository {

    public List<RoomTimeSlot> find(RoomSearchSpecification roomSearchSpecification) {
        Result<Record> result = roomSearchSpecification.toQuery().fetch();
        List<RoomTimeSlot> list = new ArrayList<RoomTimeSlot>();
        for(Record record : result) {
            list.add(RoomTimeSlot.fromRecord(record));
        }
        return  list;
    }


}
