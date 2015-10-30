package com.mathheals.euvou;

import android.app.Activity;

import junit.framework.TestCase;

import dao.PlaceDAO;

/**
 * Created by viny on 20/10/15.
 */
public class PlaceDAOTest extends TestCase {

    private Activity activity;

    public PlaceDAOTest()
    {
        activity = new Activity();
    }

    public void testSearchPlaceByPartName() throws Exception {
        PlaceDAO place = new PlaceDAO(activity);
        if(place.searchPlaceByPartName("") != null)
            assertTrue(true);
        else
            fail();
    }
}
