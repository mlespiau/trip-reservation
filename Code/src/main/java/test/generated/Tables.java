/**
 * This class is generated by jOOQ
 */
package test.generated;


import javax.annotation.Generated;

import test.generated.tables.Booking;
import test.generated.tables.Hotel;
import test.generated.tables.Room;
import test.generated.tables.Roomtimeslot;


/**
 * Convenience access to all tables in tripreservation
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

	/**
	 * The table tripreservation.booking
	 */
	public static final Booking BOOKING = test.generated.tables.Booking.BOOKING;

	/**
	 * The table tripreservation.hotel
	 */
	public static final Hotel HOTEL = test.generated.tables.Hotel.HOTEL;

	/**
	 * The table tripreservation.room
	 */
	public static final Room ROOM = test.generated.tables.Room.ROOM;

	/**
	 * The table tripreservation.roomTimeSlot
	 */
	public static final Roomtimeslot ROOMTIMESLOT = test.generated.tables.Roomtimeslot.ROOMTIMESLOT;
}
