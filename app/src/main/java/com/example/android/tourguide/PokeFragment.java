package com.example.android.tourguide;


import android.content.res.Resources;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Custom object stores location information for attraction category poke, similar to sushi.
 */
public class PokeFragment {

    ArrayList<Attraction> attractionArrayList;

    public PokeFragment(Resources resources) {
        attractionArrayList = new ArrayList<>();

        attractionArrayList.add(new Attraction(resources.getString(R.string.poke_costco_title),
                Double.parseDouble(resources.getString(R.string.poke_costco_lat)),
                Double.parseDouble(resources.getString(R.string.poke_costco_lng)),
                resources.getString(R.string.poke_costco_snippet)));
        attractionArrayList.add(new Attraction(resources.getString(R.string.poke_sack_n_save_title),
                Double.parseDouble(resources.getString(R.string.poke_sack_n_save_lat)),
                Double.parseDouble(resources.getString(R.string.poke_sack_n_save_lng)),
                resources.getString(R.string.poke_sack_n_save_snippet),
                R.drawable.saknsave_poke_counter));
        attractionArrayList.add(new Attraction(resources.getString(R.string.poke_safeway_title),
                Double.parseDouble(resources.getString(R.string.poke_safeway_lat)),
                Double.parseDouble(resources.getString(R.string.poke_safeway_lng)),
                resources.getString(R.string.poke_safeway_snippet)));
        attractionArrayList.add(new Attraction(resources.getString(R.string.poke_umeke_grill_title),
                Double.parseDouble(resources.getString(R.string.poke_umeke_grill_lat)),
                Double.parseDouble(resources.getString(R.string.poke_umeke_grill_lng)),
                resources.getString(R.string.poke_umeke_grill_snippet),
                R.drawable.umeke_poke_bowl));
        attractionArrayList.add(new Attraction(resources.getString(R.string.poke_umeke_lunch_title),
                Double.parseDouble(resources.getString(R.string.poke_umeke_lunch_lat)),
                Double.parseDouble(resources.getString(R.string.poke_umeke_lunch_lng)),
                resources.getString(R.string.poke_umeke_lunch_snippet)));
    }
}
