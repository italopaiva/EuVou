package com.mathheals.euvou.controller.utility;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by marlonmendes on 14/10/15.
 */
public class ActivityUtility {

    public static void restartActivity(Activity activity) {
        Intent intent = activity.getIntent();
        activity.finish();
        activity.startActivity(intent);
    }
}
