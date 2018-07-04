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
//        attractionArrayList.add(new Attraction();
//        attractionArrayList.add(new Attraction();
//        attractionArrayList.add(new Attraction();

        MainActivity.snorkelArrayList = attractionArrayList;

        SnorkelFragment fragment = new SnorkelFragment();
        return fragment;
    }
}
