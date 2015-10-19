package dao;

import org.json.JSONObject;

import model.Event;

/**
 * Created by geovanni on 10/10/15.
 */
public class EventDAO extends DAO {

    public void saveEvent(Event event){
        if(executeConsult("Select count(longitude) from tb_locate where longitude = " +
                event.getLongitude() +" and latitude = " + event.getLatitude()) == null)
            executeQuery("insert into tb_locate values("+event.getLongitude() +","+event.getLatitude() +
                    ",'"+event.getAdress()+"')");

        executeQuery("insert into tb_event(nameEvent,dateTimeEvent,description,longitude,latitude) VALUES('" +
                event.getNameEvent() + "','" + event.getDateTimeEvent() + "','" + event.getDescription() + "'," +
                "" + event.getLongitude() + "," + event.getLatitude() + ")");

        String query = "";
        for (String category : event.getCategory()) {
            query += "INSERT INTO event_category VALUES(" +
                    "(SELECT idEvent FROM tb_event WHERE nameEvent = '"+event.getNameEvent()+"' AND dateTimeEvent = '"+event.getDateTimeEvent()+"' AND " +
                    "description = '"+event.getDescription()+"' AND longitude ="+event.getLongitude()+"  AND latitude ="+event.getLatitude()+" )," +
                    "(SELECT idCategory FROM tb_category WHERE namecategory = '"+category+"'));";
        }

        executeQuery(query);

    }
    public  void deleteEvent(Event event)
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
