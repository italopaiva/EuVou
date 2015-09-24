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
        this.executeQuery("INSERT INTO tb_user(nameUser, login,passwordUser,birthDate, email)VALUES" +
                "(\"" + user.getName() + "\", \"" + user.getUsername() +  "\", \"" + user.getPassword() +  "\"," +
                " STR_TO_DATE(\"" + user.getBirthDate() +  "\",'%d/%m/%Y'),\"" + user.getEmail() +  "\")");
    }

    public void update(User user){

    }

}
