package com.mathheals.euvou;

import android.app.Application;
import android.test.ApplicationTestCase;
import model.User;
import android.test.ActivityUnitTestCase;

//<a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>

public class ApplicationTest extends ApplicationTestCase<Application> {

    public ApplicationTest(Class<Application> applicationClass) {
        super(applicationClass);
    }


    public void TestNameIsEmpty() throws Exception{
        User user = new User("","","","","");
        String a = "ola";
        String b = "ola";
        assertEquals(a,b);

    }

}