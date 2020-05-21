package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import edu.miracosta.cs134.aespinoza.foodhelper.model.FoodResourceListAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDB = new DBHelper(this);
        mAllFoodResourcesList = mDB.getAllFoodResource();

        if(mAllFoodResourcesList.size() == 0 ){
            mAllFoodResourcesList = JSONLoader.loadJSONFromHTTP();
            for(FoodResource f : mAllFoodResourcesList){
                mDB.addFoodResource(f); }
        }

        mFoodResourceListAdapter = new FoodResourceListAdapter(this,R.layout.foodresource_list_item,mAllFoodResourcesList);
        mFoodResourcesListView = findViewById(R.id.foodResourcesListView);
        mFoodResourcesListView.setAdapter(mFoodResourceListAdapter);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.mapsfragment);
        mapFragment.getMapAsync(this);
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

    public void viewPokemonDetails(View v)
    {

    }

    /**
     * Takes the user to the helpActivity.
     * @param v current view
     */
    public void goToHelp(View v)
    {

    }
}
