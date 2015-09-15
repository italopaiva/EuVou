package dao;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.List;

public class Request extends Activity{

    private static final String DATABASE_WEB_SERVICE_URL = "http://192.168.25.18/webservice/database_access.php";
    private static final String POST_METHOD = "POST";
    private static final String GET_METHOD = "GET";

    private List<NameValuePair> params;
    private String result;

    private Activity currentActivity;

    public Request(List<NameValuePair> params){
        setParams(params);
    }

    public Request(List<NameValuePair> params, Activity activity){
        setParams(params);
        setCurrentActivity(activity);
    }

    public String execute(){
        new Access().execute();

        String result = result();

        return result;
    }

    private void setParams(List<NameValuePair> params){
        this.params = params;
    }

    private void setCurrentActivity(Activity activity){
        this.currentActivity = activity;
    }

    private void setResult(String result){
        this.result = result;
    }

    private String result(){
        return this.result;
    }

    private JSONObject makeRequest(List<NameValuePair> params){

        JSONParser jsonParser = new JSONParser();

        JSONObject queryResult = jsonParser.makeHttpRequest(DATABASE_WEB_SERVICE_URL, POST_METHOD, params);

        return queryResult;
    }

    private class Access extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {

            JSONObject json = Request.this.makeRequest(Request.this.params);

            return json.toString();
        }


        protected void onPostExecute(String jsonResult) {

            Request.this.setResult(jsonResult);

            if (jsonResult != null) {
                Toast.makeText(Request.this.currentActivity, jsonResult, Toast.LENGTH_LONG).show();
            }
        }
    }
}
