package com.mathheals.euvou.controller.user_profile;

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

import dao.UserDAO;

public class ShowUser extends Fragment {

    public ShowUser()
    {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.show_user, container, false);
        UserDAO userDAO = new UserDAO(getActivity());

        String nameUser=this.getArguments().getString("username");
        JSONObject userData = userDAO.searchUserByUsername(nameUser);

       try {
            String nameUserDB = userData.getJSONObject("0").getString("nameUser");
            String birthDateDB = userData.getJSONObject("0").getString("birthDate");
            String mailDB = userData.getJSONObject("0").getString("email");

            TextView name= (TextView) view.findViewById(R.id.labelName);
            TextView date = (TextView) view.findViewById(R.id.labelBirthDate);
            TextView mail = (TextView) view.findViewById(R.id.labelMail);
            name.setText(nameUserDB);
            date.setText(birthDateDB);
            mail.setText(mailDB);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch(NullPointerException except)
       {
           Toast.makeText(getActivity(),"O nome não foi encontrado",Toast.LENGTH_LONG);
       }

        return view;
    }
}
