/**
 * This class is generated by jOOQ
 */
package generated.tables.pojos;


import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoomtimeslotPojo implements Serializable {

	private static final long serialVersionUID = 627127087;

	private UInteger id;
	private UInteger roomid;
	private Date     fromdate;
	private Date     todate;
	private UInteger bookingid;

	public RoomtimeslotPojo() {}

	public RoomtimeslotPojo(RoomtimeslotPojo value) {
		this.id = value.id;
		this.roomid = value.roomid;
		this.fromdate = value.fromdate;
		this.todate = value.todate;
		this.bookingid = value.bookingid;
	}

	public RoomtimeslotPojo(
		UInteger id,
		UInteger roomid,
		Date     fromdate,
		Date     todate,
		UInteger bookingid
	) {
		this.id = id;
		this.roomid = roomid;
		this.fromdate = fromdate;
		this.todate = todate;
		this.bookingid = bookingid;
	}

	public UInteger getId() {
		return this.id;
	}

	public void setId(UInteger id) {
		this.id = id;
	}

	public UInteger getRoomid() {
		return this.roomid;
	}

	public void setRoomid(UInteger roomid) {
		this.roomid = roomid;
	}

	public Date getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return this.todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public UInteger getBookingid() {
		return this.bookingid;
	}

	public void setBookingid(UInteger bookingid) {
		this.bookingid = bookingid;
	}
}
