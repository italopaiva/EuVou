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
    private int idEvent;
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

        idEvent = this.getArguments().getInt("idEvent");

        View view = inflater.inflate(R.layout.fragment_edit_event, container, false);

        EditText nameField = (EditText) view.findViewById(R.id.eventName);

        EditText dateField = (EditText) view.findViewById(R.id.eventDate);
        dateField.addTextChangedListener(Mask.insert("##/##/####", dateField));

        EditText descriptionField = (EditText) view.findViewById(R.id.eventDescription);

        EditText priceRealField = (EditText) view.findViewById(R.id.eventPriceReal);
        EditText priceDecimalField = (EditText) view.findViewById(R.id.eventPriceDecimal);

        EditText addressField = (EditText) view.findViewById(R.id.eventAddress);

        CheckBox showCheckbox = (CheckBox) view.findViewById(R.id.optionShow);
        CheckBox expositionCheckbox = (CheckBox) view.findViewById(R.id.optionExposition);
        CheckBox cinemaCheckbox = (CheckBox) view.findViewById(R.id.optionCinema);
        CheckBox theaterCheckbox = (CheckBox) view.findViewById(R.id.optionTheater);
        CheckBox partyCheckbox = (CheckBox) view.findViewById(R.id.optionParty);
        CheckBox educationCheckbox = (CheckBox) view.findViewById(R.id.optionEducation);
        CheckBox museumCheckbox = (CheckBox) view.findViewById(R.id.optionMuseum);
        CheckBox sportsCheckbox = (CheckBox) view.findViewById(R.id.optionSports);
        CheckBox othersCheckbox = (CheckBox) view.findViewById(R.id.optionOthers);

        EventDAO eventDAO = new EventDAO(getActivity());
        EventCategoryDAO eventCategoryDAO = new EventCategoryDAO(getActivity());
        CategoryDAO categoryDAO = new CategoryDAO(getActivity());

        //Change the value of idEvent when the consultEvent was finished
        JSONObject jsonEvent = eventDAO.searchEventById(idEvent);
        JSONObject jsonEventCategory = eventCategoryDAO.searchCategoriesByEventId(idEvent);

        try {
            String nameEvent = jsonEvent.getJSONObject("0").getString("nameEvent");
            nameField.setText(nameEvent);

            String dateEvent = jsonEvent.getJSONObject("0").getString("dateTimeEvent");
            dateField.setText(dateEvent);

            String descriptionEvent = jsonEvent.getJSONObject("0").getString("description");
            descriptionField.setText(descriptionEvent);

            String addressEvent = jsonEvent.getJSONObject("0").getString("address");
            addressField.setText(addressEvent);

            Integer priceEvent = jsonEvent.getJSONObject("0").getInt("price");
            priceRealField.setText(Integer.toString(priceEvent/100));
            priceDecimalField.setText(Integer.toString(priceEvent - priceEvent / 100 * 100));

            latitude = jsonEvent.getJSONObject("0").getString("latitude");
            longitude = jsonEvent.getJSONObject("0").getString("longitude");

            Vector <Integer> idCategories = new Vector<>();
            String idCategory;
            for(int i=0; i<jsonEventCategory.length(); i++){
                idCategory=jsonEventCategory.getJSONObject(Integer.toString(i)).getString("idCategory");
                idCategories.add(Integer.parseInt(idCategory));
            }

            for(int i=0; i<idCategories.size(); i++){
                JSONObject jsonCategory = categoryDAO.searchCategoryById(idCategories.get(i));
                String nameCategory = jsonCategory.getJSONObject("0").getString("nameCategory");

                switch (nameCategory){
                    case "Show":
                        showCheckbox.setChecked(true);
                        categories.add("Show");
                        break;
                    case "Teatro":
                        theaterCheckbox.setChecked(true);
                        categories.add("Teatro");
                        break;
                    case "Cinema":
                        cinemaCheckbox.setChecked(true);
                        categories.add("Cinema");
                        break;
                    case "Balada":
                        partyCheckbox.setChecked(true);
                        categories.add("Balada");
                        break;
                    case "Museu":
                        museumCheckbox.setChecked(true);
                        categories.add("Museu");
                        break;
                    case "Educacao":
                        educationCheckbox.setChecked(true);
                        categories.add("Educacao");
                        break;
                    case "Exposicao":
                        expositionCheckbox.setChecked(true);
                        categories.add("Exposicao");
                        break;
                    case "Esporte":
                        sportsCheckbox.setChecked(true);
                        categories.add("Esporte");
                        break;
                    case "Outros":
                        othersCheckbox.setChecked(true);
                        break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Adding listener to eventLocal EditText
        Button eventLocal = (Button) view.findViewById(R.id.eventLocal);
        eventLocal.setOnClickListener(this);

        //Adding listener to CheckBoxs to verify if each CheckBox is checked or not
        addCheckBoxListeners(view);

        Button updateEvent = (Button)view.findViewById(R.id.updateEvent);
        updateEvent.setOnClickListener(this);

        return view;
    }

    private void updateEventOnDataBase(Event event){
        EventDAO eventDAO = new EventDAO(getActivity());
        eventDAO.updateEvent(event);
    }

    public void updateEvent(){
        EditText nameField = (EditText) this.getActivity().findViewById(R.id.eventName);
        String nameEvent = nameField.getText().toString();

        EditText dateField = (EditText) this.getActivity().findViewById(R.id.eventDate);
        String dateEvent = dateField.getText().toString();

        EditText descriptionField = (EditText) this.getActivity().findViewById(R.id.eventDescription);
        String descriptionEvent = descriptionField.getText().toString();

        EditText addresField = (EditText) this.getActivity().findViewById(R.id.eventAddress);
        String addresEvent = addresField.getText().toString();

        EditText priceRealField = (EditText) this.getActivity().findViewById(R.id.eventPriceReal);
        EditText priceDecimalField = (EditText) this.getActivity().findViewById(R.id.eventPriceDecimal);
        Integer eventPriceReal = Integer.parseInt(priceRealField.getText().toString());
        Integer eventPriceDecimal = Integer.parseInt(priceDecimalField.getText().toString());
        Integer priceEvent = eventPriceReal * 100 + eventPriceDecimal;

        try {
            Event event = new Event(idEvent, nameEvent, priceEvent, addresEvent, dateEvent, descriptionEvent,
                    latitude, longitude, categories);

            updateEventOnDataBase(event);

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
    }

    private void addEventCategories(View v){
        if(v.getId() == R.id.optionCinema){
            CheckBox cinemaCheckBox = (CheckBox) v;

            if(cinemaCheckBox.isChecked()) {
                categories.add(cinemaCheckBox.getText().toString());
            }else{
                categories.remove(cinemaCheckBox.getText().toString());
            }
        }else if(v.getId() == R.id.optionEducation) {
            CheckBox educationCheckBox = (CheckBox) v;

            if(educationCheckBox.isChecked()) {
                categories.add("Educacao");
            }else{
                categories.remove("Educacao");
            }
        }else if(v.getId() == R.id.optionExposition){
            CheckBox expositionCheckBox = (CheckBox) v;

            if(expositionCheckBox.isChecked()) {
                categories.add("Exposicao");
            }else{
                categories.remove("Exposicao");
            }
        }else if(v.getId() == R.id.optionMuseum){
            CheckBox museumCheckBox = (CheckBox) v;

            if(museumCheckBox.isChecked()) {
                categories.add(museumCheckBox.getText().toString());
            }else{
                categories.remove(museumCheckBox.getText().toString());
            }
        }else if(v.getId() == R.id.optionOthers){
            CheckBox othersCheckBox = (CheckBox) v;

            if(othersCheckBox.isChecked()) {
                categories.add(othersCheckBox.getText().toString());
            }else{
                categories.remove(othersCheckBox.getText().toString());
            }
        }else if(v.getId() == R.id.optionParty){
            CheckBox partyCheckBox = (CheckBox) v;

            if(partyCheckBox.isChecked()) {
                categories.add(partyCheckBox.getText().toString());
            }else{
                categories.remove(partyCheckBox.getText().toString());
            }
        }else if(v.getId() == R.id.optionShow){
            CheckBox showCheckBox = (CheckBox) v;

            if(showCheckBox.isChecked()) {
                categories.add(showCheckBox.getText().toString());
            }else{
                categories.remove(showCheckBox.getText().toString());
            }
        }else if(v.getId() == R.id.optionSports){
            CheckBox sportsCheckBox = (CheckBox) v;

            if(sportsCheckBox.isChecked()) {
                categories.add(sportsCheckBox.getText().toString());
            }else{
                categories.remove(sportsCheckBox.getText().toString());
            }
        }else if(v.getId() == R.id.optionTheater){
            CheckBox theaterCheckBox = (CheckBox) v;

            if(theaterCheckBox.isChecked()) {
                categories.add(theaterCheckBox.getText().toString());
            }else{
                categories.remove(theaterCheckBox.getText().toString());
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.updateEvent) {
            updateEvent();
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