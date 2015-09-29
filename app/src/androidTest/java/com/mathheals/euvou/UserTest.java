package com.mathheals.euvou;

import junit.framework.TestCase;

import java.text.ParseException;
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void testIfInstantiateWithValidRandomId(){
        int randomId = random.nextInt(Integer.MAX_VALUE - 1) + 1;
        try {
            user = new User(randomId,"maria","11/11/2015","maria@euvou.com");

            assertEquals(randomId, user.getIdUser());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void testIfInstantiateWithValidId2(){

        try {
            user = new User(2,"maria","11/11/2015","maria@euvou.com");

            assertEquals(2, user.getIdUser());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void testIfInstantiateWithValidIdMaxMenosUm(){

        try {
            user = new User(Integer.MAX_VALUE-1,"maria","11/11/2015","maria@euvou.com");

            assertEquals(Integer.MAX_VALUE-1, user.getIdUser());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void testIfInstantiateWithValidIdMax(){

        try {
            user = new User(Integer.MAX_VALUE,"maria","11/11/2015","maria@euvou.com");

            assertEquals(Integer.MAX_VALUE, user.getIdUser());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(ok);
    }

    public void testIfInstantiateWithInvalidIdLessOne() {
        boolean ok =  false;

        try {
            user = new User(-1, "maria", "11/11/2015", "maria@euvou.com");
            ok = false;
        } catch (UserException e) {
            ok = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(ok);
    }

    public void testIfInstantiateWithInvalidIdMin() {
        boolean ok =  false;

        try {
            user = new User(Integer.MIN_VALUE, "maria", "11/11/2015", "maria@euvou.com");
            ok = false;
        } catch (UserException e) {
            ok = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(ok);
    }

    public void testIfInstantiateWithInvalidIdMinMoreOne() {
        boolean ok =  false;

        try {
            user = new User(Integer.MIN_VALUE+1, "maria", "11/11/2015", "maria@euvou.com");
            ok = false;
        } catch (UserException e) {
            ok = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(ok);
    }

    /*public void testIfInstantiateWithInvalidRandomId(){
        boolean ok = false;
        int randomId = random.nextInt(Integer.MIN_VALUE + 1) - 1 ;
        try {
            user = new User(randomId, "maria", "11/11/2015", "maria@euvou.com");
            ok = false;
        } catch (UserException e) {
            ok = true;

        }
        assertTrue(ok);
    }
    */

     /* Valid entries for user id */
    public void testIfNameIsValid(){
        try {
            user = new User(3,"maria","11/11/2015","maria@euvou.com");

           assertEquals("maria", user.getName() );
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfNameHasUntil50Caracteres(){
        try {
            user = new User(3,"dsedfghjklljhgfdswasdfghjkjhgfdsdfghjkjhgfdsasdfghjkjhgfdsasdfghjmkjhgfdsasdfgbhnmhgfdsdfgdsd","11/11/2015","maria@euvou.com");

            assertEquals("dsedfghjklljhgfdswasdfghjkjhgfdsdfghjkjhgfdsasdfghjkjhgfdsasdfghjmkjhgfdsasdfgbhnmhgfdsdfgdsd", user.getName());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfDateIsValid(){
        try {
            user = new User(3,"maria","11/11/2015","maria@euvou.com");
            assertEquals("11/11/2015", user.getBirthDate() );
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfMonthIsValid(){
        try {
            user = new User(3,"maria","11/13/2015","maria@euvou.com");
            assertEquals("11/13/2015", user.getBirthDate() );
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfDayIsValid(){
        try {
            user = new User(3,"maria","30/02/2015","maria@euvou.com");
            assertEquals("30/02/2015", user.getBirthDate() );
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfYearIsValid(){
        try {
            user = new User(3,"maria","30/02/0","maria@euvou.com");
            assertEquals("30/02/0", user.getBirthDate() );
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValid(){
        try {
            user = new User(3,"maria","22/02/2015","maria@euvou.com");
            assertEquals("maria@euvou.com", user.getEmail() );
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValidCapps(){
        try {
            user = new User(3,"maria","22/02/2015","MARIA@euvou.com");
            assertEquals("MARIA@euvou.com", user.getEmail() );
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValidWithNumbers(){
        try {
            user = new User(3,"maria","22/02/2015","1102@euvou.com");
            assertEquals("1102@euvou.com", user.getEmail() );
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValidUnderline(){
        try {
            user = new User(3,"maria","22/02/2015","a_maria@euvou.com");
            assertEquals("a_maria@euvou.com", user.getEmail() );
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValidWithDash(){
        try {
            user = new User(3,"maria","22/02/2015","a-maria@euvou.com");
            assertEquals("a-maria@euvou.com", user.getEmail() );
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValidWithPoint(){
        try {
            user = new User(3,"maria","22/02/2015","a.maria@euvou.com");
            assertEquals("a.maria@euvou.com", user.getEmail() );
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValidWithPlus(){
        try {
            user = new User(3,"maria","22/02/2015","a+maria@euvou.com");
            assertEquals("a+maria@euvou.com", user.getEmail() );
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

