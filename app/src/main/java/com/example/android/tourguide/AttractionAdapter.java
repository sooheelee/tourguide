package com.example.android.tourguide;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class AttractionAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public AttractionAdapter(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.custom_info, null);

        TextView tvTitle = view.findViewById(R.id.title);
        TextView tvSnippet = view.findViewById(R.id.snippet);
        ImageView imageView = view.findViewById(R.id.custom_image);

        tvTitle.setText(marker.getTitle());
        tvSnippet.setText(marker.getSnippet());

        Attraction attraction = (Attraction) marker.getTag();

//        int imageId = context.getResources().getIdentifier(attraction.getTitle(),
//                "drawable", context.getPackageName());
//        imageView.setImageResource(imageId);

        return view;
    }
}