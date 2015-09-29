package com.mathheals.euvou.controller.login_user;

import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UserDAO;

/**
 * Created by igor on 29/09/15.
 */
public class LoginValidation {
    private final String INVALID_USERNAME_MESSAGE = "Ops, acho que você digitou o login errado";
    private final String INVALID_PASSWORD_MESSAGE = "Ops, acho que você digitou a senha errada";

    private boolean checkUsernameCharacters(String username){

        return true;
    }

    private boolean isUsernameRegistred(String username){

        return true;
    }

    public boolean isUsernameValid(String username){

        return checkUsernameCharacters(username) && isUsernameRegistred(username);
    }

    public boolean checkPassword(String validUsername, String password){
        return true;
    }

    public String getInvalidUsernameMessage() {
        return INVALID_USERNAME_MESSAGE;
    }

    public String getPasswordUsernameMessage() {
        return INVALID_PASSWORD_MESSAGE;
    }
    
}
