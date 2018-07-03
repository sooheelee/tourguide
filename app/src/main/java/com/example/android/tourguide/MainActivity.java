package com.example.android.tourguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private GoogleMap mMap;
    SupportMapFragment sMapFragment;
    //    String[] practiceObject = SOMETHING;
    public static ArrayList<Attraction> brewsArrayList;
    public static ArrayList<Attraction> attractionsArrayList;

    //    public static MapsActivity mMap;
    NavigationView navigationView = null;
    Toolbar toolbar = null;
//    String[] SOMETHING = {"Yo", "Yo", "Ma", "20.0248", "-155.6615"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sMapFragment = SupportMapFragment.newInstance();

        android.support.v4.app.FragmentManager sFragmentManager = getSupportFragmentManager();
        if (!sMapFragment.isAdded())
            sFragmentManager.beginTransaction().add(R.id.map, sMapFragment).commit();

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sMapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        android.support.v4.app.FragmentManager sFragmentManager = getSupportFragmentManager();
        mMap.clear();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (sMapFragment.isAdded())
            sFragmentManager.beginTransaction().hide(sMapFragment).commit();

        if (id == R.id.category_snorkel_spots) {

            if (!sMapFragment.isAdded())
                sFragmentManager.beginTransaction().add(R.id.map, sMapFragment).commit();
            else
                sFragmentManager.beginTransaction().show(sMapFragment).commit();

        } else if (id == R.id.category_national_parks) {
            sFragmentManager.beginTransaction().remove(sMapFragment).commit();
            sFragmentManager.beginTransaction().add(R.id.map, new BrewsFragment()).commit();

        } else if (id == R.id.category_poke) {

        } else if (id == R.id.category_local_brews) {
            BrewsFragment.newInstance();
            Log.i("brews_category", "BREWS: " + brewsArrayList.toString());

            int count = 0;
            int arrayListSize = brewsArrayList.size();
            while (arrayListSize > count) {
                Log.i("brew_category", "BREWS COUNT " + Integer.valueOf(count).toString());
                count++;
            }

            for (Attraction attraction : brewsArrayList) {
            }

        } else if (id == R.id.category_luau) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gohawaii.com/islands/hawaii-big-island/things-to-do/land-activities/Luau"));
            startActivity(intent);
        } else if (id == R.id.category_lava_activity) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://volcanoes.usgs.gov/volcanoes/kilauea/multimedia_maps.html"));
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i("onMapReady", "MAP IS READY");
        mMap = googleMap;
        setUpMap();
    }

    public void setUpMap() {
        Log.i("setUpMap", "MAP IS SETTING UP");
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setMinZoomPreference(9);

        LatLng bigIsland = new LatLng(19.5429, -155.6659);
        mMap.addMarker(new MarkerOptions().position(bigIsland).title("Island of Hawaii").snippet("YO!")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bigIsland));

        LatLng konaBrewery = new LatLng(19.64308, -155.99784);
        mMap.addMarker(new MarkerOptions().position(konaBrewery).title("Check").snippet("check")).showInfoWindow();


        // TODO: replace brewsArrayList with generic attractionsArrayList
        // For attraction in attractionsArrayList, place a marker on the map

        if (!(null == brewsArrayList)) {
            for (int counter = 0; counter < brewsArrayList.size(); counter++) {
                LatLng attractionLatLng = new LatLng(brewsArrayList.get(counter).getLatitude(), brewsArrayList.get(counter).getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(attractionLatLng)
                        .title(brewsArrayList.get(1).getTitle())
                        .snippet(brewsArrayList.get(1).getSnippet()))
                        .showInfoWindow();
            }
        } else {
            Log.i("for loop", "BREWSARRAYLIST IS NULL");
        }

    }
}
