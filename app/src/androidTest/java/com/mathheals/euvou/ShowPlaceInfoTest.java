package com.mathheals.euvou;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;

import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.show_place.ShowPlaceInfo;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasToString;

/**
 * Created by marlonmendes on 12/11/15.
 */
public class ShowPlaceInfoTest extends ActivityInstrumentationTestCase2<HomePage>{
    public ShowPlaceInfoTest() {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testShowPlaceStarting() {
        clickOnTodosPlaceCategory();
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        UiObject marker = device.findObject(new UiSelector().descriptionContains("Parque Ecológico do Tororó"));
        try {
            marker.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.address)).check(matches(isDisplayed())
        );
    }

    private void clickOnTodosPlaceCategory() {
        onView(withContentDescription("Navigate up")).perform(click());
        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(0)
                .perform(click());
    }
}
