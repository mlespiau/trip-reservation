package hotel;

import static test.generated.tables.Hotel.HOTEL;
import static test.generated.tables.Room.ROOM;
import static test.generated.tables.Roomtimeslot.ROOMTIMESLOT;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.jooq.SelectOnConditionStep;
import org.jooq.SelectOnStep;
import org.jooq.types.UInteger;

import spark.QueryParamsMap;
import framework.Database;

public class RoomSearchSpecification {
	private LocalDate checkIn;
	private LocalDate checkOut;
	private int adultSpace;
	private int childrenSpace;
	
	public RoomSearchSpecification(LocalDate checkIn, LocalDate checkOut, int adultSpace, int childrenSpace) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.adultSpace = adultSpace;
		this.childrenSpace = childrenSpace;
	}	

	public static RoomSearchSpecification fromQueryParams(QueryParamsMap queryMap) {
//		QueryParamsMap checkInParam = queryMap.get("checkIn");
//		if (!checkInParam.hasValue()) {
//			throw new RequiredParameterException();
//		}
//		QueryParamsMap queryParamsMap = queryMap.get("checkIn");
//		QueryParamsMap queryParamsMapCualquiera = queryMap.get("cualquier");
		
		return new RoomSearchSpecification(
			LocalDate.parse(queryMap.get("checkIn").value(), DateTimeFormatter.ISO_LOCAL_DATE),
	        LocalDate.parse(queryMap.get("checkOut").value(), DateTimeFormatter.ISO_LOCAL_DATE),
	        queryMap.get("adultSpace").integerValue(),
	        queryMap.get("childrenSpace").integerValue());
	      
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public int getAdultSpace() {
		return adultSpace;
	}

	public int getChildrenSpace() {
		return childrenSpace;
	}

	public SelectConditionStep<Record> toQuery() {
		SelectConditionStep<Record> query = Database.getInstance().getDslContext().
            select().from(ROOMTIMESLOT).
            join(ROOM).on(ROOMTIMESLOT.ROOMID.equal(ROOM.ID)).
            join(HOTEL).on(ROOM.HOTELID.equal(HOTEL.ID)).
            where(ROOMTIMESLOT.FROMDATE.lessThan(Date.valueOf(this.getCheckIn()))).
            and(ROOMTIMESLOT.TODATE.greaterThan(Date.valueOf(this.getCheckOut()))).
            and(ROOM.ADULTSPACE.equal(UInteger.valueOf(this.getAdultSpace()))).
            and(ROOM.CHILDRENSPACE.equal(UInteger.valueOf(this.getChildrenSpace())));
		return query;
	}
}
