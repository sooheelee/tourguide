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
//        attractionArrayList.add(new Attraction();
//        attractionArrayList.add(new Attraction();
//        attractionArrayList.add(new Attraction();

        MainActivity.nationalParksArrayList = attractionArrayList;

        NationalParksFragment fragment = new NationalParksFragment();
        return fragment;
    }
}
