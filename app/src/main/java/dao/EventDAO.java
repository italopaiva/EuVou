package dao;

import android.app.Activity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Vector;

import exception.EventException;
import model.Event;

/**
 * Created by geovanni on 10/10/15.
 */
public class EventDAO extends DAO {

    public EventDAO(Activity currentActivity) {
        super(currentActivity);
    }

    public EventDAO(){

    }

    public void saveEvent(Event event) throws JSONException {
        if(executeConsult("Select count(longitude) from tb_locate where longitude = " +
                event.getLongitude() +" and latitude = " + event.getLatitude()) == null)
            executeQuery("insert into tb_locate values("+event.getLongitude() +","+event.getLatitude() +
                    "')");

        executeQuery("insert into tb_event(nameEvent,idOwner,dateTimeEvent,description,longitude,latitude) VALUES('" +
                event.getNameEvent() + "'," + event.getIdOwner() + ",'" + event.getDateTimeEvent() + "','" + event.getDescription() + "'," +
                "" + event.getLongitude() + "," + event.getLatitude() + ")");

        //String query = "";

        Vector<String> categories = event.getCategory();

        //for (String category : event.getCategory()) {
        for(int i=0; i<categories.size(); i++){
            String query = "INSERT INTO event_category(idEvent, idCategory) VALUES((SELECT idEvent FROM tb_event " +
                    "WHERE nameEvent='"+event.getNameEvent()+"'), " +
                    "(SELECT idCategory FROM tb_category WHERE nameCategory = '"+categories.get(i)+"'))";

            executeQuery(query);
        }

    }
    public String deleteEvent(Event event)
    {
       return this.executeQuery("DELETE FROM tb_event WHERE idEvent ="+event.getIdEvent());
    }

    public void updateEvent(Event event)
    {
        if(executeConsult("Select count(longitude) from tb_locate where longitude = " +
                event.getLongitude() +" and latitude = " + event.getLatitude()) == null)
            executeQuery("insert into tb_locate values("+event.getLongitude() +","+event.getLatitude() +
                    "')");

        executeQuery("UPDATE tb_event SET nameEvent=\""+event.getNameEvent()+"\", "+"dateTimeEvent=\""+event.getDateTimeEvent()+
                "\", "+"description=\""+event.getDescription()+"\", "+"longitude=\""+event.getLongitude()+"\", "+"latitude=\""+event.getLatitude()+" \", \""+event.getCategory()+" \")");

        executeQuery("delete from event_category where idEvent ="+event.getIdEvent());

        String query = "";
        for (String category : event.getCategory()) {
            query += "INSERT INTO event_category VALUES("+event.getIdEvent() +","
                    + "(SELECT idCategory FROM tb_category WHERE namecategory = '"+category+"'));";
        }
        executeQuery(query);
    }
    public JSONObject searchEventByName(String eventName)
    {
        return this.executeConsult("SELECT * FROM tb_event WHERE nameEvent=\""+ eventName + "\"");
    }

    public Vector<Event> searchEventByOwner(int owner) throws JSONException, ParseException, EventException {
        JSONObject json = this.executeConsult("SELECT * FROM tb_event WHERE idOwner=\"" + owner + "\" GROUP BY idEvent");

        if(json == null)
            return null;

        Vector<Event> events = new Vector<>();

        for (int i = 0; i < json.length(); i++)
        {
            Event event = new Event(json.getJSONObject(""  + i).getInt("idEvent"),json.getJSONObject(""  + i).getInt("idOwner"),
                    json.getJSONObject("" + i).getString("nameEvent"),
                    json.getJSONObject("" + i).getString("dateTimeEvent"),json.getJSONObject(""  + i).getString("description"),
                    json.getJSONObject("" + i).getString("longitude"),json.getJSONObject(""  + i).getString("latitude"));
            events.add(event);
        }

        return events;
    }


}
