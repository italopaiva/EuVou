package dao;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.NameValuePair;

import java.util.List;

public class Consult extends Activity{

    private static final String DATABASE_WEB_SERVICE_URL = "http://192.168.25.18/webservice/query.php";
    private static final String POST_METHOD = "POST";

    private List<NameValuePair> params;
    private String result;

    private Activity currentActivity;

    public Consult(List<NameValuePair> params){
        setParams(params);
    }

    public Consult(List<NameValuePair> params, Activity activity){
        setParams(params);
        setCurrentActivity(activity);
    }

    public String execute(){
        new Access().execute();

        return result();
    }

    private void setParams(List<NameValuePair> params){
        this.params = params;
    }

    private void setCurrentActivity(Activity activity){
        this.currentActivity = activity;
    }

    private String result(){
        return this.result;
    }

    private String makeRequest(List<NameValuePair> params){

        QueryParse queryParse = new QueryParse();

        return queryParse.makeHttpRequest(DATABASE_WEB_SERVICE_URL, POST_METHOD, params);

    }

    private class Access extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {

            return Consult.this.makeRequest(Consult.this.params);

        }

        protected void onPostExecute(String result) {

            if (result != null) {
                Toast.makeText(Consult.this.currentActivity, result, Toast.LENGTH_LONG).show();
            }

        }
    }
}
