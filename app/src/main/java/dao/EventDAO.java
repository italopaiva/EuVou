package dao;

import model.Event;

/**
 * Created by geovanni on 10/10/15.
 */
public class EventDAO extends DAO {

    public void saveEvent(Event event){
        this.executeQuery("INSERT INTO tb_event(nameEvent,description,latitude,longitude)VALUES"+"(\""+event.getNameEvent()+" \", \""+
        event.getDescription()+" \", \""+event.getLatitude()+" \", \""+event.getLongitude()+" \")");

    }
    public  void deleteEvent(String eventName)
    {
        this.executeQuery("DELETE FROM tb_event WHERE nameEvent =\""+eventName+"\"");
    }
}
