package com.mathheals.euvou.controller.show_place;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mathheals.euvou.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowPlaceInfo extends Fragment {


    public ShowPlaceInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_place_info, container, false);
    }


}
