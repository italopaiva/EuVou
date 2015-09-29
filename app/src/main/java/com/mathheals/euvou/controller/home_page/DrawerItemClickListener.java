package com.mathheals.euvou.controller.home_page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by julliana on 20/09/15.
 */
public class DrawerItemClickListener implements AdapterView.OnItemClickListener {

    private Fragment currentFragment;
    public static final String OPTION = "option";
    private ActionBarActivity activity;
    private DrawerLayout drawerLayout;
    private ListView drawerList;

    public DrawerItemClickListener(DrawerLayout drawerLayout, ListView drawerList, ActionBarActivity activity){

        this.drawerLayout = drawerLayout;
        this.drawerList = drawerList;
        this.activity = activity;

    }
    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        selectItem(position);

    }

    private void selectItem(int position){

        currentFragment = new OptionFragment();
        //Seta como argumento a posição do item do menu escolhido
        Bundle args = new Bundle();
        args.putInt(OPTION, position);
        currentFragment.setArguments(args);
        ((HomePage)activity).replaceFragment(currentFragment);
        drawerLayout.closeDrawer(drawerList); //Fecha o menu

    }
}