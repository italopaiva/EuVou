package com.mathheals.euvou.controller.utility;
import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.widget.EditText;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by julliana on 21/09/15.
 */
public class Validator {
    public static boolean hasPasswordSizeValid(String password) {
        boolean isValid = true;

        if (!(password.length() >= 6)) {
            isValid = false;
        }

        return isValid;
    }

    public static boolean hasPasswordConfirmationValid(String password, String confirmPassword) {
        boolean isValid = true;

        if (confirmPassword.compareTo(password) != 0) {
            isValid = false;
        }

        return isValid;
    }

    public static boolean isPasswordValid(String password, EditText editPassword, String confirmPassword, EditText confirmPasswordField) {
        boolean isValid = true;

        if (!hasPasswordSizeValid(password)) {
            editPassword.requestFocus();
            editPassword.setError("Para segurança da sua conta informe seis caracteres para sua senha");
            isValid = false;
        }

        else if(!hasPasswordConfirmationValid(password, confirmPassword)){
            confirmPasswordField.requestFocus();
            confirmPasswordField.setError("Ops, as senhas não conferem");
            isValid = false;
        }

        return isValid;
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public final static boolean isMailValid(String mail) {
        boolean isValid = true;
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            isValid = false;
        }

        return isValid;
    }

    public final static boolean isMailConfirmationValid(String mail, String confirmMail) {
        boolean isValid = true;
        if(mail.compareTo(confirmMail) != 0) {
            isValid = false;
        }

        return isValid;
    }

    public final static boolean isMailValidAndConfirmated(String mail, EditText mailField, EditText confirmMailField, String confirmMail) {
        boolean isValid=true;
        if(!isMailValid(mail)){
            mailField.requestFocus();
            mailField.setError("Ops, e-mail inválido");
            isValid=false;
        }

        else if(!isMailConfirmationValid(mail, confirmMail)){
            confirmMailField.requestFocus();
            confirmMailField.setError("Ops, os e-mails não conferem");
            isValid=false;
        }

        return isValid;
    }

    public static boolean isDataValida(String data, EditText dateField) {
        boolean isValid = true;
        SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        try {
            FORMAT.setLenient(false);
            FORMAT.parse(data);
        } catch (ParseException excecao) {
            dateField.setError("Ops, essa data é inválida");
            dateField.setFocusable(true);
            dateField.requestFocus();
            isValid = false;
        }

        return isValid;
    }

    public static boolean isNameEmpty(String name){
        boolean isEmpty = false;
        if (TextUtils.isEmpty(name)) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public static boolean isDateEmpty(String date){
        boolean isEmpty = false;
        if (TextUtils.isEmpty(date)) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public static boolean isMailEmpty(String mail){
        boolean isEmpty = false;
        if (TextUtils.isEmpty(mail)) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public static boolean isConfirmMailEmpty(String confirmMail){
        boolean isEmpty = false;
        if (TextUtils.isEmpty(confirmMail)) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public static boolean isLoginEmpty(String login){
        boolean isEmpty = false;
        if (TextUtils.isEmpty(login)) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public static boolean isPasswordEmpty(String password){
        boolean isEmpty = false;
        if (TextUtils.isEmpty(password)) {
            isEmpty=true;
        }
        return isEmpty;
    }

    public static boolean isConfirmPasswordEmpty(String confirmPassword){
        boolean isEmpty = false;
        if (TextUtils.isEmpty(confirmPassword)) {
            isEmpty=true;
        }
        return isEmpty;
    }

    public static boolean isEmptyFields(String name, EditText nameField, String date, EditText dateField, String mail, EditText mailField, String confirmMail,
                                        EditText confirmMailField, String login, EditText loginField, String password, EditText passwordField, String confirmPassword, EditText confirmPasswordField) {
        boolean isEmpty = false;
        if (isNameEmpty(name)) {
            nameField.requestFocus();
            nameField.setError("Hey, acho que você está esquecendo de nos dizer seu nome");
            isEmpty = true;
        }
        if (isDateEmpty(date)) {
            dateField.requestFocus();
            dateField.setError("Hey, acho que você está esquecendo de nos dizer sua data de nascimento");
            isEmpty = true;
        }
        if (isMailEmpty(mail)) {
            mailField.requestFocus();
            mailField.setError("Hey, acho que você está esquecendo de nos dizer seu e-mail");
            isEmpty = true;
        }
        if (isConfirmMailEmpty(confirmMail)) {
            confirmMailField.requestFocus();
            confirmMailField.setError("Você deve confirmar seu e-mail digitando-o novamente");
            isEmpty = true;
        }
        if (isLoginEmpty(login)) {
            loginField.requestFocus();
            loginField.setError("Hey, acho que você está esquecendo de nos dizer seu login");
            isEmpty = true;
        }
        if (isPasswordEmpty(password)) {
            passwordField.requestFocus();
            passwordField.setError("Você deve escolher uma senha pra sua conta");
            isEmpty = true;
        }

        if (isConfirmMailEmpty(confirmPassword)) {
            confirmPasswordField.requestFocus();
            confirmPasswordField.setError("Você deve confirmar sua senha digitando-a novamente");
            isEmpty = true;
        }

        return isEmpty;
    }

}