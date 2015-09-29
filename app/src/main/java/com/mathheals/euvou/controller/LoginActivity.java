package com.mathheals.euvou.controller;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import com.mathheals.euvou.R;

/**
 * Created by izabela on 27/09/15.
 */
public class LoginActivity extends ActionBarActivity implements View.OnClickListener{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        Button signUp = (Button) findViewById(R.id.signUpButton);
        signUp.setOnClickListener(this);
    }

    public void onClick(View v) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new RegisterFragment());
        fragmentTransaction.commit();
    }
}