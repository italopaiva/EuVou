package com.mathheals.euvou.controller.show_event;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mathheals.euvou.R;

import org.json.JSONException;
import org.json.JSONObject;

import dao.EventDAO;
import model.Event;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowEvent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowEvent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowEvent extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowEvent.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowEvent newInstance(String param1, String param2) {
        ShowEvent fragment = new ShowEvent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ShowEvent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_event,container,false);
        EventDAO eventDAO = new EventDAO(this.getActivity());
        String eventName =  this.getArguments().getString("eventName");
        JSONObject eventDATA = eventDAO.searchEventByName(eventName);

        try
        {
            String eventNameDB = eventDATA.getJSONObject("0").getString("eventName");
            String eventAdress = eventDATA.getJSONObject("0").getString("dateTimeEvent");
            String eventDescription = eventDATA.getJSONObject("0").getString("eventDescription");
            String eventDateTime = eventDATA.getJSONObject("0").getString("dateTimeEvent");
            String eventLongitude = eventDATA.getJSONObject("0").getString("longitude");
            String eventLatitude = eventDATA.getJSONObject("0").getString("latitude");

            TextView nameShow = null, adressShow= null,descriptionShow= null, dataShow = null;

            nameShow.setText(eventNameDB);
            adressShow.setText(eventAdress);
            descriptionShow.setText(eventDescription);
            dataShow.setText(eventDateTime);

        }catch(JSONException ex)
        {

        }catch(NullPointerException exception)
        {
            Toast.makeText(getActivity(),"O nome não foi encontrado",Toast.LENGTH_LONG);
        }

        return inflater.inflate(R.layout.fragment_show_event, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
