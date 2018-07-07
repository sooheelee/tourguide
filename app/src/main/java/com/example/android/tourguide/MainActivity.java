package com.example.android.tourguide;

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
import android.util.Log;
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
    Bitmap brewsBitmap, snorkelBitmap, nationalParksBitmap, pokeBitmap, luauBitmap, lavaBitmap;
    LatLng bigIsland;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bigIsland = new LatLng(19.6000, -155.5500);
        sMapFragment = SupportMapFragment.newInstance();

        brewsBitmap = svgToBitmap(this, R.drawable.ic_maki_beer_15);
        snorkelBitmap = svgToBitmap(this, R.drawable.ic_sea_turtle);
        nationalParksBitmap = svgToBitmap(this, R.drawable.ic_starr_gazania_rigens_var);
        pokeBitmap = svgToBitmap(this, R.drawable.ic_maki_sushi_15);

        // TODO: Add markers or clean this up.
        luauBitmap = svgToBitmap(this, R.drawable.ic_coconut_umbrella_straw);
        lavaBitmap = svgToBitmap(this, R.drawable.ic_maki_volcano_15);

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

                String categoryInformation = "Map is interactive. Top left menu has filtering options.";

                if (categoryTag == 1) {
                    categoryInformation = "All beaches are public in Hawaii.";
                } else if (categoryTag == 2) {
                    categoryInformation = "A hike to active lava can take all day. Check current lava activity before planning.";
                } else if (categoryTag == 3) {
                    categoryInformation = "List includes stores with to-go poké. Most of these also pack cooked rice.";
                } else if (categoryTag == 4) {
                    categoryInformation = "Check out unusual brews with coconut and local tropical fruits.";
                }

                Snackbar.make(view, categoryInformation, Snackbar.LENGTH_LONG)
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
        if (id == R.id.reset_settings) {
            mMap.setMaxZoomPreference((float) 8.8);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(bigIsland));
            sMapFragment.getMapAsync(this);
            mMap.setMaxZoomPreference(30);
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

        if (id == R.id.category_snorkel_spots) {
            SnorkelFragment.newInstance();
            categoryTag = 1;
            sMapFragment.getMapAsync(this);
        } else if (id == R.id.category_national_parks) {
            NationalParksFragment.newInstance();
            categoryTag = 2;
            sMapFragment.getMapAsync(this);
        } else if (id == R.id.category_poke) {
            PokeFragment.newInstance();
            categoryTag = 3;
            sMapFragment.getMapAsync(this);
        } else if (id == R.id.category_local_brews) {
            BrewsFragment.newInstance();
            categoryTag = 4;
            sFragmentManager.beginTransaction().show(sMapFragment).commit();
            sMapFragment.getMapAsync(this);
        } else if (id == R.id.category_luau) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.gohawaii.com/islands/hawaii-big-island/things-to-do/land-activities/Luau"));
            startActivity(intent);
        } else if (id == R.id.category_lava_activity) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://volcanoes.usgs.gov/volcanoes/kilauea/multimedia_maps.html"));
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();
    }

    public void setUpMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setMinZoomPreference((float) 8.8);
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

    public void setMapMarkers(ArrayList<Attraction> selectedCategoryArrayList, Bitmap bitmap) {
        if (selectedCategoryArrayList == null) {
            return;
        }
        ArrayList<Marker> markerArrayList = new ArrayList<>();

        for (int i = 0; i < selectedCategoryArrayList.size(); i++) {
            LatLng attractionLatLng = new LatLng(selectedCategoryArrayList.get(i).getLatitude(), selectedCategoryArrayList.get(i).getLongitude());
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
            //           marker.showInfoWindow();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static Bitmap svgToBitmap(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable instanceof BitmapDrawable) {
            return BitmapFactory.decodeResource(context.getResources(), drawableId);
        } else if (drawable instanceof VectorDrawable) {
            return svgToBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("Unsupported drawable type");
        }
    }

    private static Bitmap svgToBitmap(VectorDrawable vectorImage) {
        Bitmap bitmap = Bitmap.createBitmap(vectorImage.getIntrinsicWidth(),
                vectorImage.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorImage.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorImage.draw(canvas);
        Log.i("svgToBitmap", "BITMAP");
        return bitmap;
    }
}

