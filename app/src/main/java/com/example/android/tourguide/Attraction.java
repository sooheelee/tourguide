package com.example.android.tourguide;

import android.content.Context;

/**
 * Groups information about an attraction
 */
public class Attraction {
    private String mTitle;
    private String mSnippet;
    private double[] mLatLng = NO_LAT_LNG;
    private Context mContext;
    private int mImageResourceID = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;
    private static final double[] NO_LAT_LNG = {19.64308, -155.99784};

    public void TextView(Context context) {
        mTitle = "";
        mSnippet = "";
        mContext = context;
    }

    public Attraction(String title, String snippet, double latitude, double longitude, int imageResourceID) {
        mTitle = title;
        mSnippet = snippet;
        mLatLng[0] = latitude;
        mLatLng[1] = longitude;
        mImageResourceID = imageResourceID;
    }

    public Attraction(String title, double latitude, double longitude, int imageResourceID) {
        mTitle = title;
        mLatLng[0] = latitude;
        mLatLng[1] = longitude;
        mImageResourceID = imageResourceID;
    }

    public Attraction(String title, String snippet, double latitude, double longitude) {
        mTitle = title;
        mSnippet = snippet;
        mLatLng[0] = latitude;
        mLatLng[1] = longitude;
    }

    public Attraction(String title, double latitude, double longitude) {
        mTitle = title;
        mLatLng[0] = latitude;
        mLatLng[1] = longitude;
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "mTitle='" + mTitle + '\'' +
                ", mSnippet='" + mSnippet + '\'' +
                ", mLatitude=" + mLatLng[0] +
                ", mLongitude=" + mLatLng[1] +
                ", mContext=" + mContext +
                ", mImageResourceID=" + mImageResourceID +
                '}';
    }

    public String getTitle() { return mTitle; }
    public String getSnippet() { return mSnippet; }
    public double getLatitude() { return mLatLng[0]; }
    public double getLongitude() { return mLatLng[1]; }
    public int getImageResourceID() { return mImageResourceID; }
    public boolean imageResourceExists() { return mImageResourceID != NO_IMAGE_PROVIDED; }
}
