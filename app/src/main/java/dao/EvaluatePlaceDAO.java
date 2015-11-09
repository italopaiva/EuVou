package dao;

import android.app.Activity;

import org.json.JSONObject;

/**
 * Created by marlonmendes on 09/11/15.
 */
public class EvaluatePlaceDAO extends DAO{

    public EvaluatePlaceDAO() {}

    public EvaluatePlaceDAO(Activity currentActivity) {
        super(currentActivity);
    }

    public void evaluatePlace(float grade, int idUser, int idPlace) {
        final String QUERY = "INSERT INTO evaluate_place(grade, idUser, idPlace) VALUES (\"" + grade + "\"," +
                                                                                  "\"" + idUser + "\"," +
                                                                                  "\"" + idPlace + "\")";
        executeQuery(QUERY);
    }

    public JSONObject searchEvaluateByPlaceID(int idPlace) {
        final String QUERY = "SELECT * FROM evaluate_place WHERE idPlace = " + idPlace;
        return executeConsult(QUERY);
    }
}
