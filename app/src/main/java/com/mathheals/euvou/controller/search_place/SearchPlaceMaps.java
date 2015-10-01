package com.mathheals.euvou.controller.search_place;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mathheals.euvou.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dao.PlaceDAO;
import model.Place;

public class SearchPlaceMaps extends FragmentActivity{

    protected GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private String filter;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private JSONObject searchPlaces()
    {
        return new PlaceDAO().searchPlaceByPartName(getFilter());
    }

    private void setUpMap() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-15.7941454, -47.8825479), 9));
        setFilter(getIntent().getStringExtra("query"));
        try {
            addMarkerPlace(convertJsonToPlace(searchPlaces()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<Place> convertJsonToPlace(JSONObject result) throws JSONException {
        ArrayList<Place> placeList = new ArrayList<>();

        for (int i = 0; i < result.length(); i++) {
            String evaluate = result.getJSONObject("" + i).getString("evaluate");
            Place aux;
            aux = new Place(
                    result.getJSONObject("" + i).getString("namePlace"),
                    Float.parseFloat(((evaluate.equals("null")) ? "0" : evaluate)),
                    Double.parseDouble(result.getJSONObject("" + i).getString("longitude")),
                    Double.parseDouble(result.getJSONObject("" + i).getString("latitude")),
                    result.getJSONObject("" + i).getString("operation"),
                    result.getJSONObject("" + i).getString("description"),
                    result.getJSONObject("" + i).getString("address")
            );
            placeList.add(aux);
        }
        return placeList;
    }

    private void addMarkerPlace(ArrayList<Place> places) {
        for (Place place : places) {
            mMap.addMarker(
                    new MarkerOptions()
                            .title(place.getName())
                            .snippet(place.getAddress())
                            .position(new LatLng(place.getLatitude(), place.getLongitude()))
            );
        }
    }

}
