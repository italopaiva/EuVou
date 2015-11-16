package com.mathheals.euvou.controller.ShowPlaceRanking;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.mathheals.euvou.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import dao.PlaceDAO;
import exception.PlaceException;
import model.Place;


public class ShowPlaceRanking extends Fragment {

    private PlaceDAO placeDAO;
    private JSONObject allPlaces;
    private ArrayList<Place> places;
    private ListView listView;

    public ShowPlaceRanking() {
        // Required empty public constructor
    }
    public JSONObject getPlaces()
    {
        placeDAO = new PlaceDAO(this.getActivity());
        return  placeDAO.searchAllPlaces();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void passJSONOBJECtoArrayList(JSONObject JsonPlace) throws JSONException, PlaceException, ParseException {
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

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_place_ranking, container, false);
    }



}
