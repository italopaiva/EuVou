package com.mathheals.euvou.controller.event_registration;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.utility.LoginUtility;
import com.mathheals.euvou.controller.utility.Mask;

import org.json.JSONException;

import java.text.ParseException;
import java.util.Vector;

import dao.EventDAO;
import exception.EventException;
import model.Event;

/**
 * Created by izabela on 13/10/15.
 */
public class RegisterEventFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private static final String SUCCESSFULL_CADASTRATION_MESSAGE = "Evento cadastrado com sucesso :)";
    private String latitude;
    private String longitude;
    Vector<String> categories= new Vector<>();

    public RegisterEventFragment(){
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.register_event, container, false);

        //Adding listener to saveEvent Button
        Button registerEvent = (Button) view.findViewById(R.id.saveEvent);
        registerEvent.setOnClickListener(this);

        //Adding listener to eventLocal EditText
        Button eventLocal = (Button) view.findViewById(R.id.eventLocal);
        eventLocal.setOnClickListener(this);

        //Adding mask to eventDate Field
        EditText eventDate = (EditText) view.findViewById(R.id.eventDate);
        eventDate.addTextChangedListener(Mask.insert("##/##/####", eventDate));

        //Adding listener to CheckBoxs to verify if each CheckBox is checked or not
        addCheckBoxListeners(view);

        return view;
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
        if(v.getId() == R.id.saveEvent){
            EditText nameEventField = (EditText) this.getActivity().findViewById(R.id.eventName);
            String nameEvent = nameEventField.getText().toString();

            EditText dateEventField = (EditText) this.getActivity().findViewById(R.id.eventDate);
            String dateEvent = dateEventField.getText().toString();

            EditText descriptionEventField = (EditText) this.getActivity().findViewById(R.id.eventDescription);
            String descriptionEvent = descriptionEventField.getText().toString();

            try {
                Event event = new Event(new LoginUtility().getUserId(),nameEvent, dateEvent, descriptionEvent,
                                        latitude, longitude, categories);
                registerEvent(event);

                Toast.makeText(getActivity().getBaseContext(), SUCCESSFULL_CADASTRATION_MESSAGE, Toast.LENGTH_LONG).show();
            } catch (EventException e) {
                String message = e.getMessage();

                //Verify address field
                if(message.equals(Event.ADDRESS_IS_EMPTY)){

                }

                if(message.equals(Event.DESCRIPTION_CANT_BE_EMPTY)){
                    descriptionEventField.requestFocus();
                    descriptionEventField.setError(message);
                }

                if(message.equals(Event.DESCRIPTION_CANT_BE_GREATER_THAN)){
                    descriptionEventField.requestFocus();
                    descriptionEventField.setError(message);
                }

                if(message.equals(Event.EVENT_DATE_IS_EMPTY)){
                    dateEventField.requestFocus();
                    dateEventField.setError(message);
                }

                if(message.equals(Event.EVENT_NAME_CANT_BE_EMPTY_NAME)){
                    nameEventField.requestFocus();
                    nameEventField.setError(message);
                }

                if(message.equals(Event.INVALID_EVENT_DATE)){
                    dateEventField.requestFocus();
                    dateEventField.setError(message);
                }

                if(message.equals(Event.NAME_CANT_BE_GREATER_THAN_50)){
                    nameEventField.requestFocus();
                    nameEventField.setError(message);
                }
            } catch (ParseException e) {
                e.printStackTrace();

            }
        }
        else if(v.getId() == R.id.eventLocal){
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

    private void registerEvent(Event event){
        EventDAO eventDAO = new EventDAO(getActivity());
        try {
            eventDAO.saveEvent(event);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addCheckBoxListeners(View v){

        CheckBox showCategory = (CheckBox) v.findViewById(R.id.optionShow);
        showCategory.setOnClickListener(this);

        CheckBox expositionCategory = (CheckBox) v.findViewById(R.id.optionExposition);
        expositionCategory.setOnClickListener(this);

        CheckBox museumCategory = (CheckBox) v.findViewById(R.id.optionMuseum);
        museumCategory.setOnClickListener(this);

        CheckBox cinemaCategory = (CheckBox) v.findViewById(R.id.optionCinema);
        cinemaCategory.setOnClickListener(this);

        CheckBox theaterCategory = (CheckBox) v.findViewById(R.id.optionTheater);
        theaterCategory.setOnClickListener(this);

        CheckBox partyCategory = (CheckBox) v.findViewById(R.id.optionParty);
        partyCategory.setOnClickListener(this);

        CheckBox educationCategory = (CheckBox) v.findViewById(R.id.optionEducation);
        educationCategory.setOnClickListener(this);

        CheckBox sportsCategory = (CheckBox) v.findViewById(R.id.optionSports);
        sportsCategory.setOnClickListener(this);

        CheckBox othersCategory = (CheckBox) v.findViewById(R.id.optionOthers);
        othersCategory.setOnClickListener(this);

    }


}
