package com.mathheals.euvou.controller.edit_event;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.event_registration.LocalEventActivity;
import com.mathheals.euvou.controller.utility.Mask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Vector;

import dao.CategoryDAO;
import dao.EventCategoryDAO;
import dao.EventDAO;
import exception.EventException;
import model.Event;


public class EditEventFragment extends Fragment implements View.OnClickListener {
    private static int idEvent=12;
    private static final String SUCCESSFULL_UPDATE_MESSAGE = "Evento alterado com sucesso :)";
    private String latitude;
    private String longitude;
    Vector<String> categories= new Vector<>();

    public EditEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_event, container, false);

        return view;
    }

    private void updateEvent(Event event){
        EventDAO eventDAO = new EventDAO(getActivity());
        eventDAO.updateEvent(event);
    }

    public void addEventCategories(View v){
        if(v.getId() == R.id.optionCinema){
            CheckBox cinemaCheckBox = (CheckBox) v;
            categories.add(cinemaCheckBox.getText().toString());
        }else if(v.getId() == R.id.optionEducation) {
            CheckBox educationCheckBox = (CheckBox) v;
            categories.add(educationCheckBox.getText().toString());
        }else if(v.getId() == R.id.optionExposition){
            CheckBox expositionCheckBox = (CheckBox) v;
            categories.add(expositionCheckBox.getText().toString());
        }else if(v.getId() == R.id.optionMuseum){
            CheckBox museumCheckBox = (CheckBox) v;
            categories.add(museumCheckBox.getText().toString());
        }else if(v.getId() == R.id.optionOthers){
            CheckBox othersCheckBox = (CheckBox) v;
            categories.add(othersCheckBox.getText().toString());
        }else if(v.getId() == R.id.optionParty){
            CheckBox partyCheckBox = (CheckBox) v;
            categories.add(partyCheckBox.getText().toString());
        }else if(v.getId() == R.id.optionShow){
            CheckBox showCheckBox = (CheckBox) v;
            categories.add(showCheckBox.getText().toString());
        }else if(v.getId() == R.id.optionSports){
            CheckBox sportsCheckBox = (CheckBox) v;
            categories.add(sportsCheckBox.getText().toString());
        }else if(v.getId() == R.id.optionTheater){
            CheckBox theaterCheckBox = (CheckBox) v;
            categories.add(theaterCheckBox.getText().toString());
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.updateEvent) {
            EditText nameField = (EditText) this.getActivity().findViewById(R.id.eventName);
            String nameEvent = nameField.getText().toString();

            EditText dateField = (EditText) this.getActivity().findViewById(R.id.eventDate);
            String dateEvent = dateField.getText().toString();

            EditText descriptionField = (EditText) this.getActivity().findViewById(R.id.eventDescription);
            String descriptionEvent = descriptionField.getText().toString();

            try {
                Event event = new Event(idEvent, nameEvent, dateEvent, descriptionEvent,
                        latitude, longitude, categories);
                updateEvent(event);

                Toast.makeText(getActivity().getBaseContext(), SUCCESSFULL_UPDATE_MESSAGE, Toast.LENGTH_LONG).show();
            } catch (EventException e) {
                String message = e.getMessage();

                //Verify address field
                if(message.equals(Event.ADDRESS_IS_EMPTY)){

                }

                if(message.equals(Event.DESCRIPTION_CANT_BE_EMPTY)){
                    descriptionField.requestFocus();
                    descriptionField.setError(message);
                }

                if(message.equals(Event.DESCRIPTION_CANT_BE_GREATER_THAN)){
                    descriptionField.requestFocus();
                    descriptionField.setError(message);
                }

                if(message.equals(Event.EVENT_DATE_IS_EMPTY)){
                    dateField.requestFocus();
                    dateField.setError(message);
                }

                if(message.equals(Event.EVENT_NAME_CANT_BE_EMPTY_NAME)){
                    nameField.requestFocus();
                    nameField.setError(message);
                }

                if(message.equals(Event.INVALID_EVENT_DATE)){
                    dateField.requestFocus();
                    dateField.setError(message);
                }

                if(message.equals(Event.NAME_CANT_BE_GREATER_THAN_50)){
                    nameField.requestFocus();
                    nameField.setError(message);
                }
            } catch (ParseException e) {
                e.printStackTrace();

            }
        }else if(v.getId() == R.id.eventLocal){
            Intent map = new Intent(getActivity(), LocalEventActivity.class);
            startActivityForResult(map, 2);
        }else{
            addEventCategories(v);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (2) : {
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    latitude = bundle.getString("latitude");
                    longitude = bundle.getString("longitude");

                    Toast.makeText(getContext(), "Local selecionado com sucesso", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

}