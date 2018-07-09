package com.example.android.tourguide;


import android.content.res.Resources;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Custom object storing location information for attraction category local breweries.
 */
public class BrewsFragment {

    public ArrayList<Attraction> attractionArrayList;

    public BrewsFragment(Resources resources) {
        attractionArrayList = new ArrayList<>();

        // These hardcoded values are stored as string resources in the strings.xml. However, when
        // using strings resources here, either with getResources().getString() or with
        // Double.parseDouble(getResources().getString()), the following errors occur:
        // (i) Either BrewsFragment errors with "non-static method getResources() cannot be referenced from a static context"
        // OR (ii) MainActivity errors with "non-static method newInstance() cannot be referenced from a static context".
        // Using a private Context context variable, e.g. 'context.getResources', allows build but then
        // subsequently crashes app. Next thing to try is importing json format data.
        attractionArrayList.add(new Attraction(resources.getString(R.string.brews_kona_title),
                19.64308, -155.99784,
                "74 Pawai Pl, Kailua-Kona\n11am-10pm, daily\n(808) 334-2739",
                R.drawable.kona_brewing));
        attractionArrayList.add(new Attraction("Big Island Brewhaus",
                20.0248, -155.6615,
                "64-1066 Mamalahoa Hwy, Waimea\n11am-8:30pm M-Sat, noon-8pm Sun\n(808) 887-1717"));
        attractionArrayList.add(new Attraction("Mehana Brewing Company",
                19.7061, -155.0692,
                "275 E Kawili St, Hilo\nTasting Room opens noon M-Sat; closes between 4pm and 6pm\n(808) 934-8211"));
    }
}
