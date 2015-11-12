package com.mathheals.euvou;

import android.support.test.espresso.Espresso;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.event_consultation.EventConsultation;
import com.mathheals.euvou.controller.home_page.HomePage;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.junit.Before;

import android.widget.EditText;

import dao.EventDAO;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by marlonmendes on 04/11/15.
 */
public class EventConsultationTest extends ActivityInstrumentationTestCase2<HomePage>{
    private LoginUtility isLoged;
    private EventDAO eventDAO;

    public EventConsultationTest() {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
        isLoged = new LoginUtility(getActivity());
        eventDAO = new EventDAO();
    }

    public void testIfEventConsultationIsOpened() {
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.radio_events)).check(matches(isDisplayed()));
        onView(withId(R.id.radio_people)).check(matches(isDisplayed()));
        onView(withId(R.id.radio_places)).check(matches(isDisplayed()));
    }

    public void testIfAnyEventWasFound() {
        onView(withId(R.id.search)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText("t"), pressKey(66));
        onData(hasToString(containsString("t")))
                .inAdapterView(withId(R.id.events_list)).atPosition(0)
                .perform(click());
        onView(withId(R.id.event_name_text)).check(matches(isDisplayed()));

    }

    public void testIfEventConsultationReturnsToHomePage() {
        onView(withId(R.id.search)).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());
        onView(withText("EuVou")).check(matches(isDisplayed()));
    }

    public void testConsultationByCategory() {
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.radio_people))
                .perform(click());
        onView(withId(R.id.radio_people))
                .check(matches(isChecked()));
        onView(withId(R.id.radio_places))
                .perform(click());
        onView(withId(R.id.radio_places))
                .check(matches(isChecked()));
        onView(withId(R.id.radio_events))
                .perform(click());
        onView(withId(R.id.radio_events))
                .check(matches(isChecked()));
    }
    
    public void testButtonToMap() {
        onView(withId(R.id.search)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText("t"), pressKey(66));
        onData(hasToString(containsString("t")))
                .inAdapterView(withId(R.id.events_list)).atPosition(0)
                .perform(click());
        onView(withId(R.id.showEventOnMapButton)).perform(click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    public void testMarkParticipateNotLoged()
    {
        if(isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Sair")).perform(click());
        }

        onView(withId(R.id.search)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText("t"), pressKey(66));
        onData(hasToString(containsString("t")))
                .inAdapterView(withId(R.id.events_list)).atPosition(0)
                .perform(click());
        onView(withId(R.id.EuVou)).check(matches(not(isDisplayed())));
    }

    private void markClique()
    {
        onView(withId(R.id.search)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText("t"), pressKey(66));
        onData(hasToString(containsString("t")))
                .inAdapterView(withId(R.id.events_list)).atPosition(0)
                .perform(click());
    }


    public void testMarkParticipateTwoTimeLoged() {
        if (!isLoged.hasUserLoggedIn()) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        markClique();
        onView(withId(R.id.EuVou)).perform(click());
        onView(withId(R.id.EuVou)).perform(click());

        onView(withId(R.id.search)).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());
        onView(withText("EuVou")).check(matches(isDisplayed()));
        markClique();
        onView(withId(R.id.EuVou)).perform(click());
        onView(withId(R.id.EuVou)).perform(click());
    }

    public void testMarkOffParticipateTwoTimeLoged() {
        if (!isLoged.hasUserLoggedIn()) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        markClique();
        onView(withId(R.id.EuVou)).perform(click());
        onView(withId(R.id.EuVou)).perform(click());

        onView(withId(R.id.search)).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());
        onView(withText("EuVou")).check(matches(isDisplayed()));
        markClique();
        onView(withId(R.id.EuVou)).perform(click());
        onView(withId(R.id.EuVou)).perform(click());
    }
}
