package com.mathheals.euvou;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UserDAO;


public class ShowUserTest extends TestCase {

    private Activity activity;

    public ShowUserTest()
    {
        activity = new Activity();
    }

    public void testShowUserName(){

        UserDAO userDAO = new UserDAO(activity);

        JSONObject userData = userDAO.searchUserByUsername("igodudu");

        try {
            String nameUserDB = userData.getJSONObject("0").getString("nameUser");
           // String birthDateDB = userData.getJSONObject("0").getString("birthDate");
            //String mailDB = userData.getJSONObject("0").getString("email");

            assertEquals("Igor Duarte",nameUserDB);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch(NullPointerException except)
        {

        }

    }
    public void testShowUserBirthDate()
    {
        UserDAO userDAO = new UserDAO(activity);

        JSONObject userData = userDAO.searchUserByUsername("igodudu");

        try {
            //String nameUserDB = userData.getJSONObject("0").getString("nameUser");
             String birthDateDB = userData.getJSONObject("0").getString("birthDate");
            //String mailDB = userData.getJSONObject("0").getString("email");

            assertEquals("1995-11-14",birthDateDB);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch(NullPointerException except)
        {

        }

    }
    public void testShowUserEmail()
    {
        UserDAO userDAO = new UserDAO(activity);

        JSONObject userData = userDAO.searchUserByUsername("igodudu");

        try {
            //String nameUserDB = userData.getJSONObject("0").getString("nameUser");
            //String birthDateDB = userData.getJSONObject("0").getString("birthDate");
            String mailDB = userData.getJSONObject("0").getString("email");

            assertEquals("igor-ribeiro@hotmail.com",mailDB);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch(NullPointerException except)
        {

        }

    }
}
