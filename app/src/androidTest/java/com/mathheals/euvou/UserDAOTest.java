package com.mathheals.euvou;

/**
 * Created by igor on 01/10/15.
 */

import com.mathheals.euvou.controller.utility.LoginUtility;

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
    
    public void testRegisterUser(){
        try {
            UserDAO userDAO = new UserDAO();
            User user = new User("Marcelo", "marceloChavosao", "galudo11cm@uol.com", "123456", "24/11/1969");
            userDAO.save(user);

            LoginUtility loginUtility = new LoginUtility();
            User user2 = loginUtility.getUser("marceloChavosao");

            assertTrue(user.equals(user2));

            userDAO.delete("marceloChavosao");
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testUpdateUser(){
        try {
            UserDAO userDAO = new UserDAO();
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
            UserDAO userDAO = new UserDAO();
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
