package backend.database;


import backend.usability.User;

import java.sql.*;
import java.util.ArrayList;

public class DbCallerUser extends DbConnector{

    static ResultSet rs;

    /**
     * Method for the query to get the user out if the database.
     * @param userName - this user should be got
     * @return userToGet - returns the user from the database
     */
    public User getUser(String userName) {

        try {
            rs = stmt.executeQuery("SELECT * FROM USER WHERE USER_NAME = '" + userName + "'");
            rs.first();
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
    public boolean insertUser(User userToCreate) {

        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?)");

            statement.setString(1, userToCreate.getUserName());
            statement.setString(2, userToCreate.getPassword());
            statement.setBoolean(3, userToCreate.getIsInitial());
            statement.setBoolean(4, userToCreate.getIsAdmin());
            statement.setString(5, userToCreate.getName());
            statement.setString(6, userToCreate.getSurName());

            statement.execute();
            statement.close();

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
    public boolean updateUser(User userToUpdate) {

        try {
            PreparedStatement statement = con.prepareStatement("UPDATE USER SET PASSWORD = ?, IS_INITIAL = ?, IS_ADMIN = ?, NAME = ?, SURNAME = ? WHERE USER_NAME = ?");

            statement.setString(1, userToUpdate.getPassword());
            statement.setBoolean(2, userToUpdate.getIsInitial());
            statement.setBoolean(3, userToUpdate.getIsAdmin());
            statement.setString(4, userToUpdate.getName());
            statement.setString(5, userToUpdate.getSurName());
            statement.setString(6, userToUpdate.getUserName());

            statement.execute();
            statement.close();
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
    public boolean deleteUser(User userToDelete) {

        String userName = userToDelete.getUserName();
        try {
            stmt.execute("DELETE FROM USER WHERE USER_NAME = '" + userName + "'");

            return true;
        } catch (SQLException deleteFailed){
            deleteFailed.printStackTrace();

            return false;
        }
    }

    public boolean checkUser(String userName, String password) {

        try {
            rs = stmt.executeQuery("SELECT * FROM USER WHERE USER_NAME = '" + userName + "' AND PASSWORD = '" + password + "'");
            return rs.next();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Method gives a list of all Users in database
     * @return
     */
    /*public ArrayList<User> getAllUsers() {
    // TODO: make it work

        User[] allUsers;

        try {
            rs = stmt.executeQuery("SELECT * FROM USER");


        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return allUsers;
    }*/
}
