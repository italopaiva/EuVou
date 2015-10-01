package dao;
import org.json.JSONObject;
import model.User;
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


    //This method is just used on the tests
    public void delete(String username){
        this.executeQuery("delete from tb_user where login=\"" +username+ "\"");
    }

    public void update(User user){
        this.executeQuery("UPDATE tb_user SET nameUser=\""+user.getName()+"\", " +
                "birthDate=STR_TO_DATE(\"" + user.getBirthDate() + "\",'%d/%m/%Y'), " +
                "email=\""+user.getEmail()+"\", passwordUser=\"" + user.getPassword() + "\"" +
                " WHERE idUser=\""+user.getIdUser()+"\"");
    }

    public void disableUser(int idUser){
        this.executeQuery("UPDATE tb_user SET isActivity=\"N\" WHERE idUser=" +idUser+ "");
    }

    public JSONObject searchUserByUsername(String username)
    {
        return this.executeConsult("SELECT * from vw_user WHERE login=\""+username+"\"");
    }
}