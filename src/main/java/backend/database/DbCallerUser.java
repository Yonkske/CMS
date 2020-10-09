package backend.database;

import org.h2.engine.User;

public class DbCallerUser {

    /**
     * Method for the query to get the user out if the database.
     * @param userName - this user should be got
     * @return userToGet - returns the user from the database
     */
    public static backend.usability.User getUser(String userName) {

        backend.usability.User userToGet = null;
        return userToGet;
    }

    /**
     * Method to write a new user into the database.
     * @param userToCreate - this user should be saved
     * @return boolean - if true: user was saved, if false: user can´t be saved
     */
    public static boolean createUser(backend.usability.User userToCreate) {

        return true;
    }

    /**
     * Method to update a user being saved in the database.
     * @param userToUpdate - this user should be updated
     * @return boolean - if true: user was updated, if false: user can´t be updated
     */
    public static boolean updateUser(backend.usability.User userToUpdate) {

        return true;
    }

    /**
     * Method to delete user from the database.
     * @param userToDelete - this user should be deleted
     * @return boolean - if true: user was deleted, if false: user can´t be deleted
     */
    public static boolean deleteUser(backend.usability.User userToDelete) {

        return true;
    }

}
