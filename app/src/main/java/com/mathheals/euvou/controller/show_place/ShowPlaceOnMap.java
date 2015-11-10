package com.mathheals.euvou.controller.show_place;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mathheals.euvou.R;

import org.json.JSONException;

import java.text.ParseException;

import exception.PlaceException;

public class ShowPlaceOnMap extends FragmentActivity {

    private GoogleMap mMap;
    private String name;
    private String phone;
    private String operation;
    private String description;
    private double latitue;
    private double longitude;
    private String adress;
    private float grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_place_on_map);
        setPlaceInfo(savedInstanceState);
        setUpMapIfNeeded();
    }

    private void setPlaceInfo(Bundle savedInstanceState) {
        setName(savedInstanceState.getString("name"));
        setPhone(savedInstanceState.getString("phone"));
        setAdress(savedInstanceState.getString("adress"));
        setGrade(savedInstanceState.getFloat("grade"));
        setDescription(savedInstanceState.getString("description"));
        setLatitue(savedInstanceState.getDouble("latitue"));
        setLongitude(savedInstanceState.getDouble("longitute"));
        setOperation(savedInstanceState.getString("operation"));
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.show_place_on_maps_fragment))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(getLatitue(), getLongitude()), 9));
        markPlaceOnMap();
    }

    private void markPlaceOnMap() {

        mMap.addMarker(
                new MarkerOptions()
                        .title(getName())
                        .snippet(getAdress())
                        .position(new LatLng(getLatitue(), getLongitude()))
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_place_on_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public double getLatitue() {
        return latitue;
    }

    public void setLatitue(double latitue) {
        this.latitue = latitue;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
