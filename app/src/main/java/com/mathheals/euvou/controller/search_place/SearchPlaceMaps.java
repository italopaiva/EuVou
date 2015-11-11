package com.mathheals.euvou.controller.search_place;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.show_place.ShowPlaceInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import dao.PlaceDAO;
import exception.PlaceException;
import model.Place;

public class SearchPlaceMaps extends FragmentActivity implements GoogleMap.OnMarkerClickListener{

    protected GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private ArrayList<Place> places;
    private String filter;
    private Place clickedPlace;

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
        mMap.setOnMarkerClickListener(this);

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
        return new PlaceDAO(this).searchPlaceByPartName(getFilter());
    }

    private void setUpMap() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-15.7941454, -47.8825479), 9));
        setFilter(getIntent().getStringExtra("query"));

        try {
            convertJsonToPlace(searchPlaces());
            addMarkerPlace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (PlaceException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void convertJsonToPlace(JSONObject result) throws JSONException, PlaceException, ParseException {
        places = new ArrayList<>();
        if(result == null) {
            Toast.makeText(this, "Sem Resultados", Toast.LENGTH_LONG).show();
            return;
        }
        for (int i = 0; i < result.length(); i++) {
            Place aux;
            aux = new Place(
                    result.getJSONObject("" + i).getString("namePlace"),
                    result.getJSONObject("" + i).getString("evaluate"),
                    result.getJSONObject("" + i).getString("longitude"),
                    result.getJSONObject("" + i).getString("latitude"),
                    result.getJSONObject("" + i).getString("operation"),
                    result.getJSONObject("" + i).getString("description"),
                    result.getJSONObject("" + i).getString("address"),
                    result.getJSONObject("" + i).getString("phonePlace")
                    );
            places.add(aux);
        }
    }

    private void addMarkerPlace() {
        if(places != null) {
            for (int i = 0; i < places.size(); ++i) {
                mMap.addMarker(
                        new MarkerOptions()
                                .title(places.get(i).getName())
                                .snippet(places.get(i).getAddress())
                                .position(new LatLng(places.get(i).getLatitude(), places.get(i).getLongitude()))
                );
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String marke = marker.getId().substring(1);
        int id = Integer.parseInt(marke);
        select(id);
        startShowInfoActivity();
        //startShowInfoFragment();
        //Toast.makeText(getBaseContext(), clickedPlace.getName(), Toast.LENGTH_SHORT).show();
        return false;
    }

    private void select(int id) {
        //TextView campo;
        //Place place = places.get(id);
        clickedPlace = places.get(id);
/*
        campo = (TextView) findViewById(R.id.address);
        campo.setText("Endereço: " + ((place.getAddress().isEmpty()) ? "Não há" : place.getAddress()));

        campo = (TextView) findViewById(R.id.operation);
        campo.setText("Funcionamento: " + ((place.getOperation().isEmpty()) ? "Não há" : place.getOperation()));

        campo = (TextView) findViewById(R.id.phone);
        campo.setText("Telefone: " + ((place.getPhone().isEmpty()) ? "Não há" : place.getPhone()));

        campo = (TextView) findViewById(R.id.description);
        campo.setText("Descrição: " + ((place.getDescription().isEmpty()) ? "Não há" : place.getDescription()));;

        campo = (TextView) findViewById(R.id.grade);
        campo.setText("Avaliação: " + place.getEvaluate());
  */
    }
    private void startShowInfoActivity() {
        Intent intent = new Intent(this, ShowPlaceInfo.class);
        intent.putExtras(getPlaceInfoAsBundle());
        startActivity(intent);
    }

    private Bundle getPlaceInfoAsBundle() {
        Bundle placeInfo = new Bundle();
        placeInfo.putString("name", clickedPlace.getName());
        placeInfo.putString("phone", clickedPlace.getPhone());
        placeInfo.putString("addres", clickedPlace.getAddress());
        placeInfo.putString("description", clickedPlace.getDescription());
        placeInfo.putDouble("latitude", clickedPlace.getLatitude());
        placeInfo.putDouble("longitude", clickedPlace.getLongitude());
        placeInfo.putString("operation", clickedPlace.getOperation());
        return placeInfo;
    }
}
