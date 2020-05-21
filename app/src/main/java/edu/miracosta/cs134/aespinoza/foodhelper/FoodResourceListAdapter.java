package edu.miracosta.cs134.aespinoza.foodhelper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import edu.miracosta.cs134.aespinoza.foodhelper.model.FoodResource;

/**
 * Helper class to provide custom adapter for the <code>Location</code> list.
 */
public class FoodResourceListAdapter extends ArrayAdapter<FoodResource> {

    private Context mContext;
    private List<FoodResource> mFoodResourceList ;
    private int mResourceId;

    /**
     * Creates a new <code>LocationsListAdapter</code> given a mContext, resource id and list of locations.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param foodResources The list of locations to display
     */
    public FoodResourceListAdapter(Context c, int rId, List<FoodResource> foodResources) {
        super(c, rId, foodResources);
        mContext = c;
        mResourceId = rId;
        mFoodResourceList = foodResources;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the Location selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final FoodResource selectedFoodResource = mFoodResourceList.get(pos);
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout foodResourceListLinearLayout =
                view.findViewById(R.id.foodResourceListLinearLayout);

        ImageView foodResourceListImageView =
                view.findViewById(R.id.foodResourceListImageView);


        TextView foodResourceListOrganizationNameTextView =
                view.findViewById(R.id.foodResourceListOrganizationNameTextView);

        TextView foodResourceListAddressTextView =
                view.findViewById(R.id.foodResourceListAddressTextView);

        TextView foodResourceListPhoneTextView =
                view.findViewById(R.id.foodResourceListPhoneTextView);

        foodResourceListLinearLayout.setTag(selectedFoodResource);

        foodResourceListOrganizationNameTextView.setText(selectedFoodResource.getOrganizationName());
        foodResourceListAddressTextView.setText(selectedFoodResource.getLocation().getFullAddress());
        foodResourceListPhoneTextView.setText(selectedFoodResource.getLocation().getPhone());

        return view;
    }
}
