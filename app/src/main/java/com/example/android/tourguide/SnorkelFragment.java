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
public class SnorkelFragment extends Fragment {

    public SnorkelFragment() {
        // Required empty public constructor
    }

    public static SnorkelFragment newInstance() {

        Log.i("snorkel_fragment", "SNORKEL NEW INSTANCE");
        final ArrayList<Attraction> attractionArrayList = new ArrayList<>();
        attractionArrayList.add(new Attraction("Manini'owali Beach (Kua Bay)", 19.810249, -156.006523,	"723990 Mamalahoa Hwy, Kailua-Kona"));
        attractionArrayList.add(new Attraction("Makalawena Beach", 19.791194, -156.028506,	"via Kekaha Kai Beach Park\nHI-19, Kailua-Kona"));
        attractionArrayList.add(new Attraction("Mahai'ula Beach", 19.7867, -156.0400, "via Kekaha Kai Beach Park\nHI-19, Kailua-Kona", R.drawable.pano_20170412_133510));
        attractionArrayList.add(new Attraction("Two Step", 19.423212, -155.911564, "Honaunau Beach Rd, Captain Cook"));
        attractionArrayList.add(new Attraction("South Point Cliff Dive\n(Ka Lae Point)", 18.9136, -155.6833, "Ka Lae Rd, Naalehu", R.drawable.dive_snorkel));

        MainActivity.snorkelArrayList = attractionArrayList;

        SnorkelFragment fragment = new SnorkelFragment();
        return fragment;
    }
}
