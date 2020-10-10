package backend.database;


import backend.usability.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbCallerUser extends DbConnector{

    static ResultSet rs;

    /**
     * Method for the query to get the user out if the database.
     * @param userName - this user should be got
     * @return userToGet - returns the user from the database
     */
    public static User getUser(String userName) {

        try {
            rs = stmt.executeQuery("SELECT * FROM USER WHERE USER_NAME = " + userName + ";");
            userName = rs.getString("USER_NAME");
            String password = rs.getString("PASSWORD");
            boolean isInitial = rs.getBoolean("IS_INITIAL");
            boolean isAdmin = rs.getBoolean("IS_ADMIN");
            String name = rs.getString("NAME");
            String surname = rs.getString("SURNAME");

            User userToGet = new User(userName, password, isInitial, isAdmin, name, surname);

            return userToGet;
        } catch (SQLException getFailed) {
            getFailed.printStackTrace();
            return null;
        }
    }

    /**
     * Method to write a new user into the database.
     * @param userToCreate - this user should be saved
     * @return boolean - if true: user was saved, if false: user can´t be saved
     */
    public static boolean insertUser(User userToCreate) {

        String userName = userToCreate.getUserName();
        String password = userToCreate.getPassword();
        boolean isInitial = userToCreate.getIsInitial();
        boolean isAdmin = userToCreate.getIsAdmin();
        String name = userToCreate.getName();
        String surname = userToCreate.getSurName();

        try {
            stmt.execute("INSERT INTO USER VALUES ("
                            + userName + ", "
                            + password + ", "
                            + isInitial + ", "
                            + isAdmin + ", "
                            + name + ", "
                            + surname + ";");
            return true;
        } catch (SQLException creationFailed) {
            creationFailed.printStackTrace();
            return false;
        }
    }

    /**
     * Method to update a user being saved in the database.
     * @param userToUpdate - this user should be updated
     * @return boolean - if true: user was updated, if false: user can´t be updated
     */
    public static boolean updateUser(User userToUpdate) {

        String userName = userToUpdate.getUserName();
        String password = userToUpdate.getPassword();
        boolean isInitial = userToUpdate.getIsInitial();
        boolean isAdmin = userToUpdate.getIsAdmin();
        String name = userToUpdate.getName();
        String surname = userToUpdate.getSurName();

        try {
            stmt.execute("UPDATE USER SET ( USER_NAME ="
                    + userName + ", PASSWORD = "
                    + password + ", IS_INITIAL = "
                    + isInitial + ", IS_ADMIN = "
                    + isAdmin + ", NAME = "
                    + name + ", SURNAME = "
                    + surname + ";");
            return true;
        } catch (SQLException updateFailed) {
            updateFailed.printStackTrace();
            return false;
        }
    }

    /**
     * Method to delete user from the database.
     * @param userToDelete - this user should be deleted
     * @return boolean - if true: user was deleted, if false: user can´t be deleted
     */
    public static boolean deleteUser(User userToDelete) {

        String userName = userToDelete.getUserName();
        try {
            stmt.execute("DELETE FROM USER WHERE USER_NAME = " + userName + ";");
        } catch (SQLException deleteFailed){
            deleteFailed.printStackTrace();
        }
        return false;
    }

    public ArrayList<User> getAllUsers() {

        return null;
    }
}
