package dao;

import org.json.JSONObject;

import model.Event;

/**
 * Created by geovanni on 10/10/15.
 */
public class EventDAO extends DAO {

    public void saveEvent(Event event){
        this.executeQuery("INSERT INTO tb_event(nameEvent,dateTimeEvent,description,longitude,latitude,adress,nameCategory)VALUES"+"(\""+event.getNameEvent()+" \", \""+
        event.getDateTimeEvent()+" \", \""+event.getDescription()+" \", \""+event.getLongitude()+" \", \""+event.getLatitude()+" \", \""+event.getAdress()+" \", \""+
                event.getCategory()+" \")");

    }
    public  void deleteEvent(String eventName)
    {
        this.executeQuery("DELETE FROM tb_event WHERE nameEvent =\""+eventName+"\"");
    }

    public void updateEvent(Event event)
    {
        this.executeQuery("UPDATE tb_event SET nameEvent=\""+event.getNameEvent()+"\", "+"dateTimeEvent=\""+event.getDateTimeEvent()+
        "\", "+"description=\""+event.getDescription()+"\", "+"longitude=\""+event.getLongitude()+"\", "+"latitude=\""+event.getLatitude()+" \", \""+event.getCategory()+" \")");
    }
    public JSONObject searchEventByName(String eventName)
    {
       return this.executeConsult("SELECT * FROM vw_event WHERE nameEvent LIKE'%"+eventName+"%'");
    }
}
