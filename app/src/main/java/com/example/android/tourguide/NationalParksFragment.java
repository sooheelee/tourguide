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
public class NationalParksFragment extends Fragment {

    public NationalParksFragment() {
        // Required empty public constructor
    }

    public static NationalParksFragment newInstance() {

        Log.i("national_parks_fragment", "NATIONALPARKS NEW INSTANCE");
        final ArrayList<Attraction> attractionArrayList = new ArrayList<>();
        attractionArrayList.add(new Attraction("Hawai'i Volcanoes National Park", 19.4194, -155.2885, "Kīlauea Visitor Center\n1 Crater Rim Drive, Volcano\n(808) 985-6000"));
        attractionArrayList.add(new Attraction("Pu'uhonua O Hōnaunau National Historical Park", 19.4215, -155.9105, "State Hwy 160, Hōnaunau\n(808) 328-2326"));

        MainActivity.nationalParksArrayList = attractionArrayList;

        NationalParksFragment fragment = new NationalParksFragment();
        return fragment;
    }
}
