package com.mathheals.euvou;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        addMarkerInMap(-48.191045, -15.664718, "Unidade de Brazlândia",
                "Atividade Física Orientada, Atletismo, Basquete, Bocha," +
                        " Est Básica, Est Global, Futebol De Areia, Futebol Society," +
                        " Futsal, Handebol, Hidroginástica, Luta Olímpica, Natação," +
                        " Parabadminton, Voleibol.");
        addMarkerInMap(-48.137818, -15.809371,"Unidade da Ceilândia","Atividade Física" +
                " Orientada, Atletismo, Basquetebol, Capoeira, Capoterapia, Futebol De " +
                "Areia, Futebol Society, Futsal, Ginástica Rítmica, Ginástica Rítmica, " +
                "Ginástica/ Caminhada, Hidroginástica, Judô, Karatê, Natação, Pilates, " +
                "Vôlei De Areia, Voleibol.");
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
    }

    public void addMarkerInMap(double lon, double lat, String name, String desc)
    {
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat,lon)).title(name).snippet(desc));
    }
}
