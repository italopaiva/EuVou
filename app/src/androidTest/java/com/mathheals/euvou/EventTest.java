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

    //testes Juh manjadora dos testes!
}