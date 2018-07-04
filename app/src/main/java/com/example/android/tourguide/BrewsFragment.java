package com.example.android.tourguide;


import android.support.v4.app.Fragment;
import android.util.Log;
import java.util.ArrayList;

public class BrewsFragment extends Fragment {

    public BrewsFragment() {
        // Required empty public constructor
    }

    public static BrewsFragment newInstance() {

        Log.i("brews_fragment", "BREWS NEW INSTANCE");
        final ArrayList<Attraction> attractionArrayList = new ArrayList<>();
        attractionArrayList.add(new Attraction("Kona Brewing Company", "74 Pawai Pl, Kailua-Kona\n11am-10pm, daily\n808-334-2739", 19.64308, -155.99784));
        attractionArrayList.add(new Attraction("Big Island Brewhaus", "64-1066 Mamalahoa Hwy, Waimea\n11am-8:30pm M-Sat\n noon-8pm Sun\n808-887-1717", 20.0248, -155.6615));
        attractionArrayList.add(new Attraction("Mehana Brewing Company", "275 E Kawili St, Hilo\nTasting Room opens noon M-Sat\nCloses between 4pm and 6pm\n808-934-8211", 19.7061, -155.0692));

        MainActivity.brewsArrayList = attractionArrayList;

        BrewsFragment fragment = new BrewsFragment();
        return fragment;
    }
}
