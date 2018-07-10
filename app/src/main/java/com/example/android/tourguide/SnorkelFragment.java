package com.example.android.tourguide;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom object stores location information for attraction category snorkeling spots.
 */
public class SnorkelFragment {

    ArrayList<Attraction> attractionArrayList;

    public SnorkelFragment(Resources resources) {
        attractionArrayList = new ArrayList<>();

        attractionArrayList.add(new Attraction(resources.getString(R.string.snorkel_kua_bay_title),
                Double.parseDouble(resources.getString(R.string.snorkel_kua_bay_lat)),
                Double.parseDouble(resources.getString(R.string.snorkel_kua_bay_lng)),
                resources.getString(R.string.snorkel_kua_bay_snippet),
                R.drawable.kua_bay));
        attractionArrayList.add(new Attraction(resources.getString(R.string.snorkel_makalawena_title),
                Double.parseDouble(resources.getString(R.string.snorkel_makalawena_lat)),
                Double.parseDouble(resources.getString(R.string.snorkel_makalawena_lng)),
                resources.getString(R.string.snorkel_makalawena_snippet),
                R.drawable.pano_secluded_beach));
        attractionArrayList.add(new Attraction(resources.getString(R.string.snorkel_mahaiula_title),
                Double.parseDouble(resources.getString(R.string.snorkel_mahaiula_lat)),
                Double.parseDouble(resources.getString(R.string.snorkel_mahaiula_lng)),
                resources.getString(R.string.snorkel_mahaiula_snippet),
                R.drawable.pano_beach));
        attractionArrayList.add(new Attraction(resources.getString(R.string.snorkel_two_step_title),
                Double.parseDouble(resources.getString(R.string.snorkel_two_step_lat)),
                Double.parseDouble(resources.getString(R.string.snorkel_two_step_lng)),
                resources.getString(R.string.snorkel_two_step_snippet),
                R.drawable.two_step));
        attractionArrayList.add(new Attraction(resources.getString(R.string.snorkel_south_point_title),
                Double.parseDouble(resources.getString(R.string.snorkel_south_point_lat)),
                Double.parseDouble(resources.getString(R.string.snorkel_south_point_lng)),
                resources.getString(R.string.snorkel_south_point_snippet),
                R.drawable.dive_snorkel));
    }
}
