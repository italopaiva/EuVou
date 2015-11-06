package com.mathheals.euvou;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by izabela on 04/11/15.
 */
public class SearchUserControlTest  extends ActivityInstrumentationTestCase2<HomePage> {

    public SearchUserControlTest() {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testIfSearchUserOptionIsAvalaibleForUserLoggedOut() {
        onView(withId(R.id.search)).check(matches(isDisplayed()));
    }

    public void testIfSearchResultIsDisplayed(){
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.search_src_text)).perform(typeText("igodudu"), pressKey(66));
        onView(withId(R.id.labelName)).check(matches(withText("Igor Duarte")));
        onView(withId(R.id.labelBirthDate)).check(matches(withText("1995-11-14")));
        onView(withId(R.id.labelMail)).check(matches(withText("igor-ribeiro@hotmail.com")));
    }

}
