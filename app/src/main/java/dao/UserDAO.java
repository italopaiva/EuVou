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
                "(\"" + user.getName() + "\", \"" + user.getUsername() + "\", \"" + user.getPassword() + "\"," +
                " STR_TO_DATE(\"" + user.getBirthDate() + "\",'%d/%m/%Y'),\"" + user.getEmail() + "\")");
    }

    public String searchUserById(int idUser){
        return this.executeConsult("SELECT * from vw_user WHERE idUser=1").toString();
    }

    public void update(User user){
        this.executeQuery("UPDATE tb_user SET nameUser=\""+user.getName()+"\", birthDate=\""+user.getBirthDate()+"\", " +
        "email=\""+user.getEmail()+"\" WHERE idUser=\""+user.getIdUser()+"\"");
    }

    public void delete(int idUser){
        this.executeQuery("UPDATE tb_user SET isActivity=\"N\" WHERE idUser=" +idUser+ "");
    }
    public void searchUserByName(String searchNameUser)
    {
        this.executeConsult("Select * from vw_user where nameUser = searchNameUser");
    }
}
