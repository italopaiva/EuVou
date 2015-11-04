package com.mathheals.euvou;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;

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
 * Created by izabela on 04/11/15.
 */
public class RemoveUserControlTest extends ActivityInstrumentationTestCase2<HomePage> {

    public RemoveUserControlTest() {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testIfConfigureOptionIsDisplayedForUserLoggedOut() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Sair")).perform(click());
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Configurações")).check((doesNotExist()));
    }

    public void testIfConfigureOptionIsDisplayedForUserLoggedIn() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Entrar")).perform(click());
        onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
        onView(withId(R.id.passwordField)).perform(typeText("123456"));
        onView(withText("Login")).perform(click());
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Configurações")).check(matches(isDisplayed()));
    }

    public void testRemoveButton() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Entrar")).perform(click());
        onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
        onView(withId(R.id.passwordField)).perform(typeText("123456"));
        onView(withText("Login")).perform(click());
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Configurações")).perform(click());
        onView(withText("Desativar conta")).check(matches(isDisplayed()));
        onView(withText("DESATIVAR")).check(matches(isDisplayed()));
        onView(withText("DESATIVAR")).perform(click());
        onView(withText("Sim")).check(matches(isDisplayed()));
        onView(withText("Não")).check(matches(isDisplayed()));
    }

}
