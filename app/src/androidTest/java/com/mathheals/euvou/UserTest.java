package com.mathheals.euvou;

import junit.framework.TestCase;

import exception.UserException;
import model.User;

public class UserTest extends TestCase{

    private User user;

    /* Valid entries for user id */
    public void testIfInstantiateWithValidId1(){

        try {
            user = new User(1,"maria","11/11/2015","maria@euvou.com");

            assertEquals(1, user.getIdUser());
        } catch (UserException e) {
            fail();
        }

    }

    public void testIfInstantiateWithValidId2(){

        try {
            user = new User(2,"maria","11/11/2015","maria@euvou.com");

            assertEquals(2, user.getIdUser());
        } catch (UserException e) {
            fail();
        }

    }
    
}
