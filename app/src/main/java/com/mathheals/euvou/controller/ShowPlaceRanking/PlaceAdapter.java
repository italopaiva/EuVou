package com.mathheals.euvou.controller.ShowPlaceRanking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mathheals.euvou.R;

import java.util.ArrayList;
import java.util.List;

import model.Place;

/**
 * Created by geovanni on 16/11/15.
 */
public class PlaceAdapter extends ArrayAdapter<Place> {
    public PlaceAdapter(Context context, List<Place> places) {
        super(context, 0,places);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Place place = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_show_place_ranking, parent, false);
        }
        TextView placeName = (TextView) convertView.findViewById(R.id.placeName);
        TextView placeEvaluation = (TextView) convertView.findViewById(R.id.placeEvaluation);

        placeName.setText(place.getName());
        placeEvaluation.setText(place.getEvaluate().toString());
        return convertView;
    }
}
