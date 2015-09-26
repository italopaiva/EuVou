package com.mathheals.euvou;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;

import dao.UserDAO;
import exception.UserException;
import model.User;

public class UpdateActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Button update = (Button)findViewById(R.id.updateButton);

        update.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update, menu);
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

    private void updateUser(User user){

        UserDAO userDAO = new UserDAO(UpdateActivity.this);
        userDAO.update(user);

    }

    @Override
    public void onClick(View v) {
        EditText nameField = (EditText) findViewById(R.id.nameField);
        String name = nameField.getText().toString();

        EditText dateField = (EditText) findViewById(R.id.dateField);
        String birthDate = dateField.getText().toString();

        EditText mailField = (EditText) findViewById(R.id.mailField);
        String mail = mailField.getText().toString();

        int idUser=1;

        User user = null;
        try {
            user = new User(idUser, name, birthDate, mail);
        } catch (UserException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        updateUser(user);
    }
}
