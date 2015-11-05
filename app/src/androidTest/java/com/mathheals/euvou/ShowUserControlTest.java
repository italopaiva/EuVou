package com.mathheals.euvou;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.mathheals.euvou.controller.home_page.HomePage;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by izabela on 05/11/15.
 */
public class ShowUserControlTest  extends ActivityInstrumentationTestCase2<HomePage> {

    public ShowUserControlTest() {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testIfLabelsAreDisplayed() {
        onView(withId(R.id.search)).check(matches(isDisplayed()));
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.search_src_text)).perform(typeText("igodudu"), pressKey(66));
        onView(withId(R.id.name)).check(matches(withText("Nome:")));
        onView(withId(R.id.birthDateShown)).check(matches(withText("Data de nascimento")));
        onView(withId(R.id.emailShown)).check(matches(withText("E-mail")));

    }

}
