//package com.example.android.tourguide;
//
//import android.content.Context;
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.TextView;
//
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.model.Marker;
//
//public class AttractionAdapter extends AppCompatActivity implements
//        GoogleMap.InfoWindowAdapter {
//
//    private final View mWindow;
//    private Context mContext;
////
////    @Override
//
//
//    public void CustomInfoAdapter (Context context) {
////        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info, null);
//        mContext = context;
//        mWindow = null;
//    }
////
//    private void renderText(Marker marker, View view) {
//        String title = marker.getTitle();
//        TextView tvTitle = view.findViewById(R.id.title);
//
//        if (!title.equals("")) {
//            tvTitle.setText(title);
//        }
//
//        String snippet = marker.getSnippet();
//        TextView tvSnippet = view.findViewById(R.id.snippet);
//
//        if (!snippet.equals("")) {
//            tvSnippet.setText(snippet);
//        }
//    }
//
//    @Override
//    public View getInfoWindow(Marker marker) {
//        renderText(marker, mWindow);
//        return null;
//    }
//
//    @Override
//    public View getInfoContents(Marker marker) {
//        renderText(marker, mWindow);
//        return null;
//    }
//
//
//}