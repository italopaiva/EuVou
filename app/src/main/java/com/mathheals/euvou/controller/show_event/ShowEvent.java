package com.mathheals.euvou.controller.show_event;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mathheals.euvou.R;

import org.json.JSONException;
import org.json.JSONObject;

import dao.EventDAO;
import model.Event;

public class ShowEvent extends Fragment {

    public ShowEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_event,container,false);
        EventDAO eventDAO = new EventDAO(this.getActivity());
        String eventName =  this.getArguments().getString("eventName");
        JSONObject eventDATA = eventDAO.searchEventByName(eventName);

        try
        {
            String eventNameDB = eventDATA.getJSONObject("0").getString("eventName");
            String eventAdress = eventDATA.getJSONObject("0").getString("dateTimeEvent");
            String eventDescription = eventDATA.getJSONObject("0").getString("eventDescription");
            String eventDateTime = eventDATA.getJSONObject("0").getString("dateTimeEvent");
            String eventLongitude = eventDATA.getJSONObject("0").getString("longitude");
            String eventLatitude = eventDATA.getJSONObject("0").getString("latitude");

            TextView nameShowLabel= (TextView) view.findViewById(R.id.nameLabel);

            nameShowLabel.setText(eventNameDB);
            /*adressShow.setText(eventAdress);
            descriptionShow.setText(eventDescription);
            dataShow.setText(eventDateTime);
*/
        }catch(JSONException ex)
        {

        }catch(NullPointerException exception)
        {
            Toast.makeText(getActivity(),"O nome não foi encontrado",Toast.LENGTH_LONG);
        }

        return view;
    }


}
