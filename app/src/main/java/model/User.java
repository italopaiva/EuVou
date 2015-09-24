package model;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exception.UserException;

/**
 * Created by igor on 07/09/15.
 */
public class User {

    public static final String NAME_CANT_BE_EMPTY_NAME = "Hey, acho que você está esquecendo de nos dizer seu nome.";
    public static final String NAME_CANT_BE_HIGHER_THAN_200 = "Hey, acho que você ultrapassou o número de caracteres permitid, tente novamente.";


    private static final int MAX_LENGTH_NAME = 200;

    private int idUser;
    private String name;
    private String username;
    private String email;
    private String password;
    private String birthDate;

    public User(int idUser, String name, String birthDate, String email) throws UserException {
        setName(name);
        setIdUser(idUser);
        setEmail(email);
        setBirthDate(birthDate);
    }

    public User(String name, String username, String email, String password, String birthDate) throws UserException {
        setName(name);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setBirthDate(birthDate);
    }


    private void setIdUser(int idUser){
        this.idUser = idUser;
    }

    private void setName(String name) throws UserException {

        if(!isNameEmpty(name)){

            if(name.length() <= MAX_LENGTH_NAME){
                this.name = name;
            }else{
                throw new UserException(NAME_CANT_BE_HIGHER_THAN_200);
            }

        }else{
            throw new UserException(NAME_CANT_BE_EMPTY_NAME);
        }
    }

    private boolean isNameEmpty(String name){
        boolean isEmpty = false;
        if (TextUtils.isEmpty(name)) {
            isEmpty = true;
        }
        return isEmpty;
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
