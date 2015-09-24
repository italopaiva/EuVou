package dao;

import android.app.Activity;

public class UserDAO extends DAO{

    public UserDAO(){}

    public UserDAO(Activity activity){

        super(activity);
    }

    public String save(String user){

        String query = "INSERT INTO tb_user(nameUser, login,passwordUser,birthDate, email)\n" +
                "VALUES\n" +
                "(\"Vinicius Pinheiro\", \"VinyPinheiro\", \"123\", \"1995-02-14\",\"viny-pinheiro@hotmail.com\")";

        String result = this.executeQuery(query);

        return result;
    }
}
