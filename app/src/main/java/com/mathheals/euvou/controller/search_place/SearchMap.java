package com.mathheals.euvou.controller.search_place;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import dao.PlaceDAO;
import model.Place;

/**
 * Created by viny on 30/09/15.
 */
public class SearchMap implements OnMapReadyCallback {

    private GoogleMap gMap;
    private List<Place> placeList;

    public SearchMap(String parteName)
    {
        Place aux;
        JSONObject result = new PlaceDAO().searchPlaceByPartName(parteName);

        try {
            for (int i = 0; i < result.length(); i++) {
                aux = new Place(
                        result.getJSONObject("" + i).getString("namePlace"),
                        Float.parseFloat(result.getJSONObject("" + i).getString("evaluate")),
                        Double.parseDouble(result.getJSONObject("" + i).getString("longitude")),
                        Double.parseDouble(result.getJSONObject("" + i).getString("latitude")),
                        result.getJSONObject("" + i).getString("operation"),
                        result.getJSONObject("" + i).getString("description"),
                        result.getJSONObject("" + i).getString("address")
                );
                placeList.add(aux);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        for (Place place: placeList) {
            /*passa parametro para metodo addMarkerPlace da activity MapsActivity*/
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
