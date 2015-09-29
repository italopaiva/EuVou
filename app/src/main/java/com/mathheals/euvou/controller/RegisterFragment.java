package com.mathheals.euvou.controller;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mathheals.euvou.R;

import model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_user, container, false);
        Button register = (Button) view.findViewById(R.id.saveButton);
        register.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        EditText usernameField = (EditText) v.findViewById(R.id.loginField);
        String username = usernameField.getText().toString();

        EditText nameField = (EditText) v.findViewById(R.id.nameField);
        String name = nameField.getText().toString();

        EditText passwordField = (EditText) v.findViewById(R.id.passwordField);
        String password = passwordField.getText().toString();

        EditText passwordConfirmField = (EditText) v.findViewById(R.id.confirmPasswordField);
        String passwordConfirm = passwordField.getText().toString();

        EditText mailField = (EditText) v.findViewById(R.id.mailField);
        String mail = mailField.getText().toString();

        EditText mailConfirmField = (EditText) v.findViewById(R.id.confirmMailField);
        String mailConfirm = mailField.getText().toString();

        EditText birthDateField = (EditText) v.findViewById(R.id.dateField);
        String birthDate = birthDateField.getText().toString();

        try {
            User user = new User(name, username, mail, mailConfirm, password, passwordConfirm, birthDate);

        } catch (Exception e) {
            String message = e.getMessage();

            if (message.equals(User.NAME_CANT_BE_EMPTY_NAME)) {
                nameField.requestFocus();
                nameField.setError(message);
            }

            if (message.equals(User.NAME_CANT_BE_HIGHER_THAN_50)) {
                nameField.requestFocus();
                nameField.setError(message);
            }

            if (message.equals(User.EMAIL_CANT_BE_EMPTY_EMAIL)) {
                mailField.requestFocus();
                mailField.setError(message);
            }

            if(message.equals(User.NAME_CANT_BE_EMPTY_NAME)){
                nameField.requestFocus();
                nameField.setError(message);
            }
            if(message.equals(User.NAME_CANT_BE_EMPTY_NAME)){
                nameField.requestFocus();
                nameField.setError(message);
            }

            if(message.equals(User.NAME_CANT_BE_HIGHER_THAN_50)){
                nameField.requestFocus();
                nameField.setError(message);
            }

            if(message.equals(User.NAME_CANT_BE_EMPTY_NAME)){
                nameField.requestFocus();
                nameField.setError(message);
            }

            if(message.equals(User.NAME_CANT_BE_HIGHER_THAN_50)){
                nameField.requestFocus();
                nameField.setError(message);
            }

            if (message.equals(User.EMAIL_CANT_BE_HIGHER_THAN_150)) {
                mailField.requestFocus();
                mailField.setError(message);
            }

            if (message.equals(User.INVALID_EMAIL)) {
                mailField.requestFocus();
                mailField.setError(message);
            }

            if(message.equals(User.EMAIL_ARE_NOT_EQUALS)){
                mailField.requestFocus();
                mailField.setError(message);
            }

            if (message.equals(User.USERNAME_CANT_BE_EMPTY_USERNAME)) {
                usernameField.requestFocus();
                usernameField.setError(message);
            }

            if (message.equals(User.USERNAME_CANT_BE_HIGHER_THAN_100)) {
                usernameField.requestFocus();
                usernameField.setError(message);
            }

            if (message.equals(User.PASSWORD_CANT_BE_EMPTY_PASSWORD)) {
                passwordField.requestFocus();
                passwordField.setError(message);
            }

            if (message.equals(User.PASSWORD_CANT_BE_LESS_THAN_6)) {
                passwordField.requestFocus();
                passwordField.setError(message);
            }

            if(message.equals(User.PASSWORD_ARE_NOT_EQUALS)){
                passwordField.requestFocus();
                passwordField.setError(message);
            }

            if(message.equals(User.PASSWORD_ARE_NOT_EQUALS)){
                passwordField.requestFocus();
                passwordField.requestFocus();
            }

            if (message.equals(User.BIRTH_DATE_CANT_BE_EMPTY)) {
                birthDateField.requestFocus();
                birthDateField.setError(message);
            }

            if (message.equals(User.INVALID_BIRTH_DATE)) {
                birthDateField.requestFocus();
                birthDateField.setError(message);
            }
        }

    }
}
