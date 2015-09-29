package model;

import android.util.Patterns;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import exception.UserException;


public class User {

    public static final String idIsInvalid = "Id inválido";
    public static final String NAME_CANT_BE_EMPTY_NAME = "Hey, acho que você está esquecendo de nos dizer seu nome.";
    public static final String NAME_CANT_BE_HIGHER_THAN_50 = "Hey, acho que você ultrapassou o número de caracteres permitido para o nome, tente novamente.";
    public static final String EMAIL_CANT_BE_EMPTY_EMAIL = "Hey, acho que você está esquecendo de nos dizer seu email.";
    public static final String EMAIL_CANT_BE_HIGHER_THAN_150 = "Hey, acho que você ultrapassou o número de caracteres permitido para email, tente novamente.";
    public static final String INVALID_EMAIL = "Ops, esse e-mail é inválido.";
    public static final String USERNAME_CANT_BE_EMPTY_USERNAME = "Hey, acho que você está esquecendo de nos dizer seu login.";
    public static final String USERNAME_CANT_BE_HIGHER_THAN_100 = "Hey, acho que você ultrapassou o número de caracteres permitido para o login, tente novamente.";
    public static final String PASSWORD_CANT_BE_EMPTY_PASSWORD = "Hey, acho que você está esquecendo de nos dizer sua senha.";
    public static final String PASSWORD_CANT_BE_LESS_THAN_6 = "Hey, acho que vocẽ não atingiu o número mínimo de caracteres.";
    public static final String BIRTH_DATE_CANT_BE_EMPTY = "Hey, acho que você está esquecendo de nos dizer um dia muito especial, a data do seu nascimento.";
    public static final String EMAIL_ARE_NOT_EQUALS = "Ops, E-mails não conferem.";
    public static final String PASSWORD_ARE_NOT_EQUALS = "Ops, as senhas não conferem.";
    public static final String INVALID_BIRTH_DATE = "Ops, essa data é inválida";
    private static final int MAX_LENGTH_NAME = 50;
    private static final int MAX_LENGTH_EMAIL = 150;
    private static final int MAX_LENGTH_USERNAME = 100;
    private static final int MIN_LENGTH_PASSWORD = 6;

    private int idUser;
    private String name;
    private String username;
    private String email;
    private String password;
    private String birthDate;

    public User(int idUser, String name, String birthDate, String email) throws UserException, ParseException {
        setName(name);
        setBirthDate(birthDate);
        setEmail(email);
        setIdUser(idUser);
        setIdUser(idUser);
        setEmail(email);
    }


    public User(String name, String username, String email, String confirmationMail, String password, String confirmationPassword,String birthDate) throws UserException, ParseException {
        setName(name);
        setEmail(email);
        setBirthDate(birthDate);
        setEmail(email);
        setUsername(username);
        setPassword(password);
    }

    private void setIdUser(int idUser) throws UserException {
        this.idUser = idUser;

        if(idUser <= Integer.MAX_VALUE ){
            this.idUser =idUser;
        }else {
            throw new UserException(idIsInvalid);
        }

        if(idUser >= 1 ){
            this.idUser =idUser;
        }else {
            throw new UserException(idIsInvalid);
        }
    }

    private void setName(String name) throws UserException {

        if(!name.isEmpty()){

            if(name.length() <= MAX_LENGTH_NAME){
                this.name = name;
            }else{
                throw new UserException(NAME_CANT_BE_HIGHER_THAN_50);
            }

        }else{
            throw new UserException(NAME_CANT_BE_EMPTY_NAME);
        }
    }

    private  void  setEmail(String email) throws UserException{

        if (!email.isEmpty()) {
            if(email.length() <= MAX_LENGTH_EMAIL){
                CharSequence emailCharSequence = email;
                if(Patterns.EMAIL_ADDRESS.matcher(emailCharSequence).matches()) {
                        this.email = email;
                }
                else{
                    throw new UserException(INVALID_EMAIL);
                }
            }
            else{
                throw  new UserException(EMAIL_CANT_BE_HIGHER_THAN_150);
            }
        }
        else {
            throw  new UserException(EMAIL_CANT_BE_EMPTY_EMAIL);
        }
    }

    private  void  setUsername (String username) throws UserException{

        if (!username.isEmpty()) {
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

    private  void  setPassword (String password) throws UserException{

        if (!password.isEmpty()) {
            if(password.length() >= MIN_LENGTH_PASSWORD) {
                    this.password = password;
            }
            else{
                throw  new UserException(PASSWORD_CANT_BE_LESS_THAN_6);
            }
        }
        else{
            throw  new UserException(PASSWORD_CANT_BE_EMPTY_PASSWORD);
        }

    }

    private void setBirthDate(String birthDate) throws UserException, ParseException {
        if(!birthDate.isEmpty()){
            try {
                SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");
                FORMAT.setLenient(false);
                FORMAT.parse(birthDate);
            }
            catch (ParseException excecao){
                throw new UserException(INVALID_BIRTH_DATE);
            }
        }
        else{
            throw new UserException(BIRTH_DATE_CANT_BE_EMPTY);
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
