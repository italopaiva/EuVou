package com.mathheals.euvou.controller.showPlaceRanking;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.event_registration.RegisterEventFragment;

public class ShowTop5Rank extends android.support.v4.app.Fragment implements OnClickListener {

    public ShowTop5Rank() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_show_top5_rank, container, false);
        Button bt = (Button) vw.findViewById(R.id.more);
        bt.setOnClickListener(this);
        return vw;
    }

    @Override
    public void onClick(View v) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();;
        fragmentTransaction.replace(R.id.content_frame, new ShowPlaceRanking());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}