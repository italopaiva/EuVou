package model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by igor on 07/09/15.
 */
public class User {

    private int idUser;
    private String name;
    private String username;
    private String email;
    private String password;
    private String birthDate;

    public User(int idUser, String name, String username, String email, String password, String birthDate){
        this.idUser=idUser;
        this.name=name;
        this.username=username;
        this.email=email;
        this.password=password;
        this.birthDate=birthDate;
    }

    public User(String name, String username, String email, String password, String birthDate){
        this.name=name;
        this.username=username;
        this.email=email;
        this.password=password;
        this.birthDate=birthDate;
    }


    private void setIdUser(int idUser){
        this.idUser = idUser;
    }

    private void setName(String name){
        try{
            if (name.length()<=200){
                this.name=name;
            }else {
                Log.d(name, "O nome digitado precisa ter quantidade de caracteres menor ou igual a 200");
            }
        }catch (NullPointerException e){
            Log.d(name, "O campo email é obrigatório");
        }
    }

    public void setUsername (String username) {
        int count=0;

        try{
            for(int i=0; i<username.length(); i++){
                if(username.charAt(i)==' '){
                    count++;
                }
            }
            if (username.length()<=200 && count==0){
                this.username=username;
            }else {
                Log.d(username, "O login digitado precisa ter quantidade de caracteres menor ou igual a 200 e não pode conter espaços em branco");
            }
        }catch (NullPointerException e){
            Log.d(username, "O campo email é obrigatório");
        }
    }

    private void setEmail(String email){
        try{
            if(email.length()<=150) {
                this.email = email;
            }else{
                Log.d(email, "O email deve conter no máximo 150 caracteres");
            }
        }catch (NullPointerException e){
            Log.d(email, "O campo email é obrigatório");
        }
    }

    private void setPassword(String password){
        try{
            if(password.length()>=8) {
                this.password = password;
            }else{
                Log.d(password, "A senha deve conter no minimo 8 caracteres");
            }
        }catch (NullPointerException e){
            Log.d(password, "O campo senha e obrigatório");
        }

    }

    private void setBirthDate(String birthDate){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try{
            Date date = sdf.parse(birthDate);
            this.birthDate=birthDate;
        }catch (ParseException e){
            Log.d(birthDate, "Data inválida");
        }
    }

    public int getIdUser(){
        return idUser;
    }

    public String getName(){
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getBirthDate(){
        return birthDate;
    }

    public String name(){
        return name;
    }
}
