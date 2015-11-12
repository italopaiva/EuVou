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
    
    public EventDAO(){}

    public void saveEvent(Event event){
        executeQuery("insert into tb_event(nameEvent, price, address, dateTimeEvent,description,longitude,latitude) VALUES('" +
                event.getNameEvent() + "', '" + event.getPrice() + "', '" + event.getAddress() + "','" + event.getDateTimeEvent() + "','" + event.getDescription() + "'," +
                "" + event.getLongitude() + "," + event.getLatitude() + ")");

        Vector<String> categories = event.getCategory();
        JSONObject jsonObject = executeConsult("SELECT idEvent FROM tb_event WHERE nameEvent = \"" + event.getNameEvent() + "\"");
        int idEvent = 0;
        try {
            idEvent = jsonObject.getJSONObject("0").getInt("idEvent");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0; i<categories.size(); i++){
            String query = "INSERT INTO event_category(idEvent, idCategory) VALUES(\"" + idEvent + "\", " +
                    "(SELECT idCategory FROM tb_category WHERE nameCategory = \""+categories.get(i)+"\"))";

            executeQuery(query);
        }

    }
    public  String deleteEvent(Event event)
    {
        return this.executeQuery("DELETE FROM tb_event WHERE idEvent ="+event.getIdEvent());
    }

    public void updateEvent(Event event)
    {
        executeQuery("UPDATE tb_event SET price=\"" + event.getPrice() + "\", address=\"" + event.getAddress() + "\", " +
                "nameEvent=\""+event.getNameEvent()+"\", "+"dateTimeEvent=\""+event.getDateTimeEvent()+
                "\", "+"description=\""+event.getDescription()+"\", "+"longitude=\""+event.getLongitude()+"\", " +
                " "+" latitude=\""+event.getLatitude()+ "\" WHERE idEvent = " + event.getIdEvent());

        executeQuery("delete from event_category where idEvent ="+event.getIdEvent());

        for (String category : event.getCategory()) {
            String query = "INSERT INTO event_category VALUES("+event.getIdEvent() +","
                    + "(SELECT idCategory FROM tb_category WHERE namecategory = '"+category+"'));";

            executeQuery(query);
        }

    }

    public JSONObject searchEventByName(String eventName){
        return this.executeConsult("SELECT * FROM vw_event WHERE nameEvent LIKE'%"+eventName+"%'");
    }

    public JSONObject searchEventByNameGroup(String eventName)
    {
        return this.executeConsult("SELECT * FROM vw_event WHERE nameEvent LIKE'%"+eventName+"%' GROUP BY idEvent");
    }

    public JSONObject searchEventById(int idEvent){
        return this.executeConsult("SELECT * FROM tb_event WHERE idEvent = " + idEvent);
    }

    public Vector<Event> searchEventByOwner(int owner) throws JSONException, ParseException, EventException {
        JSONObject json = this.executeConsult("SELECT * FROM tb_event WHERE idOwner=" + owner + " GROUP BY idEvent");

        if(json == null)
            return null;

        Vector<Event> events = new Vector<>();

        for (int i = 0; i < json.length(); i++)
        {

            Event event = new Event(json.getJSONObject(""  + i).getInt("idEvent"),
                    json.getJSONObject(""  + i).getInt("idOwner"),
                    json.getJSONObject("" + i).getString("nameEvent"),
                    json.getJSONObject("" + i).getString("dateTimeEvent"),
                    json.getJSONObject("" + i).getInt("price"),
                    json.getJSONObject("" + i).getString("address"),
                    json.getJSONObject(""  + i).getString("description"),
                    json.getJSONObject("" + i).getString("latitude"),
                    json.getJSONObject("" + i).getString("longitude")
            );
            events.add(event);
        }

        return events;
    }
}
