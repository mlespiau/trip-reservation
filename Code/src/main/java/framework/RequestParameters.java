package framework;

import static spark.Spark.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import spark.QueryParamsMap;

import com.google.gson.Gson;

public class RequestParameters {
	private QueryParamsMap queryMap;
	
	public RequestParameters(QueryParamsMap queryMap) {
		this.queryMap = queryMap;
	}
	
	public void assertHasValue(String parameterName) {
		if (!this.queryMap.get(parameterName).hasValue()) {
			throw RequiredParameterException.createFromParameterName(parameterName);
		}
	}
	
	public LocalDate getAsLocalDate(String parameterName) {
		return LocalDate.parse(queryMap.get(parameterName).value(), DateTimeFormatter.ISO_LOCAL_DATE);
	}
	
	public int getAsInteger(String parameterName) {
		return queryMap.get(parameterName).integerValue();
	}
	
	public static void registerExceptionHandler() {
        exception(RequiredParameterException.class, (e, request, response) -> {
            response.status(401);
            Gson gson = new Gson();
            response.body(gson.toJson(new ApiError(e.getMessage())));
        });
	}
}
