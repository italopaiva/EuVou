package com.mathheals.euvou;

import junit.framework.TestCase;

import model.Place;

/**
 * Created by viny on 01/10/15.
 */
public class PlaceTest extends TestCase {

    public void testEmptyName()
    {
        boolean ok = false;
        try {
            Place place= new Place(null,"2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");
            ok = true;
        }catch (Exception e)
        {
            ok = false;
        }
        finally {
            assertFalse(ok);
        }
    }
    public void testEmptyLatitude()
    {
        boolean ok = false;
        try {
            Place place= new Place("Pizza","2",null,"14.0025","8h às 12h","Descrição","rua das flores");
            ok = true;
        }catch (Exception e)
        {
            ok = false;
        }
        finally {
            assertFalse(ok);
        }
    }
    public void testEmptyLongitude()
    {
        boolean ok = false;
        try {
            Place place= new Place("Pizza","2","14.0025",null,"8h às 12h","Descrição","rua das flores");
            ok = true;
        }catch (Exception e)
        {
            ok = false;
        }
        finally {
            assertFalse(ok);
        }
    }

    public void testConvertLatitude()
    {
        boolean ok = false;
        try {
            Place place= new Place("Pizza","2","","14.0025","8h às 12h","Descrição","rua das flores");
            ok = true;
        }catch (Exception e)
        {
            ok = false;
        }
        finally {
            assertFalse(ok);
        }
    }
    public void testConvertLongitude()
    {
        boolean ok = false;
        try {
            Place place= new Place("Pizza","2","14.0025",null,"8h às 12h","Descrição","rua das flores");
            ok = true;
        }catch (Exception e)
        {
            ok = false;
        }
        finally {
            assertFalse(ok);
        }
    }
}
