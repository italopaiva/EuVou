package com.mathheals.euvou;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dao.UserDAO;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button register = (Button)findViewById(R.id.register_button);

        //register listeners
        register.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onClick(View v) {

        username = (EditText) findViewById(R.id.user_name);

        String userName = username.getText().toString();

        UserDAO userDao = new UserDAO(MainActivity.this);

        String result = userDao.save(userName);

        //Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
    }
 }
