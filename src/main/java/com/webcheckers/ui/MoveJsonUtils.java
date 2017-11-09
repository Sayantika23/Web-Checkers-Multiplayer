package com.webcheckers.ui;

import spark.ResponseTransformer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.dao.JumpsMarshaller;
import com.webcheckers.model.Move;

/**
 * A Utility class for parsing and formatting JSON data.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class MoveJsonUtils {

    /** The Constant GSON. */
    private static Gson GSON;

    
    /**
     * Instantiates a new json utils,
     * instance of GsonBuilder and registers
     * new instance of UserMarshaller to
     * serialize/deserialize user instances 
     */
    public MoveJsonUtils() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Move.class, new JumpsMarshaller());
        GSON = builder.create();
    }

    /**
     * From json.
     *
     * @param <T> the generic type
     * @param json the json
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T fromJson(final String json, final Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    /**
     * To json.
     *
     * @param object the object
     * @return the string
     */
    public static String toJson(Object object) {
    	System.out.println("OBJECT: " + object);
        return GSON.toJson(object);
    }

    /**
     * Json.
     *
     * @return the response transformer
     */
    public static ResponseTransformer json() {
        return JsonUtils::toJson;
    }
}