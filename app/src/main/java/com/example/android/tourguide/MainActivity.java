package com.example.android.tourguide;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Tour Guide app for navigating attractions of particular interest on the Island of Hawai'i,
 * a.k.a. Big Island. App uses Google Maps API and is interactive, e.g. allows zooming in for
 * details.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private GoogleMap mMap;
    public static SupportMapFragment sMapFragment;

    public static int categoryTag;
    public static ArrayList<Attraction> brewsArrayList;
    public static ArrayList<Attraction> snorkelArrayList;
    public static ArrayList<Attraction> nationalParksArrayList;
    public static ArrayList<Attraction> pokeArrayList;

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    Bitmap brewsBitmap, snorkelBitmap, nationalParksBitmap, pokeBitmap;
    LatLng bigIsland;

    /**
     * Creates instance of Tour Guide app.
     * <p>
     * Attractions are organized in left drawer menu into the following categories.
     * - Snorkel spots
     * - National parks
     * - Places to get Poke
     * - Local breweries
     * <p>
     * The left drawer menu also provides links to current information for two types of events.
     * - Website listing upcoming Luaus
     * - USGS website showing up-to-date lava flow activity
     * Either click upper-right triple-bar or swipe right to access drawer menu.
     * <p>
     * Clicking on an attraction type adds a list of markers to the map at appropriate latitude and
     * longitude coordinates. Clicking on an attraction marker pops-up details for the attraction.
     * Clicking upper-right menu gives option to reset the zoom of the map to show the entire island.
     * <p>
     * Some attractions also show a photo. All photos are by S. H. Lee (author of app) or J. Hess
     * (with permission), from a vacation in April of 2017. SVG graphics are from Wikimedia Commons,
     * at https://commons.wikimedia.org/wiki/Main_Page.
     * <p>
     *
     * @param savedInstanceState saves instance to a bundle
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bigIsland = new LatLng(Double.parseDouble(getResources().getString(R.string.zoom_big_island_lat)),
                Double.parseDouble(getResources().getString(R.string.zoom_big_island_lng)));
        sMapFragment = SupportMapFragment.newInstance();

        brewsBitmap = svgToBitmap(this, R.drawable.ic_maki_beer_15);
        snorkelBitmap = svgToBitmap(this, R.drawable.ic_sea_turtle);
        nationalParksBitmap = svgToBitmap(this, R.drawable.ic_starr_gazania_rigens_var);
        pokeBitmap = svgToBitmap(this, R.drawable.ic_maki_sushi_15);

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

                String categoryInformation = getResources().getString(R.string.info_start);

                if (categoryTag == 1) {
                    categoryInformation = getResources().getString(R.string.info_snorkel);
                } else if (categoryTag == 2) {
                    categoryInformation = getResources().getString(R.string.info_parks);
                } else if (categoryTag == 3) {
                    categoryInformation = getResources().getString(R.string.info_poke);
                } else if (categoryTag == 4) {
                    categoryInformation = getResources().getString(R.string.info_brews);
                }
                Snackbar.make(view, categoryInformation, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        BrewsFragment brews = new BrewsFragment(getResources());
        brewsArrayList = brews.attractionArrayList;
        NationalParksFragment nationalParks = new NationalParksFragment(getResources());
        nationalParksArrayList = nationalParks.attractionArrayList;
        PokeFragment poke = new PokeFragment(getResources());
        pokeArrayList = poke.attractionArrayList;
        SnorkelFragment snorkel = new SnorkelFragment(getResources());
        snorkelArrayList = snorkel.attractionArrayList;

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sMapFragment.getMapAsync(this);
    }

    /**
     * Handles actions related to left drawer menu
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Creates upper-right menu options
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Handles upper-right menu option selections.
     * 'Reset zoom' resets map to show Big Island.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.reset_settings) {
            mMap.setMaxZoomPreference((float) 8.8);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(bigIsland));
            sMapFragment.getMapAsync(this);
            mMap.setMaxZoomPreference(30);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Handles left-drawer menu selections.
     * Either populates map with markers for given attraction category attractions, or
     * opens browser to URL on event topic.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mMap.clear();
        int id = item.getItemId();

        if (id == R.id.category_snorkel_spots) {
            categoryTag = 1;
            sMapFragment.getMapAsync(this);
        } else if (id == R.id.category_national_parks) {
            categoryTag = 2;
            sMapFragment.getMapAsync(this);
        } else if (id == R.id.category_poke) {
            categoryTag = 3;
            sMapFragment.getMapAsync(this);
        } else if (id == R.id.category_local_brews) {
            categoryTag = 4;
            sMapFragment.getMapAsync(this);
        } else if (id == R.id.category_luau) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.luau_gohawaii_url)));
            startActivity(intent);
        } else if (id == R.id.category_lava_activity) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.lava_usgs_url)));
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Handles map resource activity
     *
     * @param googleMap Map resource plugs in Google Maps API
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();
    }

    /**
     * Sets map type to topological map, caps minimum zoom to level appropriate for viewing islands
     * in the area, and centers map view to show Big Island. Sets up attraction category markers if
     * a category had been selected using a bitmap resource.
     */
    public void setUpMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setMinZoomPreference((float) Double.parseDouble(getResources()
                .getString(R.string.zoom_big_island_zoom_level)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bigIsland));

        if (categoryTag == 1) {
            setMapMarkers(snorkelArrayList, snorkelBitmap);
        } else if (categoryTag == 2) {
            setMapMarkers(nationalParksArrayList, nationalParksBitmap);
        } else if (categoryTag == 3) {
            setMapMarkers(pokeArrayList, pokeBitmap);
        } else if (categoryTag == 4) {
            setMapMarkers(brewsArrayList, brewsBitmap);
        }
    }

    /**
     * Takes an array list of attractions generated in (@link Attraction} class and marker bitmap image.
     * Iterates over each attraction in the list. Adds each attraction to new marker array list and
     * includes (i) the category-specific bitmap marker, (ii) the attraction's latitude and longitude,
     * (iii) title of attraction and (iv) snippet of information for the attraction. Then, for each
     * marker in the marker array list, uses the attractions adapter code {@link AttractionAdapter}
     * to generate custom information window views, which remain invisible until marker is clicked.
     *
     * @param selectedCategoryArrayList
     * @param bitmap
     */
    public void setMapMarkers(ArrayList<Attraction> selectedCategoryArrayList, Bitmap bitmap) {
        if (selectedCategoryArrayList == null) {
            return;
        }
        ArrayList<Marker> markerArrayList = new ArrayList<>();

        for (int i = 0; i < selectedCategoryArrayList.size(); i++) {
            LatLng attractionLatLng = new LatLng(selectedCategoryArrayList.get(i).getLatitude(),
                    selectedCategoryArrayList.get(i).getLongitude());
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .position(attractionLatLng)
                    .title(selectedCategoryArrayList.get(i).getTitle())
                    .snippet(selectedCategoryArrayList.get(i).getSnippet()));
            markerArrayList.add(marker);
        }
        for (Marker marker : markerArrayList) {
            AttractionAdapter attractionAdapter = new AttractionAdapter(this);
            mMap.setInfoWindowAdapter(attractionAdapter);
        }
    }

    /**
     * Takes SVG format or bitmap format drawable graphic and converts to drawable graphic using
     * {@link BitmapFactory}. Throws error if given unrecognizable format. Kudos to StackOverflow.
     *
     * @param context
     * @param drawableId
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static Bitmap svgToBitmap(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable instanceof BitmapDrawable) {
            return BitmapFactory.decodeResource(context.getResources(), drawableId);
        } else if (drawable instanceof VectorDrawable) {
            return svgToBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException(String
                    .valueOf(R.string.illegal_argument_exception_svgtobitmap));
        }
    }

    /**
     * Takes SVG format graphic and converts to bitmap.
     *
     * @param vectorImage
     * @return
     */
    private static Bitmap svgToBitmap(VectorDrawable vectorImage) {
        Bitmap bitmap = Bitmap.createBitmap(vectorImage.getIntrinsicWidth(),
                vectorImage.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorImage.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorImage.draw(canvas);
        return bitmap;
    }
}

