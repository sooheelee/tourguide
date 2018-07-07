package com.example.android.tourguide;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class AttractionAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public AttractionAdapter(Context ctx) {
        context = ctx;
    }

    // Required empty public constructor
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    /**
     * Generates custom layout for map marker pop-up info windows. Changes layout parameters for
     * different aspect ratio images, e.g. portrait, landscape or panoramic. If no image is associated
     * with attraction, then gracefully removes this portion of the layout.
     *
     * @param marker
     * @return
     */
    @Override
    public View getInfoContents(Marker marker) {

        View view = ((Activity) context).getLayoutInflater()
                .inflate(R.layout.custom_info, null);

        TextView tvTitle = view.findViewById(R.id.title);
        TextView tvSnippet = view.findViewById(R.id.snippet);
        ImageView ivImagery = view.findViewById(R.id.custom_image);

        tvTitle.setText(marker.getTitle());
        tvTitle.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        tvTitle.setTextSize(context.getResources().getDimension(R.dimen.tv_title_textsize));

        tvSnippet.setText(marker.getSnippet());
        tvSnippet.setTextColor(ContextCompat.getColor(context, android.R.color.black));
        tvSnippet.setTextSize(context.getResources().getDimension(R.dimen.tv_snippet_textsize));

        ArrayList<Attraction> currentCategory = null;

        if (MainActivity.categoryTag == 1) {
            currentCategory = MainActivity.snorkelArrayList;
        } else if (MainActivity.categoryTag == 2) {
            currentCategory = MainActivity.nationalParksArrayList;
        } else if (MainActivity.categoryTag == 3) {
            currentCategory = MainActivity.pokeArrayList;
        } else if (MainActivity.categoryTag == 4) {
            currentCategory = MainActivity.brewsArrayList;
        }

        int lengthOfArray = currentCategory.size();
        int imageResourceId = 0;
        for (int i = 0; i < lengthOfArray; i++) {
            if (currentCategory.get(i).getTitle().equals(marker.getTitle())) {
                imageResourceId = currentCategory.get(i).getImageResourceID();
            }
            if (imageResourceId != -1) {
                ivImagery.setImageResource(imageResourceId);
                if (imageResourceId != 0) {
                    int imageHeight = context.getResources().getDrawable(imageResourceId).getIntrinsicHeight();
                    int imageWidth = context.getResources().getDrawable(imageResourceId).getIntrinsicWidth();
                    if (imageHeight / imageWidth < 0.4) {
                        ivImagery.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        ivImagery.setAdjustViewBounds(true);
                    } else if (imageHeight / imageWidth >= 1.0) {
                        ivImagery.getLayoutParams().height = (int) context.getResources().getDimension(R.dimen.image_aspect_long);
                        ivImagery.getLayoutParams().width = (int) context.getResources().getDimension(R.dimen.image_aspect_short);
                    }
                }
            } else {
                ivImagery.setVisibility(View.GONE);
            }
        }
        return view;
    }
}