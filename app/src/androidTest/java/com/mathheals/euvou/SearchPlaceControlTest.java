package com.mathheals.euvou;

import android.test.ActivityInstrumentationTestCase2;
import com.mathheals.euvou.controller.home_page.HomePage;
import org.junit.Before;

/**
 * Created by izabela on 04/11/15.
 */
public class SearchPlaceControlTest extends ActivityInstrumentationTestCase2<HomePage> {

    public SearchPlaceControlTest() {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }


}
