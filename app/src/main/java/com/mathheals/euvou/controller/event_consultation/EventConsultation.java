package com.mathheals.euvou.controller.event_consultation;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.show_event.ShowEvent;

public class EventConsultation extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        View.OnClickListener {

    private RadioGroup radioGroup;
    private ActionBar actionBar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_consultation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_consultation, menu);
        actionBar = getSupportActionBar();

        setSearchBar(menu);
        configActionBar();

        radioGroup = (RadioGroup) findViewById(R.id.search_radio_group);
        radioGroup.setOnCheckedChangeListener(this);
        return true;
    }

    private void setSearchBar(Menu menu) {
        final String SEARCH_VIEW_HINT = "Pesquisar";

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(SEARCH_VIEW_HINT);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                int checkedButton = radioGroup.getCheckedRadioButtonId();
                switch (checkedButton) {
                    case R.id.radio_events:
                        Toast.makeText(getBaseContext(), "EVENTOS: " + query, Toast.LENGTH_LONG).show();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putString("eventName", query);
                        ShowEvent event = new ShowEvent();
                        event.setArguments(bundle);
                        EventDao eventDao = new EventDao();
                        if(eventDao.searchEventByName(query)!= null)
                        {
                            fragmentTransaction.replace(R.id.content_frame, event);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }else{
                            Toast.makeText(getBaseContext(), "O evento não foi encontrado", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.radio_places:
                        Toast.makeText(getBaseContext(), "LOCAIS: " + query, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radio_people:
                        Toast.makeText(getBaseContext(), "PESSOAS: " + query, Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    private void configActionBar() {
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00C0C3")));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, HomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        String query = searchView.getQuery().toString();
        switch(checkedId) {
            case R.id.radio_events:
                Toast.makeText(getBaseContext(), "AGORA EM EVENTOS: " + query, Toast.LENGTH_LONG).show();
                break;
            case R.id.radio_places:
                Toast.makeText(getBaseContext(), "AGORA EM LOCAIS: " + query, Toast.LENGTH_LONG).show();
                break;
            case R.id.radio_people:
                Toast.makeText(getBaseContext(), "AGORA EM PESSOAS: " + query, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        radioGroup.clearCheck();
    }
}
