package dao;

import android.app.Activity;

import model.User;

/**
 * Created by emilie on 10/09/15.
 */
public class UserDAO extends DAO{

    public UserDAO(Activity activity){
        super(activity);
    }

    public void save(User user) {
        this.executeQuery("INSERT INTO users(username,password) VALUES ('"
                        +user.getUsername()+"','"+user.getPassword()+"')");

    }


}
