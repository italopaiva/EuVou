package com.mathheals.euvou;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by izabela on 06/11/15.
 */
public class EventRegistrationControlTest extends ActivityInstrumentationTestCase2<HomePage> {

    LoginUtility isLoged;


    public EventRegistrationControlTest() {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
        isLoged = new LoginUtility(getActivity());
    }

    public void testIfRegisterEventOptionIsDisplayedForUserLoggedOut() {
        if(isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Sair")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar evento")).check((doesNotExist()));
    }

    public void testIfRegisterEventOptionIsDisplayedForUserLoggedIn() {
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar Evento")).check(matches(isDisplayed()));
    }

    public void testIfNameLabelIsCorrect() {
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar Evento")).perform(click());
        onView(withId(R.id.eventNameLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.eventNameLabel)).check(matches(withText("Nome do Evento")));
    }

    public void testIfNameFieldIsCorrect() {
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar Evento")).perform(click());
        onView(withId(R.id.eventName)).check(matches(isDisplayed()));
        onView(withId(R.id.eventName)).perform(typeText("Chocolate da Sprint 3"));
        onView(withId(R.id.eventDate)).perform(click());
        onView(withId(R.id.eventName)).check(matches(withText("Chocolate da Sprint 3")));
    }

    public void testIfDateLabelIsCorrect() {
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar Evento")).perform(click());
        onView(withId(R.id.eventDateLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.eventDateLabel)).check(matches(withText("Data do Evento")));
    }

    public void testIfDateFieldIsCorrect() {
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar Evento")).perform(click());
        onView(withId(R.id.eventDate)).check(matches(isDisplayed()));
        onView(withId(R.id.eventDate)).perform(typeText("11/11/2020"));
        onView(withId(R.id.eventPrice)).perform(click());
        onView(withId(R.id.eventDate)).check(matches(withText("11/11/2020")));
    }

    public void testIfDescpriptionLabelIsCorrect() {
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar Evento")).perform(click());
        onView(withId(R.id.descriptionLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.descriptionLabel)).check(matches(withText("Descrição do Evento")));
    }

    public void testIfDescpriptionFieldIsCorrect() {
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar Evento")).perform(click());
        onView(withId(R.id.eventDescription)).check(matches(isDisplayed()));
        onView(withId(R.id.eventDescription)).perform(typeText("11/11/2020"));
        onView(withId(R.id.eventPrice)).perform(click());
        onView(withId(R.id.eventDescription)).check(matches(withText("11/11/2020")));
    }


}
