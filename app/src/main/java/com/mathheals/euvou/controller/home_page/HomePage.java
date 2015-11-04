package com.mathheals.euvou.controller.home_page;

import android.content.Context;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.event_registration.RegisterEventFragment;
import com.mathheals.euvou.controller.login_user.LoginValidation;
import com.mathheals.euvou.controller.remove_user.DisableAccountFragment;
import com.mathheals.euvou.controller.remove_user.DisableAccountLoginConfirmation;
import com.mathheals.euvou.controller.remove_user.OhGoshFragment;
import com.mathheals.euvou.controller.search_event.ListEvents;
import com.mathheals.euvou.controller.user_profile.ShowUser;
import com.mathheals.euvou.controller.edit_user.EditUserFragment;
import com.mathheals.euvou.controller.login_user.LoginActivity;
import com.mathheals.euvou.controller.remove_user.RemoveUserFragment;
import com.mathheals.euvou.controller.remove_user.RemoveUserVIewMessages;
import com.mathheals.euvou.controller.search_place.SearchPlaceMaps;
import com.mathheals.euvou.controller.user_registration.RegisterFragment;
import com.mathheals.euvou.controller.utility.ActivityUtility;
import com.mathheals.euvou.controller.utility.LoginUtility;

import dao.UserDAO;

public class HomePage extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private static final String QUERY = "query";
    private static final String SETTINGS_FRAGMENT = "settings_fragment_tag";
    private CharSequence mTitle;
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private String[] textOptions;
    private ActionBar actionBar;
    private Fragment currentFragment;
    public static final String OPTION = "option";
    private int USER_STATUS;
    private final int LOGGED_OUT = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_navigation_drawer);
        initViews();
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, textOptions));
        EditText placeSearch = (EditText) findViewById(R.id.place_search);

        callGoogleMaps();
        onConfigActionBar();
    }

    public void searchPlace(View view){
        final String INVALID_SEARCH = "Pesquisa Invalida";

        String filter = ((EditText)findViewById(R.id.place_search)).getText().toString();
        Intent map = new Intent(HomePage.this, SearchPlaceMaps.class);
        if(filter.isEmpty()) {
            Toast.makeText(this, INVALID_SEARCH, Toast.LENGTH_LONG).show();
        }
        else{
            map.putExtra(QUERY, filter);
            HomePage.this.startActivity(map);
            drawerLayout.closeDrawer(linearLayout);
        }
    }

    private void callGoogleMaps()
    {
        drawerList.setOnItemClickListener(this);
    }

    private void initViews(){
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer_list);
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
    }

    private void onConfigActionBar(){

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00C0C3")));

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
        
        MenuItem searchBar = menu.findItem(R.id.search);
        SearchView userSearch = (SearchView) searchBar.getActionView();
        userSearch.setQueryHint("Busque usuários");
        userSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("username", query);
                ShowUser user = new ShowUser();
                user.setArguments(bundle);
                UserDAO userDAO = new UserDAO(getParent());
                if(userDAO.searchUserByUsername(query)!=null) {
                    fragmentTransaction.replace(R.id.content_frame, user);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else{
                    Toast.makeText(getBaseContext(), "O nome não foi encontrado", Toast.LENGTH_LONG).show();
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
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
                ActivityUtility.clearBackStack(this);
                fragmentTransaction.replace(R.id.content_frame, new RemoveUserFragment(), SETTINGS_FRAGMENT);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.register_event:
                fragmentTransaction.replace(R.id.content_frame, new RegisterEventFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.logout:
                new LoginUtility(HomePage.this).setUserLogOff();

                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            case R.id.myEvents:
                fragmentTransaction.replace(R.id.content_frame, new ListEvents());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                //Toast.makeText(getBaseContext(), "Cadastrar evento", Toast.LENGTH_LONG).show();
            default:
                return false;
        }
    }

    public boolean userLoggedOutOptions(MenuItem item) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.registration:
                fragmentTransaction.replace(R.id.content_frame, new RegisterFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.log_in:
                Intent myIntent = new Intent(HomePage.this, LoginActivity.class);
                HomePage.this.startActivity(myIntent);
                return true;
            default:
                return false;
        }
    }

    // Alterar Usuário methods
    public void editUserUpdateButtonOnClick(View view) {
        final String SUCESS_EDIT_MESSAGE = "Dados alterados com sucesso :)";
        Toast.makeText(getBaseContext(), SUCESS_EDIT_MESSAGE, Toast.LENGTH_LONG).show();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        return;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String aux = "";
        switch (position) {
            case 1:
                aux = "Museu";
                break;
            case 2:
                aux = "Parque";
                break;
            case 3:
                aux = "Teatro";
                break;
            case 4:
                aux = "shop";
                break;
            case 5:
                aux = "Unidade";
                break;

        }
        Intent map = new Intent(HomePage.this, SearchPlaceMaps.class);
        map.putExtra(QUERY, aux);
        HomePage.this.startActivity(map);
        drawerLayout.closeDrawer(linearLayout);
    }
}
