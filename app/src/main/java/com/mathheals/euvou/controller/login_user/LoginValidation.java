package com.mathheals.euvou.controller.login_user;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UserDAO;

/**
 * Created by igor on 29/09/15.
 */
public class LoginValidation {
    private final String INVALID_USERNAME_MESSAGE = "Ops, acho que você digitou o login errado";
    private final String INVALID_PASSWORD_MESSAGE = "Ops, acho que você digitou a senha errada";
    private final String JSON_FORMAT = "0";
    private final String PASSWORD_USER = "passwordUser";
    Activity activity;

    public LoginValidation(Activity activity){
        this.activity=activity;
    }

    public LoginValidation(){}

    private boolean checkUsernameCharacters(String username){
        if(username.isEmpty())
            return false;
        if(username.contains(" "))
            return false;
        return true;
    }

    public boolean isUsernameRegistred(String username){
        UserDAO userDAO = new UserDAO();

        JSONObject json = userDAO.searchUserByUsername(username);

        if(json!=null){
            return true;
        }else{
            return false;
        }

    }

    public boolean isUsernameValid(String username){

        return checkUsernameCharacters(username) && isUsernameRegistred(username);
    }

    public boolean checkPassword(String validUsername, String passwordTyped){
        UserDAO userDAO = new UserDAO();

        JSONObject json = userDAO.searchUserByUsername(validUsername);

        try {
            String password = json.getJSONObject(JSON_FORMAT).getString(PASSWORD_USER);

            if(password.equals(passwordTyped)){
                return true;
            }else{
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String getInvalidUsernameMessage() {
        return INVALID_USERNAME_MESSAGE;
    }

    public String getInvalidPasswordMessage() {
        return INVALID_PASSWORD_MESSAGE;
    }

}
