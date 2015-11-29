package com.mathheals.euvou;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;

import org.junit.Before;
import org.junit.Test;

import android.widget.ListView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by marlonmendes on 28/11/15.
 */
public class EventRecomendationTest  extends ActivityInstrumentationTestCase2<HomePage> {

    public final String TOP_ONE_EVENT_NAME = "Festa";
    private Activity activity;
    private ListView list;

    public EventRecomendationTest() {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        list = (ListView) activity.findViewById(R.id.list_view_event_recomendations);
    }

    @Test
    public void testIfAnyEventIsRecommended() {
        assertNotNull("The list was not loaded", list);
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                int position = 0;
                list.performItemClick(list.getAdapter().getView(position, null, null),
                        position, list.getAdapter().getItemId(position));
            }

        });
        getInstrumentation().waitForIdleSync();
        onView(withId(R.id.event_name_text  )).check(matches(isDisplayed()));
    }
}
