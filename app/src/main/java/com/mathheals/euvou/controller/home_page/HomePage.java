package com.mathheals.euvou.controller.home_page;

import android.app.FragmentTransaction;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.remove_user.DisableAccountFragment;
import com.mathheals.euvou.controller.remove_user.OhGoshFragment;
import com.mathheals.euvou.controller.remove_user.RemoveUserFragment;

public class HomePage extends ActionBarActivity {
    private CharSequence mTitle;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private String[] textOptions;
    private ActionBar actionBar;
    private DrawerItemClickListener listener;
    private Fragment currentFragment;
    public static final String OPTION = "option";

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
        inflater.inflate(R.menu.home_page, menu);

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

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // Handle your other action bar items...
        switch(item.getItemId()) {
            case R.id.edit_register:
                // Put here code for "Alterar Cadastro"
                return true;
            case R.id.settings:
                fragmentTransaction.replace(R.id.content_frame, new RemoveUserFragment());
                fragmentTransaction.commit();
                return true;
            case R.id.visualize_profile:
                //Put here code for "Visualizar Usuario"
        }

        return super.onOptionsItemSelected(item);
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
        switch(view.getId()) {
            case R.id.button_disable_account_id:
                fragmentTransaction.replace(R.id.content_frame, new OhGoshFragment());
                fragmentTransaction.add(R.id.content_frame, new DisableAccountFragment());
                fragmentTransaction.commit();
                return;
            case R.id.button_yes_id:
                Toast.makeText(getBaseContext(), "YES BUTTON", Toast.LENGTH_LONG).show();
                return;
            case R.id.button_no_id:
                Toast.makeText(getBaseContext(), "NO BUTTON", Toast.LENGTH_LONG).show();
                return;
        }
    }
}
