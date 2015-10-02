package com.mathheals.euvou;

import android.widget.TextView;
import android.widget.Toast;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UserDAO;


public class ShowUserTest extends TestCase {


    public void testShowUserName(){

        UserDAO userDAO = new UserDAO();

        JSONObject userData = userDAO.searchUserByName("igodudu");

        try {
            String nameUserDB = userData.getJSONObject("0").getString("login");
           // String birthDateDB = userData.getJSONObject("0").getString("birthDate");
            //String mailDB = userData.getJSONObject("0").getString("email");

            assertEquals("igodudu",nameUserDB);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch(NullPointerException except)
        {

        }

    }
}
