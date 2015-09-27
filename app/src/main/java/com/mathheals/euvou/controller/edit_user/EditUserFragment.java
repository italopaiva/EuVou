package com.mathheals.euvou.controller.edit_user;


import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mathheals.euvou.R;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UserDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditUserFragment extends Fragment {


    public EditUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_user, container, false);

        UserDAO userDAO = new UserDAO(this.getActivity());

        EditText nameField = (EditText) view.findViewById(R.id.nameField);
        EditText dateField = (EditText) view.findViewById(R.id.dateField);
        EditText mailField = (EditText) view.findViewById(R.id.mailField);

        String str = userDAO.searchUserById(1);
        JSONObject json = null;
        try {
            json = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            String nameUser = json.getJSONObject("0").getString("nameUser");
            String birthDate = json.getJSONObject("0").getString("birthDate");
            String mail = json.getJSONObject("0").getString("email");

            nameField.setText(nameUser);
            dateField.setText(birthDate);
            mailField.setText(mail);

        } catch (JSONException e) {
            e.printStackTrace();
        }



        return view;
    }
}
