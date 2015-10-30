package com.mathheals.euvou;

import android.app.Activity;

import junit.framework.TestCase;

import org.json.JSONException;

import java.text.ParseException;
import java.util.Vector;

import dao.EventDAO;
import exception.EventException;
import model.Event;

/**
 * Created by viny on 30/10/15.
 */
public class EventDAOTest extends TestCase {

    private Activity activity;

    public EventDAOTest()
    {
        activity = new Activity();
    }

    public void testSearchEventByOwner() throws ParseException, EventException, JSONException {
        EventDAO eventDAO = new EventDAO(activity);
        assertEquals(eventDAO.searchEventByOwner(-1), null);
    }

    public void testSearchEventByOwner2() throws ParseException, EventException, JSONException {
        EventDAO eventDAO = new EventDAO(activity);
        assertNotSame(eventDAO.searchEventByOwner(3), null);
    }


    public void testEventDelet() throws ParseException, EventException, JSONException {

       /* EventDAO eventDAO = new EventDAO(activity);
        Vector<String> category = new Vector<>();
        category.add("Outros");

        Event event = new Event(3,"Evento Delete","14/02/2000 20:15:10","DESCRICAO", "12.20","78.41520",category);

        eventDAO.saveEvent(event);
        if (eventDAO.searchEventByName("Evento Delete") == null) fail();
        int id = eventDAO.searchEventByName("Evento Delete").getJSONObject("0").getInt("idEvent");


        event = new Event(id,2,"Evento Delete","14/02/2000 20:15:10","DESCRICAO", "12.20","78.41520","null");

        eventDAO.deleteEvent(event);

        assertEquals(eventDAO.searchEventByName("Evento Delete"), null);*/
    }
}
