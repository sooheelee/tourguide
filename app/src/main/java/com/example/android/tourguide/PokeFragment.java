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
//        attractionArrayList.add(new Attraction();
//        attractionArrayList.add(new Attraction();
//        attractionArrayList.add(new Attraction();

        MainActivity.pokeArrayList = attractionArrayList;

        PokeFragment fragment = new PokeFragment();
        return fragment;
    }
}
