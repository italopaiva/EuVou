package com.mathheals.euvou;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.mathheals.euvou.controller.Validator;

//<a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>

public class ApplicationTest extends ApplicationTestCase<Application> {


    public ApplicationTest(Class<Application> applicationClass) {
        super(applicationClass);
    }

    public void TestIfMailIsValid() throws Exception{
        assertFalse(Validator.isMailValid("fulanoagmail.com"));
    }

    public void TestIfMailIsNotValid()throws Exception{
        assertTrue(Validator.isMailValid("fulano@gmail.com"));
    }

    public void TestIfMailAndConfirmMailValidAreEquals()throws Exception{
        assertTrue(Validator.isMailConfirmationValid("ze@euvou.com", "ze@euvou.com"));
    }

    public void TestIfMailAndConfirmMailValidAreDifferent()throws Exception{
        assertFalse(Validator.isMailConfirmationValid("ze@euvou.com", "we@euvou.com"));
    }

    public void TestConfirmIfNameIsEmpty()throws Exception{
        assertNull(Validator.isNameEmpty(" "));
    }

    public void TestConfirmIfNameIsNotEmpty()throws Exception{
        assertNull(Validator.isNameEmpty("zzz"));
    }

    public void TestConfirmIfDateIsEmpty()throws Exception{
        assertNull(Validator.isDateEmpty(" "));
    }

    public void TestConfirmIfDateIsNotEmpty()throws Exception{
        assertNull(Validator.isDateEmpty("11/02/2015"));
    }

    public void TestIfMailIsEmpty()throws Exception{
        assertNull(Validator.isMailEmpty("fulano@gmail.com "));
    }

    public void TestIfMailIsNotEmpty()throws Exception{
        assertNull(Validator.isMailEmpty(" "));
    }

    public void TestIfConfirmMailIsEmpty()throws Exception{
        assertNull(Validator.isMailEmpty(" "));}

    public void TestIfConfirmMailIsNotEmpty()throws Exception{
        assertNull(Validator.isMailEmpty("fulano@gmail.com"));
    }

    public void TestConfirmIfLoginIsNotEmpty()throws Exception{
        assertNull(Validator.isNameEmpty("zzz"));
    }

    public void TestConfirmIfLoginoIsEmpty()throws Exception{
        assertNull(Validator.isLoginEmpty(" "));
    }

    public void TestConfirmIfPasswordIsNotEmpty()throws Exception{
        assertNull(Validator.isPasswordEmpty("123"));
    }

    public void TestConfirmIfPasswordIsEmpty()throws Exception{
        assertNull(Validator.isPasswordEmpty(" "));
    }

    public void TestConfirmIfConfirmPasswordIsNotEmpty()throws Exception{
        assertNull(Validator.isConfirmPasswordEmpty("123"));
    }

    public void TestConfirmIfConfirmPasswordIsEmpty()throws Exception{
        assertNull(Validator.isConfirmPasswordEmpty(" "));
    }

}