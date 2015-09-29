package com.mathheals.euvou;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mathheals.euvou.controller.ShowUser;
import com.mathheals.euvou.controller.edit_user.EditUserFragment;

import dao.*;
import org.json.JSONException;
import org.json.JSONObject;

import dao.UserDAO;
import model.User;

public class SearchUser extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    public String searchUser (String nameUser)
    {
        UserDAO userDAO = new UserDAO(SearchUser.this);
        String userData = userDAO.searchUserByName(nameUser);
        JSONObject json = null;

        try {
            json = new JSONObject(userData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            String nameUserDB = json.getJSONObject("0").getString("nameUser");
            String birthDateDB = json.getJSONObject("0").getString("birthDate");
            String mailDB = json.getJSONObject("0").getString("email");

            TextView labelname = null, labelbrithdate = null,labelMail = null;

            labelname.setText(nameUserDB);
            labelbrithdate.setText(birthDateDB);
            labelMail.setText(mailDB);

        } catch (JSONException e) {
            e.printStackTrace();
        }
);



        return userDAO.searchUserByName(nameUser);
    }*/
    @Override
    public void onClick(View v) {

        EditText searchName = (EditText) findViewById(R.id.searchNameField);
        String searchNameStr = searchName.getText().toString();
        //searchUser(searchNameStr);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId())
        {
            case R.id.btnSearch:fragmentTransaction.replace(R.id.content_frame, new ShowUser());
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                return;

        }


    }
}
