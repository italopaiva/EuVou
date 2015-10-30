package com.mathheals.euvou.controller.show_event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.mathheals.euvou.R;
import org.json.JSONException;
import org.json.JSONObject;
import dao.EventDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowEvent extends android.support.v4.app.Fragment {

    public ShowEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_event,container,false);
        EventDAO eventDAO = new EventDAO(this.getActivity());
        String eventId =  this.getArguments().getString("idEventSearch");
        JSONObject eventDATA = eventDAO.searchEventById(eventId);
        //Toast.makeText(getContext(), eventNamee, Toast.LENGTH_LONG).show();


        try
        {
            String eventNameDB = eventDATA.getJSONObject("0").getString("nameEvent");
            //String eventAdress = eventDATA.getJSONObject("0").getString("dateTimeEvent");
            //o banco ainda está sem a tabela endereço!!
            String eventDescription = eventDATA.getJSONObject("0").getString("description");
            String eventDateTime = eventDATA.getJSONObject("0").getString("dateTimeEvent");
            String eventLongitude = eventDATA.getJSONObject("0").getString("longitude");
            String eventLatitude = eventDATA.getJSONObject("0").getString("latitude");

            TextView name1Event= (TextView) view.findViewById(R.id.nameEventShow);
            TextView dateEvent= (TextView) view.findViewById(R.id.dateEvent);
            TextView description= (TextView) view.findViewById(R.id.descriptionEvent);

            name1Event.setText(eventNameDB);
            description.setText(eventDescription);
            dateEvent.setText(eventDateTime);
            /*adressShow.setText(eventAdress);
            descriptionShow.setText(eventDescription);
            dataShow.setText(eventDateTime);
*/
        }catch(JSONException ex)
        {
            ex.printStackTrace();
        }catch(NullPointerException exception)
        {
            Toast.makeText(getActivity(),"O nome não foi encontrado",Toast.LENGTH_LONG);
        }

        return view;
    }


}
