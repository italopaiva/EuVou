package com.mathheals.euvou.controller.show_event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Button;
import java.util.ArrayList;

import dao.CategoryDAO;
import dao.EventCategoryDAO;
import dao.EventDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowEvent extends android.support.v4.app.Fragment implements View.OnClickListener {

    private TextView eventCategoriesText;
    private EventDAO eventDAO;
    private final String PRICE_COLUMN = "price";
    private String eventPrice;
    private TextView eventPriceText;
    private Button showEventOnMapButton, participateButton;
    private String eventLongitude;
    private String eventLatitude;
    private String eventId;

    private final String GO = "#EUVOU";
    private final String NOTGO = "#NÃOVOU";

    private final Integer LOGGED_OUT = -1;
    private int userId;
    private boolean isUserLoggedIn;
    private TextView ratingMessage;
    private View showEventView;

    public ShowEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setShowEventView(inflater.inflate(R.layout.fragment_show_event, container, false));
        showEventOnMapButton = (Button) showEventView.findViewById(R.id.showEventOnMapButton);
        participateButton = (Button) showEventView.findViewById(R.id.EuVou);
        showEventOnMapButton.setOnClickListener(this);
        participateButton.setOnClickListener(this);

        eventDAO = new EventDAO(this.getActivity());
        eventId = this.getArguments().getString("idEventSearch");
        JSONObject eventDATA = eventDAO.searchEventById(Integer.parseInt(eventId));

        setUserId(new LoginUtility(getActivity()).getUserId());
        if(userId != LOGGED_OUT) {
            participateButton.setVisibility(showEventView.GONE);
        }
        else {
            participateButton.setVisibility(View.VISIBLE);
            if(eventDAO.verifyParticipate(userId,Integer.parseInt(eventId)) == null) {
                participateButton.setText(GO);
            }
            else{
                participateButton.setText(NOTGO);
            }
        }

        try {
            String eventNameDB = eventDATA.getJSONObject("0").getString("nameEvent");
            String eventAdress = eventDATA.getJSONObject("0").getString("address");
            String eventDescription = eventDATA.getJSONObject("0").getString("description");
            String eventDateTime = eventDATA.getJSONObject("0").getString("dateTimeEvent");
            eventPrice = eventDATA.getJSONObject("0").getString(PRICE_COLUMN);
            eventLongitude = eventDATA.getJSONObject("0").getString("longitude");
            eventLatitude = eventDATA.getJSONObject("0").getString("latitude");

            TextView name1Event = (TextView) showEventView.findViewById(R.id.nameEventShow);
            TextView dateEvent = (TextView) showEventView.findViewById(R.id.dateEvent);
            TextView description = (TextView) showEventView.findViewById(R.id.descriptionEvent);
            TextView addressShow = (TextView) showEventView.findViewById(R.id.eventPlaces);
            eventCategoriesText = (TextView) showEventView.findViewById(R.id.eventCategories);
            eventPriceText = (TextView) showEventView.findViewById(R.id.eventPrice);
            name1Event.setText(eventNameDB);
            description.setText(eventDescription);
            dateEvent.setText(eventDateTime);
            setPriceText(eventPriceText, eventPrice);
            setCategoriesText(new Integer(eventId), eventCategoriesText);
            addressShow.setText(eventAdress);

        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (NullPointerException exception) {
            Toast.makeText(getActivity(), "O nome não foi encontrado", Toast.LENGTH_LONG);
        }

        setIsUserLoggedIn(userId != LOGGED_OUT);
        setRatingMessage(isUserLoggedIn);

        return showEventView;
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

    public void setCategoriesText(int eventId, TextView eventCategoriesText){
        String[] eventCategories = getEventCategoriesById(eventId);
        String text = eventCategories[0];
        for(int i = 1; i < eventCategories.length; ++i)
            text += (", " + eventCategories[i]);
        eventCategoriesText.setText(text);
    }

    public void setPriceText(TextView eventPriceText, String eventPrice) {
        final int PRICE = new Integer(eventPrice);
        final String REAIS_PART = Integer.toString(PRICE / 100);
        final String CENTS = Integer.toString(PRICE % 100);
        final String CENTS_PART = CENTS.length() > 1 ? CENTS : "0" + CENTS;
        eventPriceText.setText("R$ " + REAIS_PART + "," + CENTS_PART);

        return;
    }

    private void showEventOnMap() {
        Bundle latitudeAndLongitude = new Bundle();
        latitudeAndLongitude.putStringArray("LatitudeAndLongitude", new String[]{eventLatitude, eventLongitude});
        Intent intent = new Intent(getContext(), ShowOnMap.class);
        intent.putExtras(latitudeAndLongitude);
        startActivity(intent);
    }
    private void markParticipate()
    {
        if(eventDAO.verifyParticipate(userId,Integer.parseInt(eventId)) != null)
            Toast.makeText(getActivity(), "Heyy, você já marcou sua participação", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), eventDAO.markParticipate(userId,Integer.parseInt(eventId)), Toast.LENGTH_SHORT).show();
    }
    private void markOffParticipate()
    {

        if(eventDAO.verifyParticipate(userId,Integer.parseInt(eventId)) == null)
            Toast.makeText(getActivity(), "Heyy, você já desmarcou sua participação", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), eventDAO.markOffParticipate(userId, Integer.parseInt(eventId)), Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.showEventOnMapButton:
                showEventOnMap();
                break;
            case R.id.EuVou:
                if(((Button)view.findViewById(R.id.EuVou)).getText().equals(GO))
                    markParticipate();
                else
                    markOffParticipate();
                break;
        }
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRatingBar() {

    }

    public void setIsUserLoggedIn(boolean isUserLoggedIn) {
        this.isUserLoggedIn = isUserLoggedIn;
    }

    private void setRatingMessage(boolean isUserLoggedIn) {
        String message = isUserLoggedIn ? "Sua avaliação:" : "Faça login para avaliar este evento!";
        ratingMessage = (TextView) showEventView.findViewById(R.id.rate_event_text);
        ratingMessage.setText(message);
    }

    public void setShowEventView(View showEventView) {
        this.showEventView = showEventView;
    }
}