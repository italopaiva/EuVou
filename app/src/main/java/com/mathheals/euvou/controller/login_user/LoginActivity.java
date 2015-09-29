package com.mathheals.euvou.controller.login_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mathheals.euvou.R;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UserDAO;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void verifyLogin(String typedUsername, String typedPassword) {
        UserDAO userDAO = new UserDAO(LoginActivity.this);

        JSONObject json = userDAO.searchUserByName(typedUsername);

        SharedPreferences pref;
        pref = getSharedPreferences("idUser", MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();

        if(json!=null){
            //String password = json.getJSONObject("0").getString("idUser");

            try {
                String password = json.getJSONObject("0").getString("passwordUser");

                if(password.equals(typedPassword)){
                    int idUser=Integer.parseInt(json.getJSONObject("0").getString("idUser"));

                    editor.putInt("idUser", idUser);
                    editor.commit();
                }else{
                    editor.putInt("idUser", -1);
                    editor.commit();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onClick(View v) {

    }
}
