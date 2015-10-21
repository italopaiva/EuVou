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
import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.Mask;

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
        EditText eventLocal = (EditText) view.findViewById(R.id.eventLocal);
        eventLocal.setOnClickListener(this);

        //Adding mask to eventDate Field
        EditText eventDate = (EditText) view.findViewById(R.id.eventDate);
        eventDate.addTextChangedListener(Mask.insert("##/##/####", eventDate));

        //Adding listener to CheckBoxs to verify if each CheckBox is checked or not
        addCheckBoxListeners(view);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.saveEvent){
            EditText nameEventField = (EditText) this.getActivity().findViewById(R.id.eventName);
            String nameEvent = nameEventField.getText().toString();

            EditText dateEventField = (EditText) this.getActivity().findViewById(R.id.eventDate);
            String dateEvent = dateEventField.getText().toString();

            EditText localEventField = (EditText) this.getActivity().findViewById(R.id.eventLocal);
            
            EditText descriptionEventField = (EditText) this.getActivity().findViewById(R.id.eventDescription);
            String descriptionEvent = descriptionEventField.getText().toString();

            Vector<String> teste= new Vector<>();
            teste.add("TESTE");
            Toast.makeText(getActivity().getBaseContext(), "IGOR", Toast.LENGTH_LONG).show();
            try {
                Event event = new Event(nameEvent, dateEvent, "TESTE", descriptionEvent,
                                        latitude, longitude, teste);
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

                if(message.equals(Event.LANTITUDE_IS_EMPTY)){
                    localEventField.requestFocus();
                    localEventField.setError(message);
                }

                if(message.equals(Event.LATITUDE_IS_INVALID)){
                    localEventField.requestFocus();
                    localEventField.setError(message);
                }

                if(message.equals(Event.LONGITUDE_IS_EMPTY)){
                    localEventField.requestFocus();
                    localEventField.setError(message);
                }

                if(message.equals(Event.LONGITUDE_IS_INVALID)){
                    localEventField.requestFocus();
                    localEventField.setError(message);
                }

                if(message.equals(Event.NAME_CANT_BE_GREATER_THAN_50)){
                    localEventField.requestFocus();
                    localEventField.setError(message);
                }
            } catch (ParseException e) {
                e.printStackTrace();

            }
        }
        else if(v.getId() == R.id.eventLocal){
            Intent map = new Intent(getActivity(), LocalEventActivity.class);
            startActivityForResult(map, 2);
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

                    Toast.makeText(getContext(), latitude+longitude, Toast.LENGTH_LONG).show();


                    Activity activity = getActivity();
                    EditText eventLocal = (EditText) activity.findViewById(R.id.eventLocal);

                    eventLocal.setText(latitude+longitude);
                }
                break;
            }
        }
    }

    private void registerEvent(Event event){
        EventDAO eventDAO = new EventDAO(getActivity());
        eventDAO.saveEvent(event);
    }

    private void addCheckBoxListeners(View v){

        CheckBox showCategory = (CheckBox) v.findViewById(R.id.optionShow);
        showCategory.setOnClickListener(this);

        CheckBox expositionCategory = (CheckBox) v.findViewById(R.id.optionExposition);
        showCategory.setOnClickListener(this);

        CheckBox museumCategory = (CheckBox) v.findViewById(R.id.optionMuseum);
        showCategory.setOnClickListener(this);

        CheckBox cinemaCategory = (CheckBox) v.findViewById(R.id.optionCinema);
        showCategory.setOnClickListener(this);

        CheckBox theaterCategory = (CheckBox) v.findViewById(R.id.optionTheater);
        showCategory.setOnClickListener(this);

        CheckBox partyCategory = (CheckBox) v.findViewById(R.id.optionParty);
        showCategory.setOnClickListener(this);

        CheckBox educationCategory = (CheckBox) v.findViewById(R.id.optionEducation);
        showCategory.setOnClickListener(this);

        CheckBox sportsCategory = (CheckBox) v.findViewById(R.id.optionSports);
        showCategory.setOnClickListener(this);

        CheckBox othersCategory = (CheckBox) v.findViewById(R.id.optionOthers);
        showCategory.setOnClickListener(this);

    }


}
