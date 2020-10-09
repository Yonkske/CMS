package backend.database;


import backend.usability.User;

import java.util.ArrayList;

public class DbCallerUser extends DbConnector{

    /**
     * Method for the query to get the user out if the database.
     * @param userName - this user should be got
     * @return userToGet - returns the user from the database
     */
    public User getUser(String userName) {


        return null;
    }

    /**
     * Method to write a new user into the database.
     * @param userToCreate - this user should be saved
     * @return boolean - if true: user was saved, if false: user can´t be saved
     */
    public boolean insertUser(User userToCreate) {

        return false;
    }

    /**
     * Method to update a user being saved in the database.
     * @param userToUpdate - this user should be updated
     * @return boolean - if true: user was updated, if false: user can´t be updated
     */
    public boolean updateUser(User userToUpdate) {

        return false;
    }

    /**
     * Method to delete user from the database.
     * @param userToDelete - this user should be deleted
     * @return boolean - if true: user was deleted, if false: user can´t be deleted
     */
    public boolean deleteUser(User userToDelete) {

        return false;
    }

    public ArrayList<User> getAllUsers() {

        return null;
    }
}
