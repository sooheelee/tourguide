package com.example.android.tourguide;

import android.content.Context;

/**
 * Groups information about an attraction
 */
public class Attraction {
    private String mTitle;
    private String mSnippet;
    private double[] mLatLng = NO_LAT_LNG;
    private double mLat;
    private double mLng;
    private Context mContext;
    private int mImageResourceID = NO_IMAGE_PROVIDED;
    private int mTag;

    private static final int NO_IMAGE_PROVIDED = -1;
    private static final double[] NO_LAT_LNG = {19.00000, -155.00000};

    public Attraction(Context context, String s) {
    }

    public void Attraction(Context context) {
        mTitle = "";
        mSnippet = "";
        mContext = context;
        mLat = 0;
        mLng = 0;
        mImageResourceID = -1;
        mTag = 0;
    }

    public Attraction(String title, double latitude, double longitude, String snippet, int imageResourceID) {
        mTitle = title;
        mSnippet = snippet;
        mLat = latitude;
        mLng = longitude;
        mImageResourceID = imageResourceID;
        mTag = getTag();
    }

    public Attraction(String title, double latitude, double longitude, String snippet) {
        mTitle = title;
        mSnippet = snippet;
        mLat = latitude;
        mLng = longitude;
        mTag = getTag();
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "mTitle='" + mTitle + '\'' +
                ", mSnippet='" + mSnippet + '\'' +
                ", mLatitude=" + mLat +
                ", mLongitude=" + mLng +
                ", mContext=" + mContext +
                ", mImageResourceID=" + mImageResourceID +
                ", mTag=" + mTag +
                '}';
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSnippet() {
        return mSnippet;
    }

    public double getLatitude() {
        return mLat;
    }

    public double getLongitude() {
        return mLng;
    }

    public int getImageResourceID() {
        return mImageResourceID;
    }

    public int getTag() {
        return mTag;
    }

    public boolean imageResourceExists() {
        return mImageResourceID != NO_IMAGE_PROVIDED;
    }
}
