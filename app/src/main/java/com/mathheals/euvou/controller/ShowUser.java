package com.mathheals.euvou.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mathheals.euvou.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import dao.UserDAO;

public class ShowUser extends Fragment {

    public ShowUser()
    {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ,String nameUser) {

        View view = inflater.inflate(R.layout.show_user, container, false);
        UserDAO userDAO = new UserDAO();
        JSONObject userData = userDAO.searchUserByUsername(nameUser);
        JSONObject json = null;

        json = new JSONObject((Map) userData);

        try {
            String nameUserDB = json.getJSONObject("0").getString("nameUser");
            String birthDateDB = json.getJSONObject("0").getString("birthDate");
            String mailDB = json.getJSONObject("0").getString("email");

            TextView labelname = null, labelBrithDate = null,labelMail = null;

            labelname.setText(nameUserDB);
            labelBrithDate.setText(birthDateDB);
            labelMail.setText(mailDB);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
