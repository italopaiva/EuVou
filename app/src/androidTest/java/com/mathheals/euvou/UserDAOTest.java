package com.mathheals.euvou;

/**
 * Created by igor on 01/10/15.
 */

import android.app.Activity;

import com.mathheals.euvou.controller.utility.LoginUtility;

import junit.framework.TestCase;

import org.json.JSONException;

import java.text.ParseException;
import java.util.Random;

import dao.UserDAO;
import exception.UserException;
import model.User;

public class UserDAOTest extends TestCase {
    private UserDAO userDAO;
    private User user;
    private final int LOGGED_OUT = -1;


    public void testRegisterUser(){

    }

    public void testUpdateUser(){

    }

    public void testDisableLogin(){
        try {
            LoginUtility loginUtility = new LoginUtility();
            User user = new User("Marcelo", "marceloChavosao", "galudo11cm@uol.com", "123456", "24/11/1969");
            UserDAO userDAO = new UserDAO();
            userDAO.save(user);
            userDAO.delete(loginUtility.getUserId(user.getUsername()));
            assertFalse(loginUtility.isUserActive("marceloChavosao"));
        } catch (UserException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void testDisabledAccountAccess(){

    }

    public void testUserLogin(){
    }

    public void testUserLogout(){

    }

}
