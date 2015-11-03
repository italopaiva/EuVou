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


public class EditEventFragment extends Fragment {
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

}