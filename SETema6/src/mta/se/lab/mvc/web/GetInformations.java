package mta.se.lab.mvc.web;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * class used to retrieve weather informations using a web connection
 * @author ADY
 * @since 2014-11-22
 */
public class GetInformations {
	
	/**
	 * used to create a JSON object 
	 * @param tagName name of weather object field
	 * @param jObj JSON object containing informations from a weather HTPP client
	 * @return JSON object containing a specific weather field
	 * @throws JSONException
	 */
	public static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
	    JSONObject subObj = jObj.getJSONObject(tagName);
	    return subObj;
	}
	
	/**
	 * used to retrieve a specific weather information in string format
	 * @param tagName name of weather object field
	 * @param jObj JSON object containing informations from a weather HTPP client
	 * @return JSON object containing a specific weather field
	 * @throws JSONException
	 */
	public static String getString(String tagName, JSONObject jObj) throws JSONException {
	    return jObj.getString(tagName);
	}

	/**
	 * used to retrieve a specific weather information in float format
	 * @param tagName name of weather object field
	 * @param jObj JSON object containing informations from a weather HTPP client
	 * @return JSON object containing a specific weather field
	 * @throws JSONException
	 */
	public static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
	    return (float) jObj.getDouble(tagName);
	}
	 

	/**
	 * used to retrieve a specific weather information in integer format
	 * @param tagName name of weather object field
	 * @param jObj JSON object containing informations from a weather HTPP client
	 * @return JSON object containing a specific weather field
	 * @throws JSONException
	 */
	public static int  getInt(String tagName, JSONObject jObj) throws JSONException {
	    return jObj.getInt(tagName);
	}
}
