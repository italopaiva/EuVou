package com.mathheals.euvou;

import junit.framework.TestCase;

import java.text.ParseException;

import exception.PlaceException;
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
    public void testValidName()
    {
        boolean ok = false;
        try {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");
            ok = true;
        }catch (Exception e)
        {
            ok = false;
        }
        finally {
            assertTrue(ok);
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
    public void testValidLatitude()
    {
        boolean ok = false;
        try {
            Place place = new Place("Pizza","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");
            ok = true;
        }catch (Exception e)
        {
            ok = false;
        }
        finally {
            assertTrue(ok);
        }
    }
    public void testValidLongitude()
    {
        boolean ok = false;
        try {
            Place place= new Place("Pizza","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");
            ok = true;
        }catch (Exception e)
        {
            ok = false;
        }
        finally {
            assertTrue(ok);
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
            Place place= new Place("Pizza","2","14.0025","","8h às 12h","Descrição","rua das flores");
            ok = true;
        }catch (Exception e)
        {
            ok = false;
        }
        finally {
            assertFalse(ok);
        }
    }

    public void testGetName()
    {
        try {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");

            assertTrue(place.getName().equals("Nome"));
        } catch (PlaceException e) {
            assertTrue(false);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    public void testGetLatitude()
    {
        try {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");

            assertTrue(place.getLatitude() == 14.0025);
        } catch (PlaceException e) {
            assertTrue(false);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }


    public void testGetLongitude()
    {
        try {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");

            assertTrue(place.getLongitude() == 14.0025);
        } catch (PlaceException e) {
            assertTrue(false);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    public void testGetAddress()
    {
        try {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");

            assertTrue(place.getAddress().equals("rua das flores"));
        } catch (PlaceException e) {
            assertTrue(false);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    public void testInvalidName()
    {
        try {
            Place place= new Place("","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");

            assertFalse(true);
        } catch (PlaceException e) {
            assertFalse(false);
        } catch (ParseException e) {
            assertFalse(false);
        }
    }

    public void testAddComment()
    {
        try {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");
            place.addComment("numero1");
            place.addComment("numero2");
            assertTrue(place.getComment().size() == 2);
        } catch (PlaceException e) {
            assertTrue(false);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    public void testAddNullComment()
    {
        try {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");
            place.addComment(null);
            assertFalse(place.getComment().size() == 1);
        } catch (PlaceException e) {
            assertFalse(false);
        } catch (ParseException e) {
            assertFalse(false);
        }
    }


    public void testAddEmptyComment()
    {
        try {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");
            place.addComment("");
            assertFalse(place.getComment().size() == 1);
        } catch (PlaceException e) {
            assertFalse(false);
        } catch (ParseException e) {
            assertFalse(false);
        }
    }


    public void testGetComment()
    {
        try {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");
            place.addComment("numero1");
            place.addComment("numero2");
            assertTrue(place.getComment().get(0).equals("numero1") && place.getComment().get(1).equals("numero2"));
        } catch (PlaceException e) {
            assertTrue(false);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    public void testSetWrongEvaluated()
    {
        try {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");
            place.setEvaluate("gfhj");
            assertFalse(true);
        } catch (PlaceException e) {
            assertFalse(false);
        } catch (ParseException e) {
            assertFalse(false);
        } catch (NumberFormatException e) {
            assertFalse(false);
        }
    }

    public void testSetEvaluated()
    {
        try {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");
            place.setEvaluate("4.5");
            assertTrue(true);
        } catch (PlaceException e) {
            assertTrue(false);
        } catch (ParseException e) {
            assertTrue(false);
        } catch (NumberFormatException e) {
            assertTrue(false);
        }
    }

    public void testSetNullStringEvaluated()
    {
        try {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores");
            place.setEvaluate("null");
            assertTrue(true);
        } catch (PlaceException e) {
            assertTrue(false);
        } catch (ParseException e) {
            assertTrue(false);
        } catch (NumberFormatException e) {
            assertTrue(false);
        }
    }
}
