package com.mathheals.euvou;

import android.app.usage.UsageEvents;

import junit.framework.TestCase;

import java.text.ParseException;

import exception.EventException;
import model.Event;

public class EventTest extends TestCase{
    private  Event event;

    public void testNameEmpty() throws ParseException {
        boolean ok = false;
        try{
            event = new Event("","14/10/2015","Endereço","Descrição do evento", 50.01,60.002);
                ok = false;
        }catch(EventException e)
        {
            ok = true;
        }catch(ParseException ex)
        {
            ok = false;
        }
        assertTrue(ok);
    }
    public void testNameIsNotEmpty()
    {
        boolean ok = false;
        try{
             event =  new Event("Geovanni","22/02/2016","Endereço","descrição",21.4,30.2);
            ok = true;
        }catch(EventException e)
        {
            ok = false;
        }catch(ParseException ex)
        {
            ex.printStackTrace();
        }
       assertTrue(ok);
    }
    public void testNameIsBiggerThanMax()
    {
        boolean ok = true;
        try
        {
            event = new Event("Joãozinho da Silva Gosta da Dilma, venham conhecer esse jovem muito jovem","02/02/2016","Cidade Vizinha","Venham conhecer o Joãozinho!",50.8,60.2);

        } catch (EventException e) {
            e.printStackTrace();
            ok = false;
        }catch(ParseException ex)
        {

        }
        assertFalse(ok);
    }
    public void testIfDateIsEmpty()  {
        boolean ok = true;
        try {
            event = new Event("festa2", "","Rua dos marajós", "festa top",34.0,34.0);
        } catch (EventException e) {
            e.printStackTrace();
            ok=false;
        }catch(ParseException ex)
        {

        }
        assertFalse(ok);
    }

    public void testIfDescriptionIsEmpty()
    {
        boolean ok = true;
        try{
            event = new Event("FG Party","10/10/2016","Rua perdida","",44.2,46.2);
            ok = true;

        }catch(EventException e)
        {
            ok = false;
        }catch(ParseException ex)
        {

        }
        assertFalse(ok);
    }

    public void testIfDescriptionIsOk()
    {
        boolean ok = true;
        try{

            event = new Event("FG a Party","10/10/2016","Um lugar perdido","Venha se perder com a gente!", 44.2,65.2);
        }catch(EventException e){
            ok = false;
        }catch(ParseException ex)
        {
        }
        assertTrue(ok);
    }

    public void testifDescriptionGoesOverTheMaximumValue()
    {
        boolean ok = true;
        try
        {
            event = new Event("FG a Party","10/10/2016","Canto da vida" ,"kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk\n" +
                    "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk\n" +
                    "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk\n" +
                    "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk\n" +
                    "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk\n" +
                    "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk\n" +
                    "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk\n" +
                    "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk\n" +
                    "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk\n" +
                    "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk\n" +
                    "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk", 55.2,79.9);

        }catch(EventException e)
        {
            ok = false;
        }catch(ParseException ex)
        {

        }
        assertFalse(ok);
    }

    public void testIfLatitudeIsUnderMinus90()
    {
        boolean ok = false;
        try
        {
            event = new Event("Evento teste","12/12/2015","Descrição endereço","Descrição teste de evento",-90.9,140.2);
        }catch(EventException e)
        {
            ok = true;
        }catch(ParseException ex)
        {

        }
        assertTrue(ok);
    }

    public void testIfLatitudeIsAbove90()
    {
        boolean ok = false;
        try
        {
            event = new Event("Evento teste","12/12/2015","quadra 04","Descrição teste de evento",99.2,130.2);
        }catch(EventException e)
        {
            ok = true;
        }
        catch(ParseException ex)
        {

        }
        assertTrue(ok);
    }

    public void testIfLatitudeIsOk()
    {
        boolean ok = false;
        try
        {
            event = new Event("Evento teste","12/12/2015","The darkest Street ever","Descrição teste de evento",-40.9,140.2);
        }catch(EventException e)
        {
            ok = true;
        }catch(ParseException ex)
        {

        }
        assertFalse(ok);
    }



}