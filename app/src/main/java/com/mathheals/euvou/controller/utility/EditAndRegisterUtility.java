package com.mathheals.euvou.controller.utility;

import android.support.v4.app.Fragment;
import android.widget.EditText;

/**
 * Created by izabela on 28/11/15.
 */
public class EditAndRegisterUtility {

    public EditAndRegisterUtility(){

    }

    public String getTextTyped(int fieldId, Fragment fragment){
        EditText field = getEditTextById(fieldId, fragment);
        String typed = field.getText().toString();

        return typed;
    }

    public EditText getEditTextById(int fieldId, Fragment fragment){
        return (EditText) fragment.getActivity().findViewById(fieldId);
    }


}
