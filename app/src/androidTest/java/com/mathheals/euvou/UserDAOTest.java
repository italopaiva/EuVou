package com.mathheals.euvou;

/**
 * Created by igor on 01/10/15.
 */

import com.mathheals.euvou.controller.utility.LoginUtility;

import android.app.Activity;

import com.mathheals.euvou.controller.utility.LoginUtility;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Random;

import dao.UserDAO;
import exception.UserException;
import model.User;

public class UserDAOTest extends TestCase {
    private static final String ID_FIELD =  "idField";
    private static final String COLUMN_USER_ID = "idUser";
    private static final String COLUMN_USER_NAME = "nameUser";
    private static final String COLUMN_USER_LOGIN = "login";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "passwordUser";
    private static final String COLUMN_USER_BIRTHDATE = "birthDate";
    private static final String COLUMN_USER_STATE = "isActivity";

    private Activity activity;

    public UserDAOTest()
    {
        activity = new Activity();
    }
    public void testRegisterUser(){
        try {
            UserDAO userDAO = new UserDAO(activity);
            User user = new User("Marcelo", "marceloChavosao", "galudo11cm@uol.com", "123456", "24/11/1969");
            userDAO.save(user);

            LoginUtility loginUtility = new LoginUtility();

            JSONObject jsonObject = userDAO.searchUserByUsername("marceloChavosao");

            User user2 = new User(jsonObject.getJSONObject("0").getString(COLUMN_USER_NAME),
                    jsonObject.getJSONObject("0").getString(COLUMN_USER_LOGIN),
                    jsonObject.getJSONObject("0").getString(COLUMN_USER_EMAIL),
                    jsonObject.getJSONObject("0").getString(COLUMN_USER_PASSWORD),
                    loginUtility.formatDateToBr(jsonObject.getJSONObject("0").getString(COLUMN_USER_BIRTHDATE)));;

            assertTrue(user.equals(user2));

            userDAO.delete("marceloChavosao");
        } catch (UserException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void testUpdateUser(){
        try {
            UserDAO userDAO = new UserDAO(activity);
            User user = new User(1, "Marcelo", "marceloChavosao", "12/12/2012", "galudo11cm@uol.com", "galudo11cm@uol.com", "123456", "123456");
            userDAO.save(user);

            LoginUtility loginUtility = new LoginUtility();
            int idUser = loginUtility.getUserId("marceloChavosao");

            user = new User(idUser, "Juliana", "marceloChavosao", "12/12/2012", "jarbas@uol.com", "jarbas@uol.com", "OMEUPAI!?", "OMEUPAI!?");
            userDAO.update(user);

            User user2 = loginUtility.getUser("marceloChavosao");

            assertTrue(user.equals(user2));

            userDAO.delete("marceloChavosao");
        } catch (UserException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void testDisableLogin(){
        try {
            LoginUtility loginUtility = new LoginUtility();
            User user = new User("Marcelo", "marceloChavosao", "galudo11cm@uol.com", "123456", "24/11/1969");
            UserDAO userDAO = new UserDAO(activity);
            userDAO.save(user);
            userDAO.disableUser(loginUtility.getUserId(user.getUsername()));
            assertFalse(loginUtility.isUserActive("marceloChavosao"));
            userDAO.delete("marceloChavosao");
        } catch (UserException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    

}
