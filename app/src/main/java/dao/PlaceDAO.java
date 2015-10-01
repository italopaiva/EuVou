package dao;

import org.json.JSONObject;

/**
 * Created by viny on 30/09/15.
 */
public class PlaceDAO extends DAO {

    public JSONObject searchPlaceByPartName(String name)
    {
        return this.executeConsult("SELECT * FROM vw_place WHERE namePlace LIKE '%" + name + "%'");
    }
}
