package com.mathheals.euvou;
import junit.framework.TestCase;
import java.text.ParseException;
import java.util.Random;
import exception.UserException;
import model.User;

public class UserTest extends TestCase {

    private User user;
    private final Random random = new Random();

    /* Valid entries for user id */
    public void testIfInstantiateWithValidId1() {

        try {
            user = new User(1, "maria", "11/09/2015", "maria@euvou.com");

            assertEquals(1, user.getIdUser());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void testIfInstantiateWithValidRandomId() {
        int randomId = random.nextInt(Integer.MAX_VALUE - 1) + 1;
        try {
            user = new User(randomId, "maria", "11/09/2015", "maria@euvou.com");

            assertEquals(randomId, user.getIdUser());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void testIfInstantiateWithValidId2() {

        try {
            user = new User(2, "maria", "11/09/2015", "maria@euvou.com");

            assertEquals(2, user.getIdUser());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void testIfInstantiateWithValidIdMaxMenosUm() {

        try {
            user = new User(Integer.MAX_VALUE - 1, "maria", "11/09/2015", "maria@euvou.com");

            assertEquals(Integer.MAX_VALUE - 1, user.getIdUser());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void testIfInstantiateWithValidIdMax() {

        try {
            user = new User(Integer.MAX_VALUE, "maria", "11/09/2015", "maria@euvou.com");

            assertEquals(Integer.MAX_VALUE, user.getIdUser());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    /*Tests invalid entries for id */

    public void testIfInstantiateWithInvalidId0() {
        boolean ok = false;

        try {
            user = new User(0, "maria", "11/09/2015", "maria@euvou.com");
            ok = false;
        } catch (UserException e) {
            ok = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(ok);
    }

    public void testIfInstantiateWithInvalidIdLessOne() {
        boolean ok = false;

        try {
            user = new User(-1, "maria", "11/09/2015", "maria@euvou.com");
            ok = false;
        } catch (UserException e) {
            ok = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(ok);
    }

    public void testIfInstantiateWithInvalidIdMin() {
        boolean ok = false;

        try {
            user = new User(Integer.MIN_VALUE, "maria", "11/09/2015", "maria@euvou.com");
            ok = false;
        } catch (UserException e) {
            ok = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(ok);
    }

    public void testIfInstantiateWithInvalidIdMinMoreOne() {
        boolean ok = false;

        try {
            user = new User(Integer.MIN_VALUE + 1, "maria", "11/09/2015", "maria@euvou.com");
            ok = false;
        } catch (UserException e) {
            ok = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(ok);
    }




    /* Valid entries for user Name */
    public void testIfNameIsValid() {
        try {
            user = new User(3, "maria", "11/09/2015", "maria@euvou.com");

            assertEquals("maria", user.getName());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfNameHasWithLessThan50Caracteres() {
        boolean ok= false;

        try {
            user = new User(3, "oioioi", "11/09/2015", "maria@euvou.com");
            ok = false;
        } catch (UserException e) {
            ok = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertFalse(ok);

    }

    /* Ivanlid test of name */
    public void testIfNameHasWithMore50Caracteres() {
        boolean ok= false;

        try {
            user = new User(3, "dsedfghjklljhgfdswasdfghjkjhgfdsdfghjkjhgfdsasdfghjkjhgfdsasdfghjmkjhgfdsasdfgbhnmhgfdsdfgdsd", "11/11/2015", "maria@euvou.com");
            ok = false;
        } catch (UserException e) {
            ok = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(ok);

    }

    public void testIfNameIsEmpty() {
        boolean ok = true;
        try {
            user = new User(2,"","11/02/2015","maria@euvou.com");
        } catch (ParseException e) {
            e.printStackTrace();

        } catch (UserException e) {
            e.printStackTrace();
            ok = false;
        }
        assertFalse(ok);

    }

     /* Valid entries for user Date */


    public void testIfMonthIsValid() {

        boolean ok = false;

        try {
            user = new User(3, "maria", "11/13/2015", "maria@euvou.com");
            ok = false;
        } catch (UserException e) {
            ok = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(ok);
    }


    public void testIfDateIsBissextile() {
        boolean ok= true;
        try {
            user = new User(3, "maria", "29/02/2000", "maria@euvou.com");
            ok=true;
        } catch (UserException e) {
            ok = false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue(ok);
    }

    public void testIfDateIsNotBissextile() {
        boolean ok= true;
        try {
            user = new User(3, "maria", "29/02/2001", "maria@euvou.com");
            ok=true;
        } catch (UserException e) {
            ok = false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertFalse(ok);
    }

    public void testIfDateIsValid() {
        boolean ok= true;
        try {
            user = new User(3, "maria", "11/11/2015", "maria@euvou.com");
            ok=true;
        } catch (UserException e) {
            ok = false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertFalse(ok);
    }

    /* Valid entries for user email*/

    public void testIfEmailIsValid() {
        try {
            user = new User(3, "maria", "22/02/2015", "maria@euvou.com" +
                    "");
            assertEquals("maria@euvou.com", user.getEmail());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValidCapps() {
        try {
            user = new User(3, "maria", "22/02/2015", "MARIA@euvou.com");
            assertEquals("MARIA@euvou.com", user.getEmail());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValidWithNumbers() {
        try {
            user = new User(3, "maria", "22/02/2015", "1102@euvou.com");
            assertEquals("1102@euvou.com", user.getEmail());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValidUnderline() {
        try {
            user = new User(3, "maria", "22/02/2015", "a_maria@euvou.com");
            assertEquals("a_maria@euvou.com", user.getEmail());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValidWithDash() {
        try {
            user = new User(3, "maria", "22/02/2015", "a-maria@euvou.com");
            assertEquals("a-maria@euvou.com", user.getEmail());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValidWithPoint() {
        try {
            user = new User(3, "maria", "22/02/2015", "a.maria@euvou.com");
            assertEquals("a.maria@euvou.com", user.getEmail());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfEmailIsValidWithPlus() {
        try {
            user = new User(3, "maria", "22/02/2015", "a+maria@euvou.com");
            assertEquals("a+maria@euvou.com", user.getEmail());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /* Invalid entries for user email */
    public void testEmailWithInValid() {
        try {
            user = new User(3, "maria", "22/02/2015", "amaria @euvou.com");
            assertTrue(false);
        } catch (UserException e) {
            assertTrue(true);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    public void testEmailWithMore150characteres() {
        try {
            user = new User(3, "maria", "22/02/2015", "amariaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@euvou.com");
            assertTrue(false);
        } catch (UserException e) {
            assertTrue(true);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }
    /* Valid entries for user password */
    public void testIfPasswordIsValidTo7Caracteres() {

        try {
            user = new User("maria", "Mariazinha", "maria@euvou.com", "1234567", "11/09/2015");
            assertEquals("1234567", user.getPassword());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfPasswordIsValidTo6caracteres() {

        try {
            user = new User("maria", "Mariazinha", "maria@euvou.com", "123456", "11/09/2015");
            assertEquals("123456", user.getPassword());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfPasswordIsBiggerThanSix() {
        try {
            user = new User("maria", "Mariazinha", "maria@euvou.com", "1234567", "11/09/2015");
            assertEquals ("1234567",user.getPassword());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testIfUsernameIsEmpty(){
        boolean ok = false;
        try {
            user = new User("Maria","","maria@euvou.com","123456","11/02/1234");
            ok = true;
        } catch (ParseException e) {
            e.printStackTrace();

        } catch (UserException e) {
            e.printStackTrace();
            ok = false;
        }
        finally {
            assertFalse(ok);
        }
    }


    public void testIfUsernameIsExists(){
        boolean ok = false;
        try {
            user = new User("Maria","igodudu","maria@euvou.com","123456","11/02/1234");
            ok = true;
        } catch (ParseException e) {
            e.printStackTrace();

        } catch (UserException e) {
            e.printStackTrace();
            ok = false;
        }
        finally {
            assertFalse(ok);
        }
    }

    public void testIfEmailIsEmpty(){
        boolean ok = true;
        try {
            user = new User("Julliana","Ju","","123456","11/02/1234");
        } catch (ParseException e) {
            e.printStackTrace();

        } catch (UserException e) {
            e.printStackTrace();
            ok = false;
        }
        assertFalse(ok);
    }

    public void testIfPasswordIsEmpty(){
        boolean ok = true;
        try {
            user = new User("Julliana","Ju","ju@eu.com","","11/02/1234");
        } catch (ParseException e) {
            e.printStackTrace();

        } catch (UserException e) {
            e.printStackTrace();
            ok = false;
        }
        assertFalse(ok);
    }

    public void testIfDateBirthIsEmpty(){
        boolean ok = true;
        try {
            user = new User("Julliana","Ju","ju@eu.com","123456","");
        } catch (ParseException e) {
            e.printStackTrace();

        } catch (UserException e) {
            e.printStackTrace();
            ok = false;
        }
        assertFalse(ok);
    }


    // Test valid in order to Username

    public void testIfUsernameIsValid() {
        try {
            user = new User("Julliana","Ju","ju@eu.com","123456","11/02/2000");
            assertEquals("Ju", user.getUsername());
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            fail();
        }
    }

    public void testGetBirthDate()
    {
        try {
            user = new User("Julliana","Ju","ju@eu.com","123456","11/02/2000");
            assertEquals(user.getBirthDate(),"11/02/2000");
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            fail();
        }
    }

    public void testWithTwoUserIsSame()
    {
        try {
            User user1 = new User("Julliana","Ju","ju@eu.com","123456","11/02/2000");
            User user2 = new User("Julliana","Ju","ju@eu.com","123456","11/02/2000");
            assertTrue(user1.equals(user2));
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            fail();
        }
    }

    public void testWithTwoUserIsNotSame()
    {
        try {
            User user1 = new User("Julliana","Ju","ju@eu.com","123456","11/02/2000");
            User user2 = new User("Jullianaa","Ju","ju@eu.com","123456","11/02/2000");
            assertFalse(user1.equals(user2));
        } catch (UserException e) {
            fail();
        } catch (ParseException e) {
            fail();
        }
    }

    public void testIfInstantiatedConstructWith8ValidArguments()
    {
        try {
            user = new User(1, "maria","mama", "11/09/2015", "maria@euvou.com","maria@euvou.com","123456","123456");
            assertTrue(true);
        } catch (UserException e) {
            assertTrue(false);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }
    public void testIfInstantiatedConstructWith8ArgumentsWithInvalidArgument()
    {
        try {
            user = new User(1, "maria","mama", "11/09/2015", "maria@euvoua.com","maria@euvou.com","123456","123456");
            assertFalse(true);
        } catch (UserException e) {
            assertFalse(false);
        } catch (ParseException e) {
            assertFalse(false);
        }
    }

    public void testIfInstantiatedConstructWith7ValidArguments()
    {
        try {
            user = new User(1, "maria","11/09/2015", "maria@euvou.com","maria@euvou.com","123456","123456");
            assertTrue(true);
        } catch (UserException e) {
            assertTrue(false);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    public void testIfInstantiatedConstructWith7ArgumentsWithInvalidArgument()
    {
        try {
            user = new User(1, "maria", "11/09/2015", "maria@euvoua.com","maria@euvou.com","123456","123456");
            assertFalse(true);
        } catch (UserException e) {
            assertFalse(false);
        } catch (ParseException e) {
            assertFalse(false);
        }
    }

    public void testInvalidUsernameWithMore100characters()
    {

        try {
            user = new User( "maria","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","maria@euvou.com","12346", "11/09/2015");
            assertFalse(true);
        } catch (UserException e) {
            assertFalse(false);
        } catch (ParseException e) {
            assertFalse(false);
        }
    }
    public void testInvalidPasswordLess6Characters()
    {
        try {
            user = new User("Julliana","Ju","ju@eu.com","1234","11/02/2000");
            assertFalse(true);
        } catch (UserException e) {
            assertFalse(false);
        } catch (ParseException e) {
            assertFalse(false);
        }
    }

    public void testVerifyPasswordConfirmation()
    {
        try {
            user = new User(1, "maria","11/09/2015", "maria@euvou.com","maria@euvou.com","123456","12s3456");
            assertFalse(true);
        } catch (UserException e) {
            assertFalse(false);
        } catch (ParseException e) {
            assertFalse(false);
        }
    }


    public void testIfInstantiatedConstructWith7StringsValidArguments()
    {
        try {
            user = new User("maria","teste","maria@euvou.com","maria@euvou.com","123456","123456","11/09/2015");
            assertTrue(true);
        } catch (UserException e) {
            assertTrue(false);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    public void testIfInstantiatedConstructWith7StringsArgumentsWithInvalidArgument()
    {
        try {
            user = new User("maria","teste","maria@euvoa.com","maria@euvou.com","123456","123456","11/09/2015");
            assertFalse(true);
        } catch (UserException e) {
            assertFalse(false);
        } catch (ParseException e) {
            assertFalse(false);
        }
    }

}
