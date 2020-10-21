package org.dhbw;

import backend.database.DbCallerCir;
import backend.database.DbCallerCit;
import backend.database.DbCallerUser;
import backend.usability.User;
import org.jasypt.util.password.StrongPasswordEncryptor;

public abstract class Controller {

    final DbCallerCir DB_CALLER_CIR;
    final DbCallerCit DB_CALLER_CIT;
    final DbCallerUser DB_CALLER_USER;
    static User user;

    /**
     * The Controller Constructor.
     */
    public Controller() {

        DB_CALLER_USER = new DbCallerUser();
        DB_CALLER_CIR = new DbCallerCir();
        DB_CALLER_CIT = new DbCallerCit();

    }

    /**
     * encrypts a password.
     * @param password the password in cleartext.
     * @return the encrypted password.
     */
    String encryptPassword(String password) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.encryptPassword(password);
    }

    /**
     * checks if a cleartext passwort matches an encrypted password.
     * @param inputPassword the cleartext pw.
     * @param encryptedStoredPassword the encrypted
     * @return true | false depending on if they match or not.
     */
    boolean checkPassword(String inputPassword, String encryptedStoredPassword) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.checkPassword(inputPassword, encryptedStoredPassword);
    }
}
