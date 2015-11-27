package com.mathheals.euvou.controller.user_registration;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.login_user.LoginActivity;
import com.mathheals.euvou.controller.utility.Mask;

import dao.UserDAO;
import model.User;

public class RegisterFragment extends Fragment implements View.OnClickListener {


    private static final String SUCCESSFULL_CADASTRATION_MESSAGE = "Bem vindo ao #EuVou :)";

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_user, container, false);
        Button register = (Button) view.findViewById(R.id.saveButton);
        register.setOnClickListener(this);
        EditText dateField = (EditText) view.findViewById(R.id.dateField);
        dateField.addTextChangedListener(Mask.insert("##/##/####", dateField));

        return view;
    }

    private void registerUser(User user) {

        UserDAO userDAO = new UserDAO(getActivity());
        userDAO.save(user);

    }

    @Override
    public void onClick(View v) {

        EditText nameField = (EditText) this.getActivity().findViewById(R.id.nameField);
        String name = nameField.getText().toString();

        EditText birthDateField = (EditText) this.getActivity().findViewById(R.id.dateField);
        String birthDate = birthDateField.getText().toString();

        EditText mailField = (EditText) this.getActivity().findViewById(R.id.mailField);
        String mail = mailField.getText().toString();

        EditText mailConfirmField = (EditText) this.getActivity().findViewById(R.id.confirmMailField);
        String mailConfirm = mailConfirmField.getText().toString();

        EditText usernameField = (EditText) this.getActivity().findViewById(R.id.loginField);
        String username = usernameField.getText().toString();

        EditText passwordField = (EditText) this.getActivity().findViewById(R.id.passwordField);
        String password = passwordField.getText().toString();

        EditText passwordConfirmField = (EditText) this.getActivity().findViewById(R.id.confirmMailPassword);
        String passwordConfirm = passwordConfirmField.getText().toString();

        try {
            User user = new User(name, username, mail, mailConfirm, password, passwordConfirm, birthDate);
            registerUser(new User(name, username, mail, password, birthDate));

            Toast.makeText(getActivity().getBaseContext(), SUCCESSFULL_CADASTRATION_MESSAGE, Toast.LENGTH_LONG).show();
            Activity activity = getActivity();
            Intent myIntent = new Intent(activity, LoginActivity.class);
            activity.startActivity(myIntent);

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

            if (message.equals(User.BIRTH_DATE_CANT_BE_EMPTY)) {
                birthDateField.requestFocus();
                birthDateField.setError(message);
            }

            if (message.equals(User.INVALID_BIRTH_DATE)) {
                birthDateField.requestFocus();
                birthDateField.setError(message);
            }

            if(message.equals(User.USERNAME_EXISTENT)){
                usernameField.requestFocus();
                usernameField.setError(message);
            }

            if(message.equals(User.CONFIRM_PASSWORD_CANT_BE_EMPTY)){
                passwordConfirmField.requestFocus();
                passwordConfirmField.setError(message);
            }

            if(message.equals(User.EMAIL_CONFIRMATION_CANT_BE_EMPTY)){
                mailConfirmField.requestFocus();
                mailConfirmField.setError(message);
            }
        }

    }
}
