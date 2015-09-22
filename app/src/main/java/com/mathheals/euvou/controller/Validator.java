package com.mathheals.euvou.controller;

import android.widget.EditText;

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

}
