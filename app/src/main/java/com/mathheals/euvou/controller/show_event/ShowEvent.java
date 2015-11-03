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

import java.util.ArrayList;

import dao.CategoryDAO;
import dao.EventCategoryDAO;
import dao.EventDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowEvent extends android.support.v4.app.Fragment {
    private TextView eventCategoriesText;
    private EventDAO eventDAO;
    private final String PRICE_COLUMN = "price";
    private String eventPrice;
    private TextView eventPriceText;

    public ShowEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_event, container, false);
        eventDAO = new EventDAO(this.getActivity());
        String eventId = this.getArguments().getString("idEventSearch");
        JSONObject eventDATA = eventDAO.searchEventById(eventId);
        //Toast.makeText(getContext(), eventNamee, Toast.LENGTH_LONG).show();


        try {
            String eventNameDB = eventDATA.getJSONObject("0").getString("nameEvent");
            //String eventAdress = eventDATA.getJSONObject("0").getString("dateTimeEvent");
            //o banco ainda está sem a tabela endereço!!
            String eventDescription = eventDATA.getJSONObject("0").getString("description");
            String eventDateTime = eventDATA.getJSONObject("0").getString("dateTimeEvent");
            eventPrice = eventDATA.getJSONObject("0").getString(PRICE_COLUMN);
            String eventLongitude = eventDATA.getJSONObject("0").getString("longitude");
            String eventLatitude = eventDATA.getJSONObject("0").getString("latitude");

            TextView name1Event = (TextView) view.findViewById(R.id.nameEventShow);
            TextView dateEvent = (TextView) view.findViewById(R.id.dateEvent);
            TextView description = (TextView) view.findViewById(R.id.descriptionEvent);
            eventCategoriesText = (TextView) view.findViewById(R.id.eventCategories);
            eventPriceText = (TextView) view.findViewById(R.id.eventPrice);

            name1Event.setText(eventNameDB);
            description.setText(eventDescription);
            dateEvent.setText(eventDateTime);
            setPriceText();
            setCategoriesText(new Integer(eventId));

            /*adressShow.setText(eventAdress);
            descriptionShow.setText(eventDescription);
            dataShow.setText(eventDateTime);
*/
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (NullPointerException exception) {
            Toast.makeText(getActivity(), "O nome não foi encontrado", Toast.LENGTH_LONG);
        }
        return view;
    }


    private String[] getEventCategoriesById(int eventId) {
        final String ID_CATEGORY = "idCategory";
        final String NAME_CATEGORY = "nameCategory";
        final String FIRST_COLUMN = "0";

        EventCategoryDAO eventCategoryDAO = new EventCategoryDAO(getActivity());
        JSONObject eventCategoryJSON = eventCategoryDAO.searchCategoriesByEventId(eventId);
        CategoryDAO categoryDAO = new CategoryDAO(getActivity());


        ArrayList<String> categories = new ArrayList<>();

        for(int i = 0; i < eventCategoryJSON.length(); ++i) {
            try {
                int categoryId = eventCategoryJSON.getJSONObject(Integer.toString(i)).getInt(ID_CATEGORY);
                JSONObject categoryJSON = categoryDAO.searchCategoryById(categoryId);
                String categoryName = categoryJSON.getJSONObject(FIRST_COLUMN).getString(NAME_CATEGORY);
                categories.add(categoryName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String[] categoriesArray = new String[categories.size()];
        categoriesArray = categories.toArray(categoriesArray);
        return categoriesArray;
    }

    void setCategoriesText(int eventId){
        String[] eventCategories = getEventCategoriesById(eventId);
        String text = eventCategories[0];
        for(int i = 1; i < eventCategories.length; ++i)
            text += (", " + eventCategories[i]);
        eventCategoriesText.setText(text);
    }

    void setPriceText() {
        final int PRICE = new Integer(eventPrice);
        final String REAIS_PART = Integer.toString(PRICE / 100);
        final String CENTS = Integer.toString(PRICE % 100);
        final String CENTS_PART = CENTS.length() > 1 ? CENTS : "0" + CENTS;
        eventPriceText.setText("R$ " + REAIS_PART + "," + CENTS_PART);

        return;
    }
}
