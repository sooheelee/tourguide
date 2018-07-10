package com.example.android.tourguide;


import android.content.res.Resources;

import java.util.ArrayList;

/**
 * Custom object stores location information for attraction category national parks.
 */
public class NationalParksFragment {

    ArrayList<Attraction> attractionArrayList;

    public NationalParksFragment(Resources resources) {
        attractionArrayList = new ArrayList<>();

        attractionArrayList.add(new Attraction(resources.getString(R.string.parks_volcano_title),
                Double.parseDouble(resources.getString(R.string.parks_volcano_lat)),
                Double.parseDouble(resources.getString(R.string.parks_volcano_lng)),
                resources.getString(R.string.parks_volcano_snippet),
                R.drawable.lava_flowing_into_ocean));
        attractionArrayList.add(new Attraction(resources.getString(R.string.parks_puuhonuao_title),
                Double.parseDouble(resources.getString(R.string.parks_puuhonuao_lat)),
                Double.parseDouble(resources.getString(R.string.parks_puuhonuao_lng)),
                resources.getString(R.string.parks_puuhonuao_snippet),
                R.drawable.puuhonua_o_honaunau));
    }
}
