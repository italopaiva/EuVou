package com.mathheals.euvou.controller.event_consultation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mathheals.euvou.R;

public class EvenC extends android.support.v4.app.Fragment implements RadioGroup.OnCheckedChangeListener{


    public EvenC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_consultation, container, false);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId) {
            case R.id.radio_events:
                Toast.makeText(getActivity().getBaseContext(), "EVENTOS", Toast.LENGTH_LONG).show();
                break;
            case R.id.radio_places:
                Toast.makeText(getActivity().getBaseContext(), "LOCAIS", Toast.LENGTH_LONG).show();
                break;
            case R.id.radio_people:
                Toast.makeText(getActivity().getBaseContext(), "PESSOAS", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
