package com.mathheals.euvou;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.mathheals.euvou.controller.edit_user.EditUserFragment;
import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.junit.Before;

import java.text.ParseException;

import exception.UserException;
import model.User;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by izabela on 04/11/15.
 */
public class EditUserControlTest extends ActivityInstrumentationTestCase2<HomePage> {

    LoginUtility isLoged;
    EditUserFragment editUserFragment = new EditUserFragment();

    public EditUserControlTest() {
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
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar cadastro")).check((doesNotExist()));
    }

    public void testIfEditUserOptionIsDisplayedForUserLoggedIn() {
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar cadastro")).check(matches(isDisplayed()));
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
        onView(withText("Alterar cadastro")).perform(click());
        onView(withId(R.id.nameLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.nameLabel)).check(matches(withText("Nome")));
    }

    public void testNameField(){
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar cadastro")).perform(click());
        onView(withId(R.id.nameField)).check(matches(isDisplayed()));
        onView(withId(R.id.nameField)).check(matches(withText("Igor Duarte")));
        onView(withId(R.id.nameField)).perform(typeText(" Ribeiro"));
        onView(withId(R.id.dateField)).perform(click());
        onView(withId(R.id.nameField)).check(matches(withText("Igor Duarte Ribeiro")));
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
        onView(withText("Alterar cadastro")).perform(click());
        onView(withId(R.id.birthDateLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.birthDateLabel)).check(matches(withText("Data de Nascimento")));
    }

    public void testDateField(){
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar cadastro")).perform(click());
        onView(withId(R.id.dateField)).check(matches(isDisplayed()));
        onView(withId(R.id.dateField)).check(matches(withText("14/11/1995")));
    }

    public void testIfMailLabelIsCorrect() {
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar cadastro")).perform(click());
        onView(withId(R.id.userMailLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.userMailLabel)).check(matches(withText("E-mail")));
    }

    public void testMailField(){
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar cadastro")).perform(click());
        onView(withId(R.id.mailField)).check(matches(isDisplayed()));
        onView(withId(R.id.mailField)).check(matches(withText("igor-ribeiro@hotmail.com")));

    }

    public void testIfMailConfirmationLabelIsCorrect() {
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar cadastro")).perform(click());
        onView(withId(R.id.confirmUserMailLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.confirmUserMailLabel)).check(matches(withText("Confirme o e-mail")));
    }

    public void testMailConfirmationField(){
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar cadastro")).perform(click());
        onView(withId(R.id.confirmMailField)).check(matches(isDisplayed()));
        onView(withId(R.id.confirmMailField)).perform(typeText("oi-xau@voltei.com"));
        onView(withId(R.id.passwordField)).perform(click());
        onView(withId(R.id.confirmMailField)).check(matches(withText("oi-xau@voltei.com")));
    }

    public void testIfPasswordLabelIsCorrect() {
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar cadastro")).perform(click());
        onView(withId(R.id.userPasswordLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.userPasswordLabel)).check(matches(withText("Senha")));
    }

    public void testPasswordField(){
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar cadastro")).perform(click());
        onView(withId(R.id.passwordField)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordField)).perform(typeText("123456"));
        onView(withId(R.id.confirmPasswordField)).perform(click());
        onView(withId(R.id.passwordField)).check(matches(withText("123456")));
    }

    public void testIfConfirmPasswordLabelIsCorrect() {
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar cadastro")).perform(click());
        onView(withId(R.id.userConfirmPasswordLabel)).check(matches(isDisplayed()));
        onView(withId(R.id.userConfirmPasswordLabel)).check(matches(withText("Confirme a senha")));
    }

    public void testConfirmPasswordField(){
        if(!isLoged.hasUserLoggedIn()){
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar cadastro")).perform(click());
        onView(withId(R.id.confirmPasswordField)).check(matches(isDisplayed()));
        onView(withId(R.id.confirmPasswordField)).perform(typeText("123456"));
        onView(withId(R.id.passwordField)).perform(click());
        onView(withId(R.id.confirmPasswordField)).check(matches(withText("123456")));
    }

    public void testSaveUserMethod() throws ParseException, UserException {
        User user = new User(10,"Iza","09/06/1997","iza@oi.com.br");
        editUserFragment.updateUser(user);
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.search_src_text)).perform(typeText("izacristest"), pressKey(66));
        onView(withId(R.id.labelName)).check(matches(withText("Iza")));
        user = new User(10,"Iza Cristina","09/06/1997", "iza@oi.com.br");
        editUserFragment.updateUser(user);

    }

}
