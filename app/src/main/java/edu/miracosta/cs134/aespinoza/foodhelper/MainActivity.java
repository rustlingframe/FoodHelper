package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import edu.miracosta.cs134.aespinoza.foodhelper.model.DBHelper;
import edu.miracosta.cs134.aespinoza.foodhelper.model.FoodResource;

/**
 * MainActivity, the first real screen for the app. Loads in the google map, the settings,
 * @Author Alvaro Espinoza Merida & Alfredo Hernandez
 * Project - Final Project CS134
 */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    /**ListView and Google Map Code.*/
    private List<FoodResource> mAllFoodResourcesList;
    private DBHelper mDB;
    private ListView mFoodResourcesListView;
    private FoodResourceListAdapter mFoodResourceListAdapter;
    //Load a Google Map into our mapsFragment
    private GoogleMap map;

    public static final String TAG = MainActivity.class.getSimpleName();

    /**Settings Code.*/
    private static final String SETTINGS_CHOICE = "settings_choice" ;
    private String settingsChoice = "nothing" ;

    /**
     * Called on when the activity first starts. Fills in the list of FoodResource s, loads up the listView,
     * and creates the google map.
     * @param savedInstanceState Used for instantiating Parcelable and Serializable objects.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**List items and general List code.*/
        mDB = new DBHelper(this);

        mAllFoodResourcesList = mDB.getAllFoodResource();
        if(mAllFoodResourcesList.size() == 0){
            mAllFoodResourcesList = getIntent().getParcelableArrayListExtra("AllFoodResourcesList");
            for(FoodResource f : mAllFoodResourcesList){
                mDB.addFoodResource(f);
            }
        }

        Log.e("MIKEMAIN", mAllFoodResourcesList.toString());
        mFoodResourceListAdapter = new FoodResourceListAdapter(this, R.layout.foodresource_list_item, mAllFoodResourcesList) ;
        mFoodResourcesListView = findViewById(R.id.foodResourcesListView) ;
        mFoodResourcesListView.setAdapter(mFoodResourceListAdapter) ;

        /**Map Code.*/
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.mapsfragment);
        mapFragment.getMapAsync(this);
    }

    /**
     * Plots the desired point onto the google map, gives it a custom marker, and zooms in.
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng oc4800 = new LatLng(33.190802,-117.301805);

        map.addMarker(new MarkerOptions()
                .title("Where the magic happens!")
                .position(oc4800)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker)));

        CameraPosition position = new CameraPosition.Builder().target(oc4800).zoom(15f).build();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
        map.moveCamera(update);
    }

    /**
     * Takes the user to the DetailsActivity of a selected item.
     * @param v selected item
     */
    public void viewFoodResourcesDetails(View v)
    {
        FoodResource selectedFoodResource = (FoodResource) v.getTag();

        Intent detailsIntent = new Intent(this,FoodResourceDetails.class);
        detailsIntent.putExtra("SelectedFoodResource",selectedFoodResource);
        startActivity(detailsIntent);
    }

    /**
     * Is called when the Menu for the settings is first created.
     * @param menu the chosen menu
     * @return true or false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_settings,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Activates when the setting icon is chosen in the MainActivity.
     * @param item The menu in the MainActivity
     * @return true or false
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);

        return super.onOptionsItemSelected(item);
    }
}
