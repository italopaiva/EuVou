package com.mathheals.euvou.controller.search_event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import dao.EventDAO;
import exception.EventException;
import model.Event;

public class ListEvents extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private Vector<Event> events;

    public ListEvents() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_list_events, container, false);
        // Inflate the layout for this fragment
        listView = (ListView) vw.findViewById(R.id.eventList);
        listView.setOnItemClickListener(this);
        fillList();
        return vw;
    }

    private void fillList() {
        try {
            int id = (new LoginUtility(getActivity())).getUserId();

            events = new EventDAO(getActivity()).searchEventByOwner(id);
            List<Map<String, String>> eventList= new ArrayList<Map<String, String>>();

            for (Event e :events)
                eventList.add(createEvent("Nome",e.getNameEvent()));

            SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),eventList,
                    android.R.layout.simple_list_item_1,
                    new String[]{"Nome"}, new int[]{android.R.id.text1});

            listView.setAdapter(simpleAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (EventException e) {
            e.printStackTrace();
        }
    }
    private HashMap<String, String> createEvent(String name, String number) {
        HashMap<String, String> eventName = new HashMap<String, String>();
        eventName.put(name, number);
        return eventName;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Event clicado = events.get((int) id);
    }
}
