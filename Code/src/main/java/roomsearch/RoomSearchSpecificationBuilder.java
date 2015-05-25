package roomsearch;

import framework.RequestParameters;

public class RoomSearchSpecificationBuilder {
	
	public RoomSearchSpecification fromQueryParams(RequestParameters requestParameters) {
		requestParameters.assertHasValue("checkIn");
		requestParameters.assertHasValue("checkOut");
		requestParameters.assertHasValue("adultSpace");
		requestParameters.assertHasValue("childrenSpace");
		return new RoomSearchSpecification(
			requestParameters.getAsLocalDate("checkIn"),
			requestParameters.getAsLocalDate("checkOut"),
	        requestParameters.getAsInteger("adultSpace"),
	        requestParameters.getAsInteger("childrenSpace"));
	      
	}
}
