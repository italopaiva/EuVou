package com.mathheals.euvou;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by izabela on 03/11/15.
 */
public class UserRegistrationControlTest extends ActivityInstrumentationTestCase2<HomePage> {

    public UserRegistrationControlTest() {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
        LoginUtility utility = new LoginUtility(getActivity());
        utility.setUserLogOff();
    }

    public void testRegisterOptionInActionBarMenu() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.saveButton)).check(matches(isDisplayed()));
        onView(withId(R.id.saveButton)).check(matches(withText("Cadastrar")));
    }
    
    public void testIfNameLabelIsCorrect() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.userNameLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.userNameLabel)).check(matches(withText("Nome")));
    }

    public void testNameField(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.nameField)).check(matches(isDisplayed()));
        onView(withId(R.id.nameField)).perform(typeText("Ju Almeida"));
        onView(withId(R.id.dateField)).perform(click());
        onView(withId(R.id.nameField)).check(matches(withText("Ju Almeida")));
    }

    public void testIfDateLabelIsCorrect() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.birthDateLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.birthDateLabel)).check(matches(withText("Data de Nascimento")));
    }

    public void testDateField(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.dateField)).check(matches(isDisplayed()));
        onView(withId(R.id.dateField)).perform(typeText("22/12/2050"));
        onView(withId(R.id.mailField)).perform(click());
        onView(withId(R.id.dateField)).check(matches(withText("22/12/2050")));
    }

    public void testIfMailLabelIsCorrect() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.userMailLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.userMailLabel)).check(matches(withText("E-mail")));
    }

    public void testMailField(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.mailField)).check(matches(isDisplayed()));
        onView(withId(R.id.mailField)).perform(typeText("oi-xau@voltei.com"));
        onView(withId(R.id.confirmMailField)).perform(click());
        onView(withId(R.id.mailField)).check(matches(withText("oi-xau@voltei.com")));
    }

    public void testIfMailConfirmationLabelIsCorrect() {
        LoginUtility utility = new LoginUtility(getActivity());
        utility.setUserLogOff();
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.confirmUserMailLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.confirmUserMailLabel)).check(matches(withText("Confirme o e-mail")));
    }

    public void testMailConfirmationField(){
        LoginUtility utility = new LoginUtility(getActivity());
        utility.setUserLogOff();
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.confirmMailField)).check(matches(isDisplayed()));
        onView(withId(R.id.confirmMailField)).perform(typeText("oi-xau@voltei.com"));
        onView(withId(R.id.loginField)).perform(click());
        onView(withId(R.id.confirmMailField)).check(matches(withText("oi-xau@voltei.com")));
    }

    public void testIfLoginLabelIsCorrect() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.userLoginLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.userLoginLabel)).check(matches(withText("Login")));
    }

    public void testLoginField(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.loginField)).check(matches(isDisplayed()));
        onView(withId(R.id.loginField)).perform(typeText("oioioi123"));
        onView(withId(R.id.passwordField)).perform(click());
        onView(withId(R.id.loginField)).check(matches(withText("oioioi123")));
    }

    public void testIfPasswordLabelIsCorrect() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.userPasswordLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.userPasswordLabel)).check(matches(withText("Senha")));
    }

    public void testPasswordField(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.passwordField)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordField)).perform(typeText("1234567"));
        onView(withId(R.id.confirmMailPassword)).perform(click());
        onView(withId(R.id.passwordField)).check(matches(withText("1234567")));
    }

    public void testIfConfirmPasswordLabelIsCorrect() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.userConfirmPasswordLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.userConfirmPasswordLabel)).check(matches(withText("Confirme a senha")));
    }

    public void testConfirmPasswordField(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar")).perform(click());
        onView(withId(R.id.confirmMailPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.confirmMailPassword)).perform(typeText("1234567"));
        onView(withId(R.id.passwordField)).perform(click());
        onView(withId(R.id.confirmMailPassword)).check(matches(withText("1234567")));
    }

}
