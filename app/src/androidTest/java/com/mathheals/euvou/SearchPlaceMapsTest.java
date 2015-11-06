package com.mathheals.euvou;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;

import org.junit.Before;
import org.junit.Rule;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by viny on 04/11/15.
 */
public class SearchPlaceMapsTest extends ActivityInstrumentationTestCase2<HomePage> {

    public SearchPlaceMapsTest() {
        super(HomePage.class);
    }
    @Rule
    public ActivityTestRule<HomePage> activityTestRule
            = new ActivityTestRule<>(HomePage.class);
    @Before
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testSelectOption0() {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(0)
                .perform(click());

        onView(
                withId(R.id.map)
        ).check(
                matches(
                        isDisplayed()
                )
        );
    }
    public void testSelectOption1() {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(1)
                .perform(click());

        onView(
                withId(R.id.map)
        ).check(
                matches(
                        isDisplayed()
                )
        );
    }
    public void testSelectOption2() {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(2)
                .perform(click());

        onView(
                withId(R.id.map)
        ).check(
                matches(
                        isDisplayed()
                )
        );
    }
    public void testSelectOption3() {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(3)
                .perform(click());

        onView(
                withId(R.id.map)
        ).check(
                matches(
                        isDisplayed()
                )
        );
    }
    public void testSelectOption4() {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(4)
                .perform(click());

        onView(
                withId(R.id.map)
        ).check(
                matches(
                        isDisplayed()
                )
        );
    }
    public void testSelectOption5() {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(5)
                .perform(click());

        onView(
                withId(R.id.map)
        ).check(
                matches(
                        isDisplayed()
                )
        );
    }
    public void testWriteOption() {
        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.place_search)).perform(typeText("catet"));
        //onView(withId(R.id.search_button)).perform(click());
        onView(withId(R.id.search_place_button)).perform(click());

        onView(
                withId(R.id.map)
        ).check(
                matches(
                        isDisplayed()
                )
        );
    }
    public void testNullOption() {
        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.place_search)).perform(typeText(""));
        onView(withId(R.id.search_place_button)).perform(click());

        onView(
                withId(R.id.place_search)
        ).check(
                matches(
                        isDisplayed()
                )
        );
        onView(withContentDescription("Navigate up")).perform(click());
    }
    public void testInexistentOption() {

        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.place_search)).perform(typeText("sfslk"));
        onView(withId(R.id.search_place_button)).perform(click());

        onView(
                withId(R.id.map)
        ).check(
                matches(
                        isDisplayed()
                )
        );
    }
    public void testMarker() {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(0)
                .perform(click());

        onView(withId(R.id.map)).perform(click());
    }

}
