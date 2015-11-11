package com.mathheals.euvou.controller.show_place;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mathheals.euvou.R;

public class ShowPlaceInfo extends FragmentActivity {

    protected GoogleMap mMap;

    private String name;
    private String phone;
    private String operation;
    private String description;
    private double longitude;
    private double latitude;
    private String adress;
    private float grade;

    private TextView addressText;
    private TextView operationText;
    private TextView phoneText;
    private TextView descriptionText;
    private TextView gradeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_place_info);
        setPlaceInfo();
        setUpMapIfNeeded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_place_info, menu);
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


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_show_place_info_map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    private void setUpMap() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(getLatitude(), getLongitude()), 9));
        markPlaceOnMap();
    }

    private void markPlaceOnMap() {

        mMap.addMarker(
                new MarkerOptions()
                        .title(getName())
                        .snippet(getAdress())
                        .position(new LatLng(getLatitude(), getLongitude()))
        );
    }

    private void setPlaceInfo() {
        Intent intent = getIntent();
        setName(intent.getStringExtra("name"));
        setPhone(intent.getStringExtra("phone"));
        setAdress(intent.getStringExtra("adress"));
        setGrade(intent.getFloatExtra("grade", 0.0F));
        setDescription(intent.getStringExtra("description"));
        setLatitude(intent.getDoubleExtra("latitude", 0.0));
        setLongitude(intent.getDoubleExtra("longitude", 0.0));
        setOperation(intent.getStringExtra("operation"));
    }

    private void setGrade(float grade) {
        this.grade = grade;
    }

    private void setAdress(String adress) {
        this.adress = adress;
    }

    private String getAdress() {
        return adress;
    }

    private float getGrade() {
        return grade;
    }

    private double getLongitude() {
        return longitude;
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private String getOperation() {
        return operation;
    }

    private void setOperation(String operation) {
        this.operation = operation;
    }

    private String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private double getLatitude() {
        return latitude;
    }

    private void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private void setAdressText(String adressText) {
        this.addressText = (TextView) findViewById(R.id.address_text);
        this.addressText.setText(adressText);
    }

    private void setOperationText(String operationText) {
        this.operationText = (TextView) findViewById(R.id.operation_text);
        this.operationText.setText(operationText);
    }

    private void setPhoneText(String phoneText) {
        this.phoneText = (TextView) findViewById(R.id.phone_text);
        this.phoneText.setText(phoneText);
    }

    private void setGradeText(String gradeText) {
        this.gradeText = (TextView) findViewById(R.id.grade_text);
        this.gradeText.setText(gradeText);
    }

    private void setDescriptionText(String descriptionText) {
        this.descriptionText = (TextView) findViewById(R.id.description_text);
        this.descriptionText.setText(descriptionText);
    }
}
