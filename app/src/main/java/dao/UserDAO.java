package dao;

import android.app.Activity;

import org.json.JSONObject;

import model.User;

/**
 * Created by emilie on 10/09/15.
 */
public class UserDAO extends DAO{

    public UserDAO(){}

    public void save(User user) {
        this.executeQuery("INSERT INTO tb_user(nameUser, login,passwordUser,birthDate, email)VALUES" +
                "(\"" + user.getName() + "\", \"" + user.getUsername() + "\", \"" + user.getPassword() + "\"," +
                " STR_TO_DATE(\"" + user.getBirthDate() + "\",'%d/%m/%Y'),\"" + user.getEmail() + "\")");
    }

    public String searchUserById(int idUser){
        return this.executeConsult("SELECT * from vw_user WHERE idUser="+idUser+"").toString();
    }


    public void update(User user){
        this.executeQuery("UPDATE tb_user SET nameUser=\""+user.getName()+"\", birthDate=\""+user.getBirthDate()+"\", " +
                "email=\""+user.getEmail()+"\" WHERE idUser=\""+user.getIdUser()+"\"");
    }

    public void delete(int idUser){
        this.executeQuery("UPDATE tb_user SET isActivity=\"N\" WHERE idUser=" +idUser+ "");
    }

    public JSONObject searchUserByUsername(String username)
    {
        return this.executeConsult("SELECT * from vw_user WHERE login=\""+username+"\"");
    }
}