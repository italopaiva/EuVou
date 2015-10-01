package com.mathheals.euvou.controller.edit_user;


import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mathheals.euvou.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

import dao.UserDAO;
import exception.UserException;
import model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditUserFragment extends Fragment implements View.OnClickListener {
    private int USER_STATUS;
    private final int LOGGED_OUT = -1;

    public EditUserFragment() {
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

        Button update = (Button)view.findViewById(R.id.updateButton);
        update.setOnClickListener(this);

        return view;
    }

    private void updateUser(User user){
        UserDAO userDAO = new UserDAO();
        userDAO.update(user);
    }

    @Override
    public void onClick(View v) {
        EditText nameField = (EditText) this.getActivity().findViewById(R.id.nameField);
        String name = nameField.getText().toString();

        EditText dateField = (EditText) this.getActivity().findViewById(R.id.dateField);
        String birthDate = dateField.getText().toString();

        EditText mailField = (EditText) this.getActivity().findViewById(R.id.mailField);
        String mail = mailField.getText().toString();

        SharedPreferences sharedId = this.getActivity().getSharedPreferences("idUser", Context.MODE_PRIVATE);
        USER_STATUS = sharedId.getInt("idUser", LOGGED_OUT);

        User user = null;
        try {
            user = new User(USER_STATUS, name, birthDate, mail);
        } catch (UserException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        updateUser(user);
    }
}