package com.mathheals.euvou;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.junit.Before;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;

/**
 * Created by izabela on 15/11/15.
 */
public class EvalueteEventControlTest extends ActivityInstrumentationTestCase2<HomePage> {

    LoginUtility isLoged;

    public EvalueteEventControlTest() {
            super(HomePage.class);
            }

    @Before
    public void setUp() throws Exception {
            super.setUp();
            getActivity();
            isLoged = new LoginUtility(getActivity());
    }

    public void testIfEditUserOptionIsDisplayedForUserLoggedOut() {
        if(isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Sair")).perform(click());
        }
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.radio_events)).perform(click());
        onView(withId(R.id.search_src_text)).perform(typeText("t"), pressKey(66));
        onData(hasToString(containsString("Teste")))
                .inAdapterView(withId(R.id.events_list)).atPosition(0)
                .perform(click());
        closeSoftKeyboard();
        assertFalse(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE).matches(onView(withId(R.id.ratingBar))));
    }

    public void testIfEvaluetationIsCorrect() {
        if (!isLoged.hasUserLoggedIn()) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.radio_events)).perform(click());
        onView(withId(R.id.search_src_text)).perform(typeText("t"), pressKey(66));
        onData(hasToString(containsString("Teste")))
                .inAdapterView(withId(R.id.events_list)).atPosition(0)
                .perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.ratingBar)).check(matches(isDisplayed()));
    }
}
