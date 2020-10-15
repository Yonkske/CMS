package org.dhbw;

import backend.database.DbCallerCir;
import backend.database.DbCallerCit;
import backend.database.DbCallerUser;
import backend.database.DbConnector;
import backend.usability.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;

public class Controller {

    final DbCallerCir DB_CALLER_CIR;
    final DbCallerCit DB_CALLER_CIT;
    final DbCallerUser CB_CALLER_USER;
    static User user;

    public Controller () {

        try {
            new DbConnector().startConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CB_CALLER_USER = new DbCallerUser();
        DB_CALLER_CIR = new DbCallerCir();
        DB_CALLER_CIT = new DbCallerCit();

    }

    public String encryptPassword(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];

        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        String hash = String.valueOf(factory.generateSecret(spec).getEncoded());
        return hash;
    }
}
