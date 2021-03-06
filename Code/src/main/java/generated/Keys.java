/**
 * This class is generated by jOOQ
 */
package generated;


import generated.tables.Booking;
import generated.tables.Hotel;
import generated.tables.Room;
import generated.tables.Roomtimeslot;
import generated.tables.records.BookingRecord;
import generated.tables.records.HotelRecord;
import generated.tables.records.RoomRecord;
import generated.tables.records.RoomtimeslotRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;
import org.jooq.types.UInteger;


/**
 * A class modelling foreign key relationships between tables of the <code>tripreservation</code> 
 * schema
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------

	public static final Identity<BookingRecord, UInteger> IDENTITY_BOOKING = Identities0.IDENTITY_BOOKING;
	public static final Identity<HotelRecord, UInteger> IDENTITY_HOTEL = Identities0.IDENTITY_HOTEL;
	public static final Identity<RoomRecord, UInteger> IDENTITY_ROOM = Identities0.IDENTITY_ROOM;
	public static final Identity<RoomtimeslotRecord, UInteger> IDENTITY_ROOMTIMESLOT = Identities0.IDENTITY_ROOMTIMESLOT;

	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final UniqueKey<BookingRecord> KEY_BOOKING_PRIMARY = UniqueKeys0.KEY_BOOKING_PRIMARY;
	public static final UniqueKey<HotelRecord> KEY_HOTEL_PRIMARY = UniqueKeys0.KEY_HOTEL_PRIMARY;
	public static final UniqueKey<HotelRecord> KEY_HOTEL_CODE = UniqueKeys0.KEY_HOTEL_CODE;
	public static final UniqueKey<RoomRecord> KEY_ROOM_PRIMARY = UniqueKeys0.KEY_ROOM_PRIMARY;
	public static final UniqueKey<RoomRecord> KEY_ROOM_CODE = UniqueKeys0.KEY_ROOM_CODE;
	public static final UniqueKey<RoomtimeslotRecord> KEY_ROOMTIMESLOT_PRIMARY = UniqueKeys0.KEY_ROOMTIMESLOT_PRIMARY;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------


	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class Identities0 extends AbstractKeys {
		public static Identity<BookingRecord, UInteger> IDENTITY_BOOKING = createIdentity(Booking.BOOKING, Booking.BOOKING.ID);
		public static Identity<HotelRecord, UInteger> IDENTITY_HOTEL = createIdentity(Hotel.HOTEL, Hotel.HOTEL.ID);
		public static Identity<RoomRecord, UInteger> IDENTITY_ROOM = createIdentity(Room.ROOM, Room.ROOM.ID);
		public static Identity<RoomtimeslotRecord, UInteger> IDENTITY_ROOMTIMESLOT = createIdentity(Roomtimeslot.ROOMTIMESLOT, Roomtimeslot.ROOMTIMESLOT.ID);
	}

	private static class UniqueKeys0 extends AbstractKeys {
		public static final UniqueKey<BookingRecord> KEY_BOOKING_PRIMARY = createUniqueKey(Booking.BOOKING, Booking.BOOKING.ID);
		public static final UniqueKey<HotelRecord> KEY_HOTEL_PRIMARY = createUniqueKey(Hotel.HOTEL, Hotel.HOTEL.ID);
		public static final UniqueKey<HotelRecord> KEY_HOTEL_CODE = createUniqueKey(Hotel.HOTEL, Hotel.HOTEL.CODE, Hotel.HOTEL.AGENTCODE);
		public static final UniqueKey<RoomRecord> KEY_ROOM_PRIMARY = createUniqueKey(Room.ROOM, Room.ROOM.ID);
		public static final UniqueKey<RoomRecord> KEY_ROOM_CODE = createUniqueKey(Room.ROOM, Room.ROOM.CODE, Room.ROOM.HOTELID);
		public static final UniqueKey<RoomtimeslotRecord> KEY_ROOMTIMESLOT_PRIMARY = createUniqueKey(Roomtimeslot.ROOMTIMESLOT, Roomtimeslot.ROOMTIMESLOT.ID);
	}
}
