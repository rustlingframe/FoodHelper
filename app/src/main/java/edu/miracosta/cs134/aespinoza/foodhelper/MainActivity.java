package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

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
import edu.miracosta.cs134.aespinoza.foodhelper.model.JSONLoader;

/**
 * MainActivity, the first real screen for the app.
 * Project - Final Project CS134
 * @Author Alvaro Espinoza Merida & Alfredo Hernandez
 */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private List<FoodResource> mAllFoodResourcesList;
    private DBHelper mDB;
    private ListView mFoodResourcesListView;
    private FoodResourceListAdapter mFoodResourceListAdapter;
    //Load a Google Map into our mapsFragment
    private GoogleMap map;

    public static final String TAG = MainActivity.class.getSimpleName();

    private static final String SETTINGS_CHOICE = "settings_choice" ;
    private String settingsChoice = "nothing" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deleteDatabase("foodresource") ;

        mDB = new DBHelper(this);

        mAllFoodResourcesList = mDB.getAllFoodResource();
        if(mAllFoodResourcesList.size() == 0){
            //TODO: Get the mAllFoodResourcesList from the Intent (loaded in SplashActivity)
            mAllFoodResourcesList = getIntent().getParcelableArrayListExtra("AllFoodResourcesList");
            for(FoodResource f : mAllFoodResourcesList){
                mDB.addFoodResource(f);
            }
        }

        /** This is fine*/
        Log.e("MIKEMAIN", mAllFoodResourcesList.toString());
        mFoodResourceListAdapter = new FoodResourceListAdapter(this, R.layout.foodresource_list_item, mAllFoodResourcesList) ;
        mFoodResourcesListView = findViewById(R.id.foodResourcesListView) ;
        mFoodResourcesListView.setAdapter(mFoodResourceListAdapter) ;

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.mapsfragment);
        mapFragment.getMapAsync(this);

        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Event: the map comes back from Google, what to do with it ?
        //center the location on OC 4800
        map = googleMap;
        LatLng oc4800 = new LatLng(33.190802,-117.301805);

        //1) Place a "marker" there !
        map.addMarker(new MarkerOptions()
                .title("Where the magic happens!")
                .position(oc4800)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker)));
        // 2) Center the "camera postion" on oc4800;
        CameraPosition position = new CameraPosition.Builder().target(oc4800).zoom(15f).build();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
        map.moveCamera(update);
    }


    public void addPokemon(View v)
    {

    }

    // DONE: Implement the clearAllPokemon method to remove all Pokemon objects
    // DONE: from the ListAdapter and the Database (DBHelper)
    public void clearAllPokemon(View v)
    {

    }

    public void viewFoodResourcesDetails(View v)
    {
        FoodResource selectedFoodResource = (FoodResource) v.getTag();

        Intent detailsIntent = new Intent(this,FoodResourceDetails.class);
        detailsIntent.putExtra("SelectedFoodResource",selectedFoodResource);
        startActivity(detailsIntent);
    }

    /**
     * Takes the user to the helpActivity.
     * @param v current view
     */
    public void goToHelp(View v)
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_settings,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);


        return super.onOptionsItemSelected(item);
    }

    SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferenceChangeListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                    switch (key)
                    {
                        case "login":
                            settingsChoice = sharedPreferences.getString("login", settingsChoice);
                            Toast.makeText(MainActivity.this,"login",Toast.LENGTH_SHORT).show();
                            break;
                        case "logout":
                            settingsChoice = sharedPreferences.getString("logout", settingsChoice);
                            Toast.makeText(MainActivity.this,"logout",Toast.LENGTH_SHORT).show();
                            break;
                        case "sign_up":
                            settingsChoice = sharedPreferences.getString("sign_up", settingsChoice);
                            Toast.makeText(MainActivity.this,"sign up",Toast.LENGTH_SHORT).show();
                            break;
                        case "help":
                            settingsChoice = sharedPreferences.getString("help", settingsChoice);
                            Toast.makeText(MainActivity.this,"help",Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            };

}
