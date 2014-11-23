package mta.se.lab.mvc.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * class used to create a connection with an weather HTTP client
 * @author ADY
 * @since 2014-11-22
 */

public class WeatherHttpClient {
	 
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
    
 
    /**
     * used to create a connection and retrieve weather informations of a specific location
     * @param location coordinates of a specific location-latitude and longitude
     * @return weather informations of a specific area
     */
    public static String getWeatherData(String location) {
        HttpURLConnection con = null ;
        InputStream is = null;
 
        try {
            con = (HttpURLConnection) ( new URL(BASE_URL + location)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
             
            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");
             
            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
 
        return null;
                 
    }
}
