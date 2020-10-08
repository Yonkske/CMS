package backend.database;

import org.h2.engine.User;

public class DbCallerUser {

    /**
     * Method for the query to get the user out if the database.
     * @param userName - this user should be got
     * @return userToGet - returns the user from the database
     */
    public static User getUser (String userName) {

        User userToGet = null;
        return userToGet;
    }

    /**
     * Method to write a new user into the database.
     * @param user - this user should be saved
     * @return boolean - if true: user was saved, if false: user can´t be saved
     */
    public static boolean createUser(User user) {

        return true;
    }

    /**
     * Method to update a user being saved in the database.
     * @param user - this user should be updated
     * @return boolean - if true: user was updated, if false: user can´t be updated
     */
    public static boolean updateUser(User user) {

        return true;
    }

    /**
     * Method to delete user from the database.
     * @param user - this user should be deleted
     * @return boolean - if true: user was deleted, if false: user can´t be deleted
     */
    public static boolean deleteUser(User user) {

        return true;
    }

}
