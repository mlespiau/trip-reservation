/**
 * This class is generated by jOOQ
 */
package generated.tables.records;


import generated.tables.Booking;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
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
public class BookingRecord extends UpdatableRecordImpl<BookingRecord> implements Record3<UInteger, UInteger, Date> {

	private static final long serialVersionUID = 1734340800;

	/**
	 * Setter for <code>tripreservation.booking.id</code>.
	 */
	public void setId(UInteger value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>tripreservation.booking.id</code>.
	 */
	public UInteger getId() {
		return (UInteger) getValue(0);
	}

	/**
	 * Setter for <code>tripreservation.booking.customerCode</code>.
	 */
	public void setCustomercode(UInteger value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>tripreservation.booking.customerCode</code>.
	 */
	public UInteger getCustomercode() {
		return (UInteger) getValue(1);
	}

	/**
	 * Setter for <code>tripreservation.booking.date</code>.
	 */
	public void setDate(Date value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>tripreservation.booking.date</code>.
	 */
	public Date getDate() {
		return (Date) getValue(2);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<UInteger> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<UInteger, UInteger, Date> fieldsRow() {
		return (Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<UInteger, UInteger, Date> valuesRow() {
		return (Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UInteger> field1() {
		return Booking.BOOKING.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UInteger> field2() {
		return Booking.BOOKING.CUSTOMERCODE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field3() {
		return Booking.BOOKING.DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UInteger value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UInteger value2() {
		return getCustomercode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date value3() {
		return getDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BookingRecord value1(UInteger value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BookingRecord value2(UInteger value) {
		setCustomercode(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BookingRecord value3(Date value) {
		setDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BookingRecord values(UInteger value1, UInteger value2, Date value3) {
		value1(value1);
		value2(value2);
		value3(value3);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached BookingRecord
	 */
	public BookingRecord() {
		super(Booking.BOOKING);
	}

	/**
	 * Create a detached, initialised BookingRecord
	 */
	public BookingRecord(UInteger id, UInteger customercode, Date date) {
		super(Booking.BOOKING);

		setValue(0, id);
		setValue(1, customercode);
		setValue(2, date);
	}
}