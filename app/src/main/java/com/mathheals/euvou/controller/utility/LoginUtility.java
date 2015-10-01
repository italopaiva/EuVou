package com.mathheals.euvou.controller.utility;

import android.app.Activity;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UserDAO;

/**
 * Created by marlonmendes on 30/09/15.
 */
public class LoginUtility {
    private static final int LOGGED_OUT = -1;
    private static final String ID_FIELD =  "idField";
    private static final String COLUMN_USER_ID = "idUser";
    private static final String COLUMN_USER_STATE = "isActivity";
    private Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public LoginUtility(Activity activity) {
        this.activity = activity;
        sharedPreferences = activity.getSharedPreferences(ID_FIELD, activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public LoginUtility() {
    }

    public boolean hasUserLoggedIn() {
        return getUserId() != LOGGED_OUT;
    }
    // gets user's ID by username. OBS: IT'S ASSUMED THAT USERNAME DOES EXIST
    public int getUserId(String username) throws org.json.JSONException{
        UserDAO userDAO = new UserDAO();
        JSONObject jsonObject = userDAO.searchUserByUsername(username);
        return Integer.parseInt(jsonObject.getJSONObject("0").getString(COLUMN_USER_ID));
    }

    // gets current user's ID
    public int getUserId() {
        SharedPreferences sharedId = activity.getSharedPreferences(ID_FIELD, activity.MODE_PRIVATE);
        return sharedId.getInt(ID_FIELD, LOGGED_OUT);
    }

    public void setUserLogIn(int userId) {
        editor.putInt(ID_FIELD, userId);
        editor.commit();
    }

    public void setUserLogOff() {
        editor.putInt(ID_FIELD, LOGGED_OUT);
        editor.commit();
    }

    public boolean isUserActive(String username) {
        UserDAO userDAO = new UserDAO();
        JSONObject jsonObject = userDAO.searchUserByUsername(username);
        String userState = null;
        try {
            userState = jsonObject.getJSONObject("0").getString(COLUMN_USER_STATE);
            return userState == "Y";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
