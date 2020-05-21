package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import edu.miracosta.cs134.aespinoza.foodhelper.model.FoodResource;

public class FoodResourceDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_resource_details);

        ImageView foodResourceDetailsImageView = findViewById(R.id.foodResourceDetailsImageView);

        TextView foodResourceDetailsOrganizationNameTextView = findViewById(R.id.foodResourceDetailsOrganizationNameTextView);

        TextView foodResourceDetailsAddressTextView = findViewById(R.id.foodResourceDetailsAddressTextView);

        TextView foodResourceDetailsPhoneTextView = findViewById(R.id.foodResourceDetailsPhoneTextView);

        TextView foodResourceDetailsDescriptionTextView = findViewById(R.id.foodResourceDetailsDescriptionTextView);

        FoodResource selectedFoodResource = getIntent().getExtras().getParcelable("SelectedFoodResource");

        foodResourceDetailsImageView.setImageResource(R.drawable.ic_launcher_background);

        foodResourceDetailsOrganizationNameTextView.setText(selectedFoodResource.getOrganizationName());
        foodResourceDetailsAddressTextView.setText(selectedFoodResource.getLocation().getFullAddress());
        foodResourceDetailsPhoneTextView.setText(selectedFoodResource.getLocation().getPhone());
        foodResourceDetailsDescriptionTextView.setText(selectedFoodResource.getEventDescription());



    }
}
