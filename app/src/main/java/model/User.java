package model;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exception.UserException;


public class User {

    public static final String NAME_CANT_BE_EMPTY_NAME = "Hey, acho que você está esquecendo de nos dizer seu nome.";
    public static final String NAME_CANT_BE_HIGHER_THAN_200 = "Hey, acho que você ultrapassou o número de caracteres permitido para o nome, tente novamente.";
    public static final String EMAIL_CANT_BE_EMPTY_EMAIL = "Hey, acho que você está esquecendo de nos dizer seu email.";
    public static final String EMAIL_CANT_BE_HIGHER_THAN_150 = "Hey, acho que você ultrapassou o número de caracteres permitido para email, tente novamente.";
    public static final String USERNAME_CANT_BE_EMPTY_USERNAME = "Hey, acho que você está esquecendo de nos dizer seu login.";
    public static final String USERNAME_CANT_BE_HIGHER_THAN_100 = "Hey, acho que você ultrapassou o número de caracteres permitido para o login, tente novamente.";
    public static final String INVALID_EMAIL = "Ops, acho que você não nos informou um email.";

    private static final int MAX_LENGTH_NAME = 200;
    private static final int MAX_LENGTH_EMAIL = 150;
    private static final int MAX_LENGTH_USERNAME = 100;

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

    private  void  setEmail(String email) throws UserException{

        if (!isEmailEmpty(email)) {
            if(email.length() <= MAX_LENGTH_EMAIL){
                CharSequence emailCharSequence = email;
                boolean emailIsOk = Patterns.EMAIL_ADDRESS.matcher(emailCharSequence).matches();

                if(emailIsOk){
                    this.email = email;
                }else{
                    throw  new UserException(INVALID_EMAIL);
                }

            }
            else{
                throw  new UserException(EMAIL_CANT_BE_HIGHER_THAN_150);
            }
        }else{
            throw  new UserException(EMAIL_CANT_BE_EMPTY_EMAIL);
        }

    }

    private  void  setUsername (String username) throws UserException{

        if (!isUsernameEmpty(username)) {
            if(username.length() <= MAX_LENGTH_USERNAME){
                this.username = username;
            }
            else{
                throw  new UserException(USERNAME_CANT_BE_HIGHER_THAN_100);
            }
        }else{
            throw  new UserException(USERNAME_CANT_BE_EMPTY_USERNAME);
        }

    }

    private boolean isNameEmpty(String name){
        boolean isEmpty = false;
        if (TextUtils.isEmpty(name)) {
            isEmpty = true;
        }
        return isEmpty;
    }

    private boolean isEmailEmpty(String email){
        boolean isEmpty = false;
        if (TextUtils.isEmpty(email)) {
            isEmpty = true;
        }
        return isEmpty;
    }

    private boolean isUsernameEmpty(String username){
        boolean isEmpty = false;
        if (TextUtils.isEmpty(username)) {
            isEmpty = true;
        }
        return isEmpty;
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
