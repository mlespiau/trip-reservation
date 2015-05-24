/**
 * This class is generated by jOOQ
 */
package test.generated.tables;


import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;

import test.generated.Keys;
import test.generated.Tripreservation;
import test.generated.tables.records.RoomtimeslotRecord;


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
public class Roomtimeslot extends TableImpl<RoomtimeslotRecord> {

	private static final long serialVersionUID = 525796660;

	/**
	 * The reference instance of <code>tripreservation.roomTimeSlot</code>
	 */
	public static final Roomtimeslot ROOMTIMESLOT = new Roomtimeslot();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<RoomtimeslotRecord> getRecordType() {
		return RoomtimeslotRecord.class;
	}

	/**
	 * The column <code>tripreservation.roomTimeSlot.id</code>.
	 */
	public final TableField<RoomtimeslotRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

	/**
	 * The column <code>tripreservation.roomTimeSlot.roomId</code>.
	 */
	public final TableField<RoomtimeslotRecord, UInteger> ROOMID = createField("roomId", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

	/**
	 * The column <code>tripreservation.roomTimeSlot.fromDate</code>.
	 */
	public final TableField<RoomtimeslotRecord, Date> FROMDATE = createField("fromDate", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

	/**
	 * The column <code>tripreservation.roomTimeSlot.toDate</code>.
	 */
	public final TableField<RoomtimeslotRecord, Date> TODATE = createField("toDate", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

	/**
	 * Create a <code>tripreservation.roomTimeSlot</code> table reference
	 */
	public Roomtimeslot() {
		this("roomTimeSlot", null);
	}

	/**
	 * Create an aliased <code>tripreservation.roomTimeSlot</code> table reference
	 */
	public Roomtimeslot(String alias) {
		this(alias, ROOMTIMESLOT);
	}

	private Roomtimeslot(String alias, Table<RoomtimeslotRecord> aliased) {
		this(alias, aliased, null);
	}

	private Roomtimeslot(String alias, Table<RoomtimeslotRecord> aliased, Field<?>[] parameters) {
		super(alias, Tripreservation.TRIPRESERVATION, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<RoomtimeslotRecord, UInteger> getIdentity() {
		return Keys.IDENTITY_ROOMTIMESLOT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<RoomtimeslotRecord> getPrimaryKey() {
		return Keys.KEY_ROOMTIMESLOT_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<RoomtimeslotRecord>> getKeys() {
		return Arrays.<UniqueKey<RoomtimeslotRecord>>asList(Keys.KEY_ROOMTIMESLOT_PRIMARY, Keys.KEY_ROOMTIMESLOT_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Roomtimeslot as(String alias) {
		return new Roomtimeslot(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Roomtimeslot rename(String name) {
		return new Roomtimeslot(name, null);
	}
}
