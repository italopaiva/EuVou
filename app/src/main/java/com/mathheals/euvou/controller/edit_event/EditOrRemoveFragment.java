package com.mathheals.euvou.controller.edit_event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.search_event.ListEvents;
import com.mathheals.euvou.controller.show_event.ShowEvent;
import com.mathheals.euvou.controller.user_registration.RegisterFragment;

import model.Event;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditOrRemoveFragment extends android.support.v4.app.Fragment  implements View.OnClickListener{

    public EditOrRemoveFragment() {
        // Required empty public constructor
    }

    public Event evento;
    private TextView eventCategoriesText, eventPriceText;
    private ShowEvent showEvent = new ShowEvent();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_or_remove_event_fragment, container, false);

        String eventName = evento.getNameEvent();
        String eventDescription = evento.getDescription();
        String eventDateTime = evento.getDateTimeEvent();


        TextView name1Event = (TextView) view.findViewById(R.id.nameEventShow);
        TextView dateEvent = (TextView) view.findViewById(R.id.dateEvent);
        TextView description = (TextView) view.findViewById(R.id.descriptionEvent);
        eventCategoriesText = (TextView) view.findViewById(R.id.eventCategories);
        name1Event.setText(eventName);
        description.setText(eventDescription);
        dateEvent.setText(eventDateTime);
        showEvent.setCategoriesText(new Integer(evento.getIdEvent()), eventCategoriesText);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.editRemoveButton){
            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, new EditEventFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }
}