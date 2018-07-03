package com.example.android.tourguide;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BrewsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        final ArrayList<Attraction> attractionArrayList = new ArrayList<>();
        MainActivity.brewsArrayList.add(new Attraction("Kona Brewing Company", "74 Pawai Pl, Kailua-Kona\n11am-10pm, daily\n808-334-2739", 19.64308, -155.99784));
        MainActivity.brewsArrayList.add(new Attraction("Big Island Brewhaus", "64-1066 Mamalahoa Hwy, Waimea\n11am-8:30pm M-Sat\n noon-8pm Sun\n808-887-1717", 20.0248, -155.6615));
        MainActivity.brewsArrayList.add(new Attraction("Mehana Brewing Company", "275 E Kawili St, Hilo\nTasting Room opens noon M-Sat\nCloses between 4pm and 6pm\n808-934-8211", 19.7061, -155.0692));
        View view = inflater.inflate(R.layout.content_main, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    // Remember CTRL+o gives override methods
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment fragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
//        float minZoom = mMap.getMinZoomLevel();
//        float maxZoom = mMap.getMaxZoomLevel();
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setMinZoomPreference(5);
//        LatLngBounds BIG_ISLAND = new LatLngBounds(new LatLng(19.52, -153.8), new LatLng(20.92, -155.7));
//        mMap.setLatLngBoundsForCameraTarget(BIG_ISLAND);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(19.5429, -155.6659);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").snippet("Actually Hawaii")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
