package com.example.android.tourguide;


import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Custom object stores location information for attraction category poke, similar to sushi.
 */
public class PokeFragment extends Fragment {

    public PokeFragment() {
        // Required empty public constructor
    }

    public static PokeFragment newInstance() {

        final ArrayList<Attraction> attractionArrayList = new ArrayList<>();

        // See note in BrewsFragment.java about why these are hardcoded.
        attractionArrayList.add(new Attraction("Costco Wholesale",
                19.684998, -156.016477,
                "73-5600 Maiau St, Kailua-Kona\n10AM-8:30PM M-F, 10AM-6PM Sat&Sun\n(808) 331-4800"));
        attractionArrayList.add(new Attraction("Sack N Save Kona",
                19.644461, -155.993327,
                "75-5595 Palani Rd, Kailua-Kona\n5AM-12AM daily\n(808) 326-2729",
                R.drawable.saknsave_poke_counter));
        attractionArrayList.add(new Attraction("Safeway",
                19.646713, -155.989921,
                "75-1027 Henry St, Kailua-Kona\nOpen 24 hours\n(808) 329-2207"));
        attractionArrayList.add(new Attraction("Umeke's Fishmarket Bar and Grill",
                19.6421, -156.0005,
                "74-5563 Kaiwi St, Kailua-Kona\n11AM-9PM M-Sat, 11-5 Sun\n(808) 238-0571",
                R.drawable.umeke_poke_bowl));
        attractionArrayList.add(new Attraction("Umeke's Poke Bowls And Local Style Lunch Plates",
                19.6385, -155.9911,
                "75-143 Hualalai Rd #105, Kailua-Kona\n10AM-5PM M-Sat, closed Sundays\n(808) 329-3050"));

        MainActivity.pokeArrayList = attractionArrayList;

        PokeFragment fragment = new PokeFragment();
        return fragment;
    }
}
