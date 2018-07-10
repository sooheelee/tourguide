package com.example.android.tourguide;


import android.content.res.Resources;

import java.util.ArrayList;

/**
 * Custom object stores location information for attraction category local breweries.
 */
public class BrewsFragment {

    ArrayList<Attraction> attractionArrayList;

    BrewsFragment(Resources resources) {
        attractionArrayList = new ArrayList<>();
        attractionArrayList.add(new Attraction(resources.getString(R.string.brews_kona_title),
                Double.parseDouble(resources.getString(R.string.brews_kona_lat)),
                Double.parseDouble(resources.getString(R.string.brews_kona_lng)),
                resources.getString(R.string.brews_kona_snippet),
                R.drawable.kona_brewing));
        attractionArrayList.add(new Attraction(resources.getString(R.string.brews_big_island_title),
                Double.parseDouble(resources.getString(R.string.brews_big_island_lat)),
                Double.parseDouble(resources.getString(R.string.brews_big_island_lng)),
                resources.getString(R.string.brews_big_island_snippet)));
        attractionArrayList.add(new Attraction(resources.getString(R.string.brews_mehana_title),
                Double.parseDouble(resources.getString(R.string.brews_mehana_lat)),
                Double.parseDouble(resources.getString(R.string.brews_mehana_lng)),
                resources.getString(R.string.brews_mehana_snippet)));
    }
}
