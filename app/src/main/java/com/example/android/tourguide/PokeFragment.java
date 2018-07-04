package com.example.android.tourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PokeFragment extends Fragment {

    public PokeFragment() {
        // Required empty public constructor
    }

    public static PokeFragment newInstance() {

        Log.i("poke_fragment", "POKE NEW INSTANCE");
        final ArrayList<Attraction> attractionArrayList = new ArrayList<>();
        attractionArrayList.add(new Attraction("Costco Wholesale", 19.684998, -156.016477, "73-5600 Maiau St, Kailua-Kona\n(808) 331-4800\n10AM-8:30PM M-F\n10AM-6PM Sat & Sun"));
        attractionArrayList.add(new Attraction("Sack N Save Kona",	19.644461, -155.993327, "75-5595 Palani Rd, Kailua-Kona\n(808) 326-2729\n5AM-12AM daily"));
        attractionArrayList.add(new Attraction("Safeway", 19.646713, -155.989921, "75-1027 Henry St, Kailua-Kona\n(808) 329-2207\nopen 24 hours"));
        attractionArrayList.add(new Attraction("Umekes Fishmarket Bar and Grill", 	19.6421, -156.0005, "74-5563 Kaiwi St, Kailua-Kona\n11AM-9PM M-Sat\n11-5 Sun"));
        attractionArrayList.add(new Attraction("Umeke's Poke Bowls And Local Style Lunch Plates", 19.6385, -155.9911, "75-143 Hualalai Rd #105, Kailua-Kona\n(808) 329-3050\n10AM-5PM M-Sat\nclosed Sundays"));

        MainActivity.pokeArrayList = attractionArrayList;

        PokeFragment fragment = new PokeFragment();
        return fragment;
    }
}
