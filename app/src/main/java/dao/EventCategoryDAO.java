package dao;

import android.app.Activity;

import org.json.JSONObject;

/**
 * Created by julliana on 28/10/15.
 */
public class EventCategoryDAO extends DAO{

    public EventCategoryDAO(Activity currentActivity){
        super(currentActivity);
    }

    public JSONObject searchCategoriesByEventId(int idEvent){
        return this.executeConsult("SELECT idCategory FROM event_category WHERE idEvent = " + idEvent);
    }
}