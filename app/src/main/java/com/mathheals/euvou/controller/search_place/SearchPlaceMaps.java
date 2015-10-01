package com.mathheals.euvou.controller.search_place;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mathheals.euvou.R;

import org.json.JSONObject;

import dao.PlaceDAO;
import model.Place;

public class SearchPlaceMaps extends FragmentActivity{

    protected GoogleMap mMap; // Might be null if Google Play services APK is not available.

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

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
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

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-15.7941454, -47.8825479), 9));
        Place aux;
        JSONObject result = new PlaceDAO().searchPlaceByPartName(getIntent().getStringExtra("query"));

        try {
            for (int i = 0; i < result.length(); i++) {
                String evaluate = result.getJSONObject("" + i).getString("evaluate");
                
                aux = new Place(
                        result.getJSONObject("" + i).getString("namePlace"),
                        Float.parseFloat(((evaluate.equals("null")) ? "0" : evaluate)),
                        Double.parseDouble(result.getJSONObject("" + i).getString("longitude")),
                        Double.parseDouble(result.getJSONObject("" + i).getString("latitude")),
                        result.getJSONObject("" + i).getString("operation"),
                        result.getJSONObject("" + i).getString("description"),
                        result.getJSONObject("" + i).getString("address")
                );



                addMarkerPlace(aux);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addMarkerPlace(Place place)
    {
        mMap.addMarker(
                new MarkerOptions()
                    .title(place.getName())
                    .snippet(place.getAddress())
                    .position(new LatLng(place.getLatitude(), place.getLongitude()))
            );
    }

}
