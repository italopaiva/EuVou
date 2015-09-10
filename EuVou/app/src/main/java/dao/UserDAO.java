package dao;

import android.app.Activity;

public class UserDAO extends DAO{

    public UserDAO(){}

    public UserDAO(Activity activity){

        super(activity);
    }

    public String save(String user){

        String query = "INSERT INTO users(username, password) VALUES('"+user+"', '1234')";

        String result = this.executeQuery(query);

        return result;
    }
}
