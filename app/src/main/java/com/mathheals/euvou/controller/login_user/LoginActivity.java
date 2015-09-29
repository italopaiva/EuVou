package com.mathheals.euvou.controller.login_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.home_page.HomePage;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UserDAO;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button doLogin = (Button) findViewById(R.id.doLogin);
        doLogin.setOnClickListener(this);
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

        SharedPreferences prefId;
        prefId = getSharedPreferences("idUser", MODE_PRIVATE);

        SharedPreferences prefUsername;
        prefUsername = getSharedPreferences("isUsernameValid", MODE_PRIVATE);

        SharedPreferences.Editor editorId = prefId.edit();
        SharedPreferences.Editor editorUsername = prefUsername.edit();

        if(json!=null){
            editorUsername.putInt("isUsernameValid", 1);
            editorUsername.commit();

            try {
                String password = json.getJSONObject("0").getString("passwordUser");

                if(password.equals(typedPassword)){
                    int idUser=Integer.parseInt(json.getJSONObject("0").getString("idUser"));

                    editorId.putInt("idUser", idUser);
                    editorId.commit();
                }else{
                    editorId.putInt("idUser", -1);
                    editorId.commit();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            editorUsername.putInt("isUsernameValid", -1);
            editorUsername.commit();
        }

    }

    @Override
    public void onClick(View v) {
        EditText usernameField = (EditText) findViewById(R.id.usernameField);
        String typedUsername = usernameField.getText().toString();

        EditText passwordField = (EditText) findViewById(R.id.passwordField);
        String typedPassword = passwordField.getText().toString();

        verifyLogin(typedUsername, typedPassword);

        SharedPreferences sharedUsername;
        SharedPreferences sharedId;

        sharedUsername = getSharedPreferences("isUsernameValid", MODE_PRIVATE);
        int isUsernameValid = sharedUsername.getInt("isUsernameValid", -1);

        sharedId = getSharedPreferences("idUser", MODE_PRIVATE);
        int idUser = sharedId.getInt("idUser", -1);

        if(isUsernameValid==-1){
            usernameField.requestFocus();
            usernameField.setError("Ops, acho que você digitou o login errado");
        }else{
            if(idUser==-1){
                passwordField.requestFocus();
                passwordField.setError("Ops, acho que você digitou a senha errada");
            }else{
                Intent i = new Intent(this, HomePage.class);
                startActivityForResult(i, 1);
            }
        }
    }
}
