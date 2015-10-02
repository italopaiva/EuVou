package com.mathheals.euvou.controller.home_page;

import android.content.Context;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.login_user.LoginValidation;
import com.mathheals.euvou.controller.remove_user.DisableAccountFragment;
import com.mathheals.euvou.controller.remove_user.DisableAccountLoginConfirmation;
import com.mathheals.euvou.controller.remove_user.OhGoshFragment;
import com.mathheals.euvou.controller.edit_user.EditUserFragment;
import com.mathheals.euvou.controller.login_user.LoginActivity;
import com.mathheals.euvou.controller.remove_user.RemoveUserFragment;
import com.mathheals.euvou.controller.remove_user.RemoveUserVIewMessages;
import com.mathheals.euvou.controller.user_registration.RegisterFragment;
import com.mathheals.euvou.controller.utility.LoginUtility;

import dao.UserDAO;

public class HomePage extends ActionBarActivity {
    private static final String DISABLE_ACCOUNT_FRAGMENT_TAG = "disable_account_fragment_tag";
    private static final String SETTINGS_FRAGMENT = "settings_fragment_tag";
    private CharSequence mTitle;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private String[] textOptions;
    private ActionBar actionBar;
    private DrawerItemClickListener listener;
    private Fragment currentFragment;
    public static final String OPTION = "option";
    private int USER_STATUS;
    private final int LOGGED_OUT = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_navigation_drawer);
        initViews();
        onConfigListener();
        onConfigListItem();
        onConfigActionBar();

        if (currentFragment == null) {
            replaceFirstFrag();
        }
    }

    private void initViews(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer,
                        R.string.drawer_open, R.string.drawer_close) {
                    public void onDrawerClosed(View view) {

                        supportInvalidateOptionsMenu();
                    }

                    public void onDrawerOpened(View drawerView) {

                        supportInvalidateOptionsMenu();
                    }
                };

        textOptions = getResources().getStringArray(R.array.itens_menu_string);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        actionBar = getSupportActionBar();

        listener = new DrawerItemClickListener(drawerLayout, drawerList,this);
    }

    private void onConfigActionBar(){

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#008B8B")));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        LoginUtility loginUtility = new LoginUtility(HomePage.this);
        // Inflating menu for logged users

        USER_STATUS = loginUtility.getUserId();

        if(USER_STATUS != LOGGED_OUT) {
            inflater.inflate(R.menu.home_page_logged_in, menu);
        }
        // Inflating menu for not logged users
        else if(USER_STATUS == LOGGED_OUT) {
            inflater.inflate(R.menu.home_page_logged_out, menu);
        }
        else {
            return false;
        }

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setQueryHint("Buscar locais e usuário");
        return true;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        if(USER_STATUS != LOGGED_OUT) {
            userLoggedInOptions(item);
        }
        else if(USER_STATUS == LOGGED_OUT) {
            userLoggedOutOptions(item);
        }
        else {
            return false;
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean userLoggedInOptions(MenuItem item) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch(item.getItemId()) {
            case R.id.edit_register:
                // Put here code for "Alterar Cadastro"
                fragmentTransaction.replace(R.id.content_frame, new EditUserFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.settings:
                clearBackStack();
                fragmentTransaction.replace(R.id.content_frame, new RemoveUserFragment(), SETTINGS_FRAGMENT);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.visualize_profile:
                return true;
            case R.id.logout:
                new LoginUtility(HomePage.this).setUserLogOff();

                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            default:
                return false;
            //Put here code for "Visualizar Usuario"
        }
    }

    public boolean userLoggedOutOptions(MenuItem item) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.registration:
                fragmentTransaction.replace(R.id.content_frame, new RegisterFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Toast.makeText(getBaseContext(), "Cadastrar", Toast.LENGTH_LONG).show();
                return true;
            case R.id.log_in:
                Intent myIntent = new Intent(HomePage.this, LoginActivity.class);
                HomePage.this.startActivity(myIntent);
                return true;
            default:
                return false;
        }
    }
    private void onConfigListener(){

        drawerList.setOnItemClickListener(listener);

    }

    private void onConfigListItem(){

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, textOptions));

    }

    public final void replaceFragment(final Fragment frag){

        currentFragment = frag;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, frag).commit();

    }
    /**
     * Substistui o conteúdo para o primeiro fragment
     */
    private void replaceFirstFrag() {

        currentFragment = new OptionFragment();
        Bundle args = new Bundle();
        args.putInt(OPTION, 0);
        currentFragment.setArguments(args);
        replaceFragment(currentFragment);

    }

    public void configurationButtonsOnClick(View view) {
        // Handling all configuration's buttons onClick

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        Context homePageContext = getBaseContext();

        switch(view.getId()) {
            case R.id.button_disable_account_id:
                fragmentTransaction.replace(R.id.content_frame, new OhGoshFragment());
                fragmentTransaction.add(R.id.content_frame, new DisableAccountFragment(), DISABLE_ACCOUNT_FRAGMENT_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.addToBackStack(DISABLE_ACCOUNT_FRAGMENT_TAG);
                fragmentTransaction.commit();
                return;
            case R.id.button_yes_id:
                fragmentManager.popBackStack();
                RemoveUserVIewMessages.showWelcomeBackMessage(homePageContext);
                return;
            case R.id.button_no_id:
                android.support.v4.app.Fragment disableAccountFragment = getSupportFragmentManager().findFragmentByTag(DISABLE_ACCOUNT_FRAGMENT_TAG);
                fragmentTransaction.remove(disableAccountFragment);
                fragmentTransaction.add(R.id.content_frame, new DisableAccountLoginConfirmation());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return;
            case R.id.button_back_id:
                fragmentManager.popBackStack(DISABLE_ACCOUNT_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                RemoveUserVIewMessages.showWelcomeBackMessage(homePageContext);
                return;
            case R.id.button_disable_account_confirmation_id:
                if(isLoginConfirmationValid()) {
                    clearBackStack();
                    RemoveUserVIewMessages.showAccountDeactivateMessage(homePageContext);
                    new UserDAO().disableUser(new LoginUtility(this).getUserId());
                }
                return;
        }
    }
    private void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public boolean isLoginConfirmationValid() {
        EditText usernameField = (EditText) findViewById(R.id.edit_text_login_id);
        String typedUsername = usernameField.getText().toString();

        EditText passwordField = (EditText) findViewById(R.id.edit_text_password_id);
        String typedPassword = passwordField.getText().toString();

        LoginValidation loginValidation = new LoginValidation(HomePage.this);

        boolean isUsernameValid = loginValidation.isUsernameValid(typedUsername);

        if(isUsernameValid==false){
            usernameField.requestFocus();
            usernameField.setError(loginValidation.getInvalidUsernameMessage());
        }else{
            boolean isPasswordValid=loginValidation.checkPassword(typedUsername, typedPassword);

            if(isPasswordValid==false){
                passwordField.requestFocus();
                passwordField.setError(loginValidation.getInvalidPasswordMessage());
            }
            else
                return true;
        }
        return false;
    }

    // Alterar Usuário methods
    public void editUserUpdateButtonOnClick(View view) {
        final String SUCESS_EDIT_MESSAGE = "Dados alterados com sucesso :)";
        Toast.makeText(getBaseContext(), SUCESS_EDIT_MESSAGE, Toast.LENGTH_LONG).show();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        return;
    }
}
