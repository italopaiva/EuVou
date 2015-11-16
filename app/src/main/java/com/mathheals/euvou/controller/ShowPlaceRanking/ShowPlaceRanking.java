package com.mathheals.euvou.controller.ShowPlaceRanking;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.mathheals.euvou.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dao.PlaceDAO;
import exception.PlaceException;
import model.Place;


public class ShowPlaceRanking extends AppCompatActivity {

    private PlaceDAO placeDAO;
    private JSONObject allPlaces;
    private ArrayList<Place> places;
    private ListView listView;
    private List<Place> placeList;

    public ShowPlaceRanking() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = (ListView) findViewById(R.id.listViewPlaces);

        allPlaces = getPlaces();

        try {
            places = passJSONOBJECtoArrayList(allPlaces);
        }catch( JSONException ex)
        {

        }catch(PlaceException ex)
        {

        }catch(ParseException ex)
        {

        }
        placeList = orderedPlaceList(places);

        PlaceAdapter placeAdapter = new PlaceAdapter(this,placeList);
        listView.setAdapter(placeAdapter);

    }

    //procura e retorna tdos os lugares cadastrados no banco
    public JSONObject getPlaces()
    {
        placeDAO = new PlaceDAO(this);
        return  placeDAO.searchAllPlaces();
    }
    //transforma os lugares cadastrados em arrayList
    public ArrayList<Place> passJSONOBJECtoArrayList(JSONObject JsonPlace) throws JSONException, PlaceException, ParseException {
        places = new ArrayList<Place>();

        for(int i = 0; i<JsonPlace.length();i++)
        {
            Place singlePlace;
            singlePlace = new Place(JsonPlace.getJSONObject("" + i).getString("namePlace"),
                    JsonPlace.getJSONObject("" + i).getString("evaluate"),
                    JsonPlace.getJSONObject("" + i).getString("longitude"),
                    JsonPlace.getJSONObject("" + i).getString("latitude"),
                    JsonPlace.getJSONObject("" + i).getString("operation"),
                    JsonPlace.getJSONObject("" + i).getString("description"),
                    JsonPlace.getJSONObject("" + i).getString("address"),
                    JsonPlace.getJSONObject("" + i).getString("phonePlace"));
            places.add(singlePlace);

        }
        return places;

    }
    //Transforma o arrayList em Lista para poder ordenar por avaliação
    public List<Place> orderedPlaceList(ArrayList<Place> places)
    {
        List<Place> placeList = new ArrayList<Place>();

        for(Place place:places)
        {
                placeList.add(place);
        }
        Collections.sort(placeList, new Comparator<Place>() {
            @Override
            public int compare(Place a, Place b) {
                if(a.getEvaluate()<b.getEvaluate())
                    return -1;
                else if(a.getEvaluate()>b.getEvaluate())
                    return 1;
                else
                    return 0;
            }
        });
        return placeList;

    }
    //
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_place_ranking, container, false);
    }



}
