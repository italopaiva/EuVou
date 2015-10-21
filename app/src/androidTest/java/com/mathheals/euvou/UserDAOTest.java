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
    public void testSave() throws ParseException, UserException {
            UserDAO userDAO = new UserDAO(activity);
            User user;
        user = new User("marceloChavosaao","marceloChavosaao","marceloChavosao@euvou.com","marceloChavosao@euvou.com","123456","123456","11/09/2015");
        assertTrue(userDAO.save(user).contains("Salvo"));
            userDAO.delete("marceloChavosaao");
    }

    public void testDeleteByName() throws ParseException, UserException  {

        UserDAO userDAO = new UserDAO(activity);
        User user = new User("Marcelo", "marceloChavosaoa", "galudo11cm@uol.com", "123456", "24/11/1969");
        if(!userDAO.save(user).contains("Salvo"))
            assertTrue(false);
        assertTrue(userDAO.delete("marceloChavosaoa").contains("Salvo"));
    }


    public void testDeleteById() throws ParseException, UserException, JSONException {

        UserDAO userDAO = new UserDAO(activity);
        User user = new User("VIny", "viny", "viny@uol.com", "123456", "14/02/1995");
        if(!userDAO.save(user).contains("Salvo"))
            assertTrue(false);
        int id = userDAO.searchUserByUsername("viny").getJSONObject("0").getInt("idUser");
        assertTrue(userDAO.delete(id).contains("Salvo"));
        userDAO.delete("viny");
    }

    public void testeSearchUserById()
    {
        assertFalse(new UserDAO(activity).searchUserById(3) == null);
    }

    public void testUpdateUser() throws ParseException, UserException, JSONException {
        UserDAO userDAO = new UserDAO(activity);
        User user = new User(1,"Vinicius ppp", "umteste", "14/02/1995", "viny-pinheiro@hotmail.com",
                "viny-pinheiro@hotmail.com", "123456", "123456");
        if(!userDAO.save(user).contains("Salvo")) {
            assertTrue(false);
            userDAO.delete("umteste");
        }
        assertTrue(userDAO.update(user).contains("Salvo"));
        userDAO.delete("umteste");

    }

    public void testDisableLogin() throws ParseException, UserException, JSONException {
        UserDAO userDAO = new UserDAO(activity);
        User user = new User(1,"Vinicius Pinheiro", "umteste", "14/02/1995", "viny-pinheiro@hotmail.com",
                "viny-pinheiro@hotmail.com", "123456", "123456");
        if(!userDAO.save(user).contains("Salvo")) {
            assertTrue(false);
            userDAO.delete("umteste");
        }
        int id = userDAO.searchUserByUsername("umteste").getJSONObject("0").getInt("idUser");
        assertTrue(userDAO.disableUser(id).contains("Salvo"));
        userDAO.delete("umteste");

    }
    

}
