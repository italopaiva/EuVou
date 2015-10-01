package com.mathheals.euvou;

/**
 * Created by igor on 01/10/15.
 */

import com.mathheals.euvou.controller.utility.LoginUtility;

import junit.framework.TestCase;

import java.text.ParseException;
import java.util.Random;

import dao.UserDAO;
import exception.UserException;
import model.User;

public class UserDAOTest extends TestCase {
    private UserDAO userDAO = new UserDAO();

    public void testRegisterUser(){
        try {
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

    }

    public void testDisableLogin(){

    }

    public void testDisabledAccountAccess(){

    }

    public void testUserLogin(){

    }

    public void testUserLogout(){

    }

}
