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

    public Controller() {

        DB_CALLER_USER = new DbCallerUser();
        DB_CALLER_CIR = new DbCallerCir();
        DB_CALLER_CIT = new DbCallerCit();

    }

    String encryptPassword(String password) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.encryptPassword(password);
    }

    boolean checkPassword(String inputPassword, String encryptedStoredPassword) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.checkPassword(inputPassword, encryptedStoredPassword);
    }
}
