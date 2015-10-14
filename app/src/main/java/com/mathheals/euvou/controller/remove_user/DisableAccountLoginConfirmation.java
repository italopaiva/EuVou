package com.mathheals.euvou.controller.remove_user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mathheals.euvou.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisableAccountLoginConfirmation extends android.support.v4.app.Fragment implements View.OnClickListener {


    public DisableAccountLoginConfirmation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_disable_account_login_confirmation, container, false);
        Button backButton = (Button)view.findViewById(R.id.button_back_id);
        backButton.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {

        FragmentActivity activity = this.getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        switch (view.getId()) {
            case R.id.button_back_id:
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                RemoveUserVIewMessages.showWelcomeBackMessage(activity.getBaseContext());
                return;
        }
    }
}
