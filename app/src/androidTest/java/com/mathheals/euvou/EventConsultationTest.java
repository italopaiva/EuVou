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
import org.junit.Before;

import android.widget.EditText;
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
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by marlonmendes on 04/11/15.
 */
public class EventConsultationTest extends ActivityInstrumentationTestCase2<HomePage>{
    public EventConsultationTest() {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
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
        Espresso.pressBack();
        onView(withContentDescription("Navigate up")).check(matches(isDisplayed()));
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
    public void testButtonToMap()
    {
        onView(withId(R.id.search)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText("t"), pressKey(66));
        onData(hasToString(containsString("t")))
                .inAdapterView(withId(R.id.events_list)).atPosition(0)
                .perform(click());
        onView(withId(R.id.showEventOnMapButton)).perform(click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }
}
