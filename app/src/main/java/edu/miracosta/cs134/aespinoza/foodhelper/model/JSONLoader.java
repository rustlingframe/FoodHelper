package edu.miracosta.cs134.aespinoza.foodhelper.model;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
/**
 * Class loads FoodResource data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (FoodResource) with data.
 * @Author Alvaro Espinoza Merida
 *  * Project - Final Project CS134
 */
public class JSONLoader {

    public static final String JSON_URI = "https://raw.githubusercontent.com/rustlingframe/FoodResources/master/foodresources.json";
    //FROM GITHUB: https://github.com/rustlingframe/FoodResources/blob/master/foodresources.json";

    private static ArrayList<FoodResource> allFoodResourceList ;

    /**
     * Loads a JSON file from the internet and fills up the list of FoodResources
     * @return filled FoodResource list
     */
    public static ArrayList<FoodResource> loadJSONFromHTTP() {
        allFoodResourceList = new ArrayList<>() ;

        DownloadJSONTask task = new DownloadJSONTask() ;
        task.execute(JSON_URI) ;

        return allFoodResourceList ;
    }

    /**
     * Reads in puts all contents into a single StringBuilder
     * @param rd given reader
     * @return All contents in a single String
     * @throws IOException Can fail at reading in a file.
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * Reads in JSON file from a url.
     * @param url location of the JSON file
     * @return Root Json object
     * @throws IOException can fail to read the file
     * @throws JSONException catch all other exceptions
     */
    private static JSONObject readJSONFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(reader);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    // Asynchronous task = private inner class
    // <input, progress data type, output data type>
    // progress is used to report back how the user wants it such as a decimal progression, an int, or even a String.
    private static class DownloadJSONTask extends AsyncTask<String, Void, JSONObject>
    {
        // Connect to the URL (source of JSON)
        // Parse it and construct a JSON object
        @Override
        protected JSONObject doInBackground(String... strings) {
            try
            {
                return readJSONFromUrl(JSON_URI);
            } catch (Exception e) {
                Log.e("JSONLoader", "Error loading JSON from " + JSON_URI + e.getMessage()) ;
                return null ;
            }
        }

        /**
         * Reads in all JSON objects
         * @param jsonRootObject the name of the JSON file
         */
        @Override
        protected void onPostExecute(JSONObject jsonRootObject) {
            try {

                JSONArray allFoodResourceJSON = jsonRootObject.getJSONArray("foodresource") ;
                int numberOfFoodResource = allFoodResourceJSON.length();

                FoodResource foodResource;
                long id;
                String organizationName;

                String name;
                String address;
                String city;
                String state;
                String zipCode;
                String phoneNumber;
                double latitude;
                double longitude;
                String description;
                int isDiscounted;
                int isFree;

                for (int i = 0; i < numberOfFoodResource; i++) {
                    JSONObject pmJSON = allFoodResourceJSON.getJSONObject(i);
                    id = pmJSON.getLong("id");
                    organizationName = pmJSON.getString("organizationName");
                    name = pmJSON.getString("name");
                    address = pmJSON.getString("address");

                    city = pmJSON.getString("city");
                    state = pmJSON.getString("state");
                    zipCode = pmJSON.getString("zipcode");

                    phoneNumber = pmJSON.getString("phone");
                    latitude = pmJSON.getDouble("latitude");
                    longitude = pmJSON.getDouble("longitude");

                    description = pmJSON.getString("description");
                    isDiscounted = pmJSON.getInt("isDiscounted");
                    isFree = pmJSON.getInt("isFree");

                    foodResource = new FoodResource(id,organizationName,name,address,city,state,zipCode,phoneNumber,latitude,
                            longitude,description,isDiscounted,isFree);

                    allFoodResourceList.add(foodResource);
                }
            }
            catch (Exception e)
            {
                Log.e("Food Resource", e.getMessage());
            }
            // Thank you Mr.Paulding!
            Log.e("MIKE", allFoodResourceList.toString());
        }
    }
}
