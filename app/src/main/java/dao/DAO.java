package dao;

import android.app.Activity;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class DAO {

    private final String urlQuery = "http://euvou.esy.es/query.php";
    private final String urlConsult = "http://euvou.esy.es/consult.php";
    private long timeLimit;
    private final int LIMITCONECTIONTIME = 15000;

    protected String executeQuery(String query){
        Consult consult = new Consult(query,urlQuery);
        consult.exec();

        long currentTime = Calendar.getInstance().getTime().getTime();
        timeLimit = currentTime + LIMITCONECTIONTIME;

        while(!consult.getIsDoing() && currentTime < timeLimit)
            currentTime = Calendar.getInstance().getTime().getTime();

        if(currentTime >= timeLimit) {
            return null;
        }
        return consult.getResult();
    }

    protected JSONObject executeConsult(String query)
    {
        String json;
        Consult consult = new Consult(query,urlConsult);
        consult.exec();

        long currentTime = Calendar.getInstance().getTime().getTime();
        timeLimit = currentTime + LIMITCONECTIONTIME;

        while(!consult.getIsDoing() && currentTime < timeLimit)
            currentTime = Calendar.getInstance().getTime().getTime();

        if(currentTime >= timeLimit) {
            return null;
        }

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