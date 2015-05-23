package serialization;

import spark.ResponseTransformer;

import com.google.gson.Gson;

public class Json {
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return Json::toJson;
    }
}
