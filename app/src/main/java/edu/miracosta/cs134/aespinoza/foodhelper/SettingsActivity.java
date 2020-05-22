package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * SettingsActivity is used to create the settings tab based on the preferences.xml.
 * @Author Alfredo Hernandez Jr
 * CS134 Final Project
 */
public class SettingsActivity extends AppCompatActivity {

    /**
     * Called when the activity first starts.
     * @param savedInstanceState Used for instantiating Parcelable and Serializable objects.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content,new SettingsActivityFragment())
                .commit();
    }

    /**
     * SettingsActivityFragment used for something... Not too sure, but its necessary for the code to run.
     */
    public static class SettingsActivityFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
