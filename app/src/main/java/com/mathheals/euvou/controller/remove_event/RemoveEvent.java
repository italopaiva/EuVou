package com.mathheals.euvou.controller.remove_event;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mathheals.euvou.R;

import dao.EventDAO;
import model.Event;

@SuppressLint("ValidFragment")
public class RemoveEvent extends  android.support.v4.app.Fragment implements View.OnClickListener {

    private Event event;

    public RemoveEvent(Event event) {
        setEvent(event);
    }

    public Event getEvent() {
        return event;
    }

    private void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_remove_event, container, false);

        Button bt = (Button) vw.findViewById(R.id.removeEvent);
        bt.setOnClickListener(this);

        // Inflate the layout for this fragment
        return vw;
    }


    @Override
    public void onClick(View vw) {
        if (vw.getId() == R.id.removeEvent)
        {
            remove();
        }
    }

    private void remove()
    {
        EventDAO eventDAO = new EventDAO(getActivity());
        if(eventDAO.deleteEvent(event).contains("Salvo"))
            Toast.makeText(getActivity(),"Deletado com sucesso",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getActivity(),"Houve um erro",Toast.LENGTH_LONG).show();
    }
}
