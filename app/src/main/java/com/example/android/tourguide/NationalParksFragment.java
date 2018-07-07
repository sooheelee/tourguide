package com.example.android.tourguide;


import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NationalParksFragment extends Fragment {

    public NationalParksFragment() {
        // Required empty public constructor
    }

    public static NationalParksFragment newInstance() {

        final ArrayList<Attraction> attractionArrayList = new ArrayList<>();
        attractionArrayList.add(new Attraction("Hawai'i Volcanoes National Park", 19.4194, -155.2885, "Kīlauea Visitor Center\n1 Crater Rim Drive, Volcano\n(808) 985-6000", R.drawable.lava_flowing_into_ocean));
        attractionArrayList.add(new Attraction("Pu'uhonua O Hōnaunau National Historical Park", 19.4215, -155.9105, "State Hwy 160, Hōnaunau\n(808) 328-2326", R.drawable.puuhonua_o_honaunau));

        MainActivity.nationalParksArrayList = attractionArrayList;

        NationalParksFragment fragment = new NationalParksFragment();
        return fragment;
    }
}
