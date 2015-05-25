/**
 * This class is generated by jOOQ
 */
package test.generated.tables.pojos;


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
public class BookingPojo implements Serializable {

	private static final long serialVersionUID = 108694670;

	private UInteger id;
	private UInteger customercode;
	private Date     date;

	public BookingPojo() {}

	public BookingPojo(BookingPojo value) {
		this.id = value.id;
		this.customercode = value.customercode;
		this.date = value.date;
	}

	public BookingPojo(
		UInteger id,
		UInteger customercode,
		Date     date
	) {
		this.id = id;
		this.customercode = customercode;
		this.date = date;
	}

	public UInteger getId() {
		return this.id;
	}

	public void setId(UInteger id) {
		this.id = id;
	}

	public UInteger getCustomercode() {
		return this.customercode;
	}

	public void setCustomercode(UInteger customercode) {
		this.customercode = customercode;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
