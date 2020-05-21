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
 * Class loads Pokemon data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (Pokemon) with data.
 */
public class JSONLoader {

    public static final String JSON_URI = "https://raw.githubusercontent.com/rustlingframe/FoodResources/master/foodresources.json";
    //FROM GITHUB: https://github.com/rustlingframe/FoodResources/blob/master/foodresources.json";

    private static List<FoodResource> allFoodResourceList ;

    // DONE: Add a static method that reads the notusedpokedex.json file directly from the web
    // DONE: instead of using local AssetManager.  The pokedex can be found here:
    // DONE: https://github.com/Biuni/PokemonGO-Pokedex/blob/master/pokedex.json
    public static List<FoodResource> loadJSONFromHTTP() {
        allFoodResourceList = new ArrayList<>() ;
        // Android enforces that HTTP/HTTPS requests happen in BACKGROUND thread (not UI thread)
        // Background thread is asynchronous task

        DownloadJSONTask task = new DownloadJSONTask() ;
        task.execute(JSON_URI) ;

        System.out.println(allFoodResourceList.get(0).getOrganizationName());

        return allFoodResourceList;
    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

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
                Log.e("JSONLoader", "Error loading JSON from " + JSON_URI +" "+ e.getMessage()) ;
                return null ;
            }
        }
        // After JSONObject retrieved, parse it, create Pokemon object and add object to List
        // ctrl + o

        @Override
        protected void onPostExecute(JSONObject jsonRootObject) {
            try {
                JSONArray allFoodResourceJSON = jsonRootObject.getJSONArray("foodresource");
                int numberOfFoodResource = allFoodResourceJSON.length();
                allFoodResourceList = new ArrayList<>(numberOfFoodResource);

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

                    // Rather than use the .png files from JSON (too small), I decided to grab the best quality
                    // images from the source: assets.pokemon.com
                    // The schema being used is take the Pokemon id (e.g. 1) and pad it with zeros to make a
                    // 3-digit file name, e.g. 001.png
                    //*******WILL IMPLEMENT WHEN THE CODE IS COMPLETE AND FIND IMAGES ONLINE
                    //imgUri = Uri.parse(IMG_URI_BASE + String.format("%03d", id) + ".png");

                    foodResource = new FoodResource(   id, organizationName,name,address,city,state,zipCode,phoneNumber,latitude,
                            longitude,description,isDiscounted,isFree);

                    allFoodResourceList.add(foodResource);
                }
            }
            catch (Exception e)
            {
                Log.e("Food Resource", e.getMessage());
            }
        }

    }
}
