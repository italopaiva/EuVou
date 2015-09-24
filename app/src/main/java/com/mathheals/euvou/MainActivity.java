package com.mathheals.euvou;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dao.UserDAO;
import exception.UserException;
import model.User;
public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button register = (Button)findViewById(R.id.registerButton);

        //register listeners
        register.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void registerUser(User user){

        UserDAO userDAO = new UserDAO(MainActivity.this);
        userDAO.save(user);

    }

    public void onClick(View v) {

        EditText usernameField = (EditText) findViewById(R.id.loginField);
        String username = usernameField.getText().toString();

        EditText nameField = (EditText) findViewById(R.id.nameField);
        String name = nameField.getText().toString();

        EditText passwordField = (EditText) findViewById(R.id.passwordField);
        String password = passwordField.getText().toString();

        EditText passwordcConfirmField = (EditText) findViewById(R.id.passwordConfirmField);
        String passwordcConfirm = passwordField.getText().toString();

        EditText mailField = (EditText) findViewById(R.id.mailField);
        String mail = mailField.getText().toString();

        EditText mailConfirmField = (EditText) findViewById(R.id.mailConfirmField);
        String mailConfirm = mailField.getText().toString();

        EditText birthDateField = (EditText) findViewById(R.id.birthDateField);
        String birthDate = birthDateField.getText().toString();

        try {
            User user = new User(name, username, mail, password, birthDate);
            //registerUser(user);
        } catch (UserException e) {
            String message = e.getMessage();

            switch (message) {
                case User.NAME_CANT_BE_EMPTY_NAME:
                case User.NAME_CANT_BE_HIGHER_THAN_200:
                    nameField.requestFocus();
                    nameField.setError(message);
                    break;

                case User.EMAIL_CANT_BE_EMPTY_EMAIL:
                case User.EMAIL_CANT_BE_HIGHER_THAN_150:
                    mailField.requestFocus();
                    mailField.setError(message);
                    break;

                case User.USERNAME_CANT_BE_EMPTY_USERNAME:
                case User.USERNAME_CANT_BE_HIGHER_THAN_100:
                    usernameField.requestFocus();
                    usernameField.setError(message);
                    break;
            }
        }

        //String userName = username.getText().toString();

        //UserDAO userDao = new UserDAO(MainActivity.this);

        //String result = userDao.save(userName);

        //Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
    }
 }
