package com.mathheals.euvou;

import junit.framework.TestCase;

import java.util.Random;

import exception.UserException;
import model.User;

public class UserTest extends TestCase{

    private User user;
    private final Random random = new Random();

    /* Valid entries for user id */
    public void testIfInstantiateWithValidId1(){

        try {
            user = new User(1,"maria","11/11/2015","maria@euvou.com");

            assertEquals(1, user.getIdUser());
        } catch (UserException e) {
            fail();
        }

    }

    public void testIfInstantiateWithValidRandomId(){
        int randomId = random.nextInt(Integer.MAX_VALUE - 1) + 1;
        try {
            user = new User(randomId,"maria","11/11/2015","maria@euvou.com");

            assertEquals(randomId, user.getIdUser());
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

    /*Tests invalid entries for id */

    public void testIfInstantiateWithInvalidId0() {
        boolean ok =  false;

        try {
            user = new User(0, "maria", "11/11/2015", "maria@euvou.com");
            ok = false;
        } catch (UserException e) {
            ok = true;
        }

        assertTrue(ok);
    }
}
