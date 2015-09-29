package dao;

import android.app.Activity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

public abstract class DAO {

    private final String urlQuery = "http://euvou.esy.es/query.php";
    private final String urlConsult = "http://euvou.esy.es/consult.php";
    protected Activity activity;

    protected String executeQuery(String query){
        Consult consult = new Consult(query,urlQuery);
        consult.exec();
        while(!consult.getIsDoing());
        return consult.getResult();
    }

    protected JSONObject executeConsult(String query)
    {
        String json;
        Consult consult = new Consult(query,urlConsult);
        consult.exec();
        while(!consult.getIsDoing());

        json = consult.getResult();
        JSONObject jObject = null;
        try {
            jObject  = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jObject;
    }

}
