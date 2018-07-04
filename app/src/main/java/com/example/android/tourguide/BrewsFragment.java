package com.example.android.tourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.WeakHashMap;

public class BrewsFragment extends Fragment {

    GoogleMap mMap;

    public BrewsFragment() {
        // Required empty public constructor
    }

    public static BrewsFragment newInstance() {

        Log.i("brews_fragment", "BREWS NEW INSTANCE");
        final ArrayList<Attraction> attractionArrayList = new ArrayList<>();
        attractionArrayList.add(new Attraction("Kona Brewing Company", 19.64308, -155.99784, "74 Pawai Pl, Kailua-Kona\n11am-10pm, daily\n808-334-2739"));
        attractionArrayList.add(new Attraction("Big Island Brewhaus", 20.0248, -155.6615, "64-1066 Mamalahoa Hwy, Waimea\n11am-8:30pm M-Sat\n noon-8pm Sun\n808-887-1717"));
        attractionArrayList.add(new Attraction("Mehana Brewing Company", 19.7061, -155.0692, "275 E Kawili St, Hilo\nTasting Room opens noon M-Sat\nCloses between 4pm and 6pm\n808-934-8211"));

        MainActivity.brewsArrayList = attractionArrayList;

        BrewsFragment fragment = new BrewsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflator.inflate(R.layout.custom_info, container, false);

        Log.i("brews_fragment", "BREWS ON CREATE VIEW");

    return rootView;
    }
}
