package com.mathheals.euvou;

import junit.framework.TestCase;

import java.text.ParseException;

import exception.EventException;
import model.Event;

public class EventTest extends TestCase{
    private  Event event;

    //Testing names


    //testes Juh
    //Testing dates

    //testes feitos anteriormente = DATAS
    //inicio testes Geovanni

    public void testNameEmpty()
    {
        boolean ok = false;
        try{
            event = new Event("","01/01/2015","descrição do evento aí",40.5,60.9);
                ok = false;
        }catch(EventException e)
        {
            ok = true;
        }
        assertTrue(ok);
    }
    public void testNameIsNotEmpty()
    {
        boolean ok = true;
        try{
            event = new Event("Joãozinho","01/01/2015","descrição do evento aí",40.5,60.9);
            ok = true;
        }catch(EventException e)
        {
            ok = false;
        }
        assertFalse(ok);
    }
    public void testNameIsBiggerThanMax()
    {
        boolean ok = true;
        try
        {
            event = new Event("Joãozinho da Silva Gosta da Dilma, venham conhecer esse jovem muito jovem","02/02/2015","Venham conhecer o Joãozinho!",50.8,60.2);
            ok = true;
        } catch (EventException e) {
            e.printStackTrace();
            ok = false;
        }
        assertFalse(ok);
    }
    public void testNameIsvalid()
    {
        boolean ok = true;
        try
        {
            event = new Event("Joãozinho da Silva Gosta de sorvete","02/02/2015","Venham conhecer o Joãozinho!",50.8,60.2);
            ok = true;
        } catch (EventException e) {
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);
    }


    public void testIfDateIsEmpty() {
        boolean ok = true;
        try {
            event = new Event("festa2", "", "festa top",34.0,34.0);
        } catch (EventException e) {
            e.printStackTrace();
            ok=false;
        }
        assertTrue(ok);
    }
}