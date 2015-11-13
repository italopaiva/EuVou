package com.mathheals.euvou.controller.show_place;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RatingBar.*;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

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
    private String address;
    private float grade;

    private TextView addressText;
    private TextView operationText;
    private TextView phoneText;
    private TextView descriptionText;
    private TextView gradeText;

    private Button showMapButton;
    private Button hideMapButton;
    private SupportMapFragment mMapFragment;

    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_place_info);
        setShowMapButton((Button) findViewById(R.id.button_show_map));
        setHideMapButton((Button) findViewById(R.id.button_hide_map));
        setPlaceInfo();
        setAllTextViews();
        setUpMapIfNeeded();
        mMapFragment.getView().setVisibility(View.INVISIBLE);
        setRatingBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_place_info, menu);
        return true;
    }

    private void setRatingBar() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar arg0, float rateValue, boolean arg2) {Toast.makeText(getBaseContext(), "" + rateValue, Toast.LENGTH_SHORT).show();
            }
        });
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
            mMapFragment = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_show_place_info_map));
            mMap = mMapFragment.getMap();
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
                        .snippet(getAddress())
                        .position(new LatLng(getLatitude(), getLongitude()))
        );
    }

    public void showPlaceInfoOnClick(View view) {
        switch(view.getId()) {
            case R.id.button_show_map:
                setUpMapIfNeeded();
                hideMapButton.setVisibility(View.VISIBLE);
                showMapButton.setVisibility(View.GONE);
                mMapFragment.getView().setVisibility(View.VISIBLE);
                break;
            case R.id.button_hide_map:
                hideMapButton.setVisibility(View.GONE);
                showMapButton.setVisibility(View.VISIBLE);
                mMapFragment.getView().setVisibility(View.INVISIBLE);
                break;
        }
    }
    private void setPlaceInfo() {
        Intent intent = getIntent();
        setName(intent.getStringExtra("name"));
        setPhone(intent.getStringExtra("phone"));
        setAddress(intent.getStringExtra("address"));
        setGrade(intent.getFloatExtra("grade", 0.0F));
        setDescription(intent.getStringExtra("description"));
        setLatitude(intent.getDoubleExtra("latitude", 0.0));
        setLongitude(intent.getDoubleExtra("longitude", 0.0));
        setOperation(intent.getStringExtra("operation"));
    }

    private void setGrade(float grade) {
        this.grade = grade;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private String getAddress() {
        return address;
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

    private void setAddressText(String adressText) {
        this.addressText = (TextView) findViewById(R.id.address_text);
        this.addressText.setText(adressText);
        this.addressText.setMovementMethod(new ScrollingMovementMethod());
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
        this.descriptionText.setMovementMethod(new ScrollingMovementMethod());
    }

    private void setAllTextViews() {
        setAddressText(address);
        setOperationText(operation);
        setPhoneText(phone);
        setGradeText(Float.toString(grade));
        setDescriptionText(description);
    }

    public void setShowMapButton(Button showMapButton) {
        this.showMapButton = showMapButton;
    }

    public void setHideMapButton(Button hideMapButton) {
        this.hideMapButton = hideMapButton;
    }
}
