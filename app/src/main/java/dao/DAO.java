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
    private final int LIMITCONECTIONTIME = 10000;
    private Activity currentActivity;
    public DAO(Activity currentActivity){
        this.currentActivity = currentActivity;
    }


    protected String executeQuery(String query){
        Consult consult = new Consult(query,urlQuery);
        consult.exec();

        long currentTime = Calendar.getInstance().getTime().getTime();
        timeLimit = currentTime + LIMITCONECTIONTIME;
        while(!consult.getIsDoing() && currentTime < timeLimit)
            currentTime = Calendar.getInstance().getTime().getTime();

        if(currentTime >= timeLimit) {
            Toast.makeText(currentActivity,"Problema de conexão com o servidor (verifique se esta conectado a internet)", Toast.LENGTH_LONG).show();
            return null;
        }

        return consult.getResult();
    }

    protected JSONObject executeConsult(String query)
    {
        String json;
        JSONObject jObject = null;
        try {
            json = executeQuery(query);
            jObject  = new JSONObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jObject;
    }

}