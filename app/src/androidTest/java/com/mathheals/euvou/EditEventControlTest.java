package com.mathheals.euvou;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.junit.Before;

import java.text.ParseException;
import java.util.Vector;

import dao.EventDAO;
import exception.EventException;
import model.Event;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;

/**
 * Created by julliana on 11/11/15.
 */
public class EditEventControlTest extends ActivityInstrumentationTestCase2<HomePage> {
    LoginUtility isLoged;
    EventDAO eventDAO = new EventDAO();

    public EditEventControlTest() {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
        isLoged = new LoginUtility(getActivity());
    }

    public void testIfEditUserOptionIsDisplayedForUserLoggedOut() {
        if (isLoged.hasUserLoggedIn()) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Sair")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar")).check((doesNotExist()));
    }

    public void testIfEditUserOptionIsDisplayedForUserLoggedIn() throws ParseException, EventException {
        if (!isLoged.hasUserLoggedIn()) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Meus Eventos")).perform(click());

        onData(hasToString(containsString("Teste")))
                .inAdapterView(withId(R.id.eventList)).atPosition(0)
                .perform(click());
        onView(withId(R.id.editRemoveButton)).perform(click());
        onView(withId(R.id.eventName)).perform(typeText("meuTeste"));
        onView(withText("Alterar")).perform(scrollTo());
        onView(withText("Alterar")).perform(click());

        Vector<String> category = new Vector<String>();
        category.add("Show");
        Event event = new Event("Teste","00/00/0000",10, "oi","xablau","0","0",category);
        eventDAO.updateEvent(event);
    }
}
