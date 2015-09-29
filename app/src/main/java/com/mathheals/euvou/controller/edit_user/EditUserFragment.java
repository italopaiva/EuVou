package com.mathheals.euvou.controller.edit_user;


import android.content.Context;
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
    private int USER_STATUS;
    private final int LOGGED_OUT = -1;

    public EditUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_user, container, false);

        UserDAO userDAO = new UserDAO();

        EditText nameField = (EditText) view.findViewById(R.id.nameField);
        EditText dateField = (EditText) view.findViewById(R.id.dateField);
        EditText mailField = (EditText) view.findViewById(R.id.mailField);

        SharedPreferences sharedId = this.getActivity().getSharedPreferences("idUser", Context.MODE_PRIVATE);
        USER_STATUS = sharedId.getInt("idUser", LOGGED_OUT);

        String str = userDAO.searchUserById(USER_STATUS);
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
