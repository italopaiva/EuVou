
package com.mathheals.euvou.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.mathheals.euvou.R;

public class Search_a_Place extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_a__place);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_a__place, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v)
    {
        EditText placeNameField = (EditText) findViewById(R.id.placeName);
        String placeName = placeNameField.getText().toString().trim();
        validadeNotEmptyfield(placeNameField);

        

    }
//validating search place name field not null
    @SuppressLint("NewApi")
    public boolean validadeNotEmptyfield(EditText editText)
    {
        // false = the field is not empty; true = the field is empty;
        EditText placeNameField = (EditText) findViewById(R.id.placeName);
        String placeName = placeNameField.getText().toString().trim();

        if(placeName.isEmpty())
        {
           return true;
        }else
        {
            return false;
        }
    }
}
