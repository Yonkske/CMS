package backend.usability;

public class User {
    //private static Object User;
    private static String userName;
    private static String name;
    private static String surName;
    private static String password;
    private static boolean isAdmin;
    private static boolean isInitial;

    /**
     * Its the constructor for this class.
     * @param userName - a username of the future user
     * @param name - a name of the future user
     * @param surname - a surname of the future user
     * @param password - the password of the future user
     * @param isAdmin - specification if the future user is an admin
     * @param isInitial - specification if the password is that the admin gave the future user or if he already changed his password
     */
    public User(String userName, String name, String surname, String password, boolean isAdmin, boolean isInitial) {

    }

    /**
     * Within this method a user can be read out of the database.
     * @param userName - the username of the user which should be got
     * @return User - return of the user that should be got
     */
    public static User getUser(String userName) {

        User existingUser = null;
        return existingUser;
    }

    /**
     * Within this method a new User will be created. That User will be returned afterwards.
     * @param userName - username for the user
     * @param name - the name of the user
     * @param surname - surname of the user
     * @param password - initial password the admin gave that user
     * @param isAdmin - boolean: if true that user is an admin else hes not
     * @return newUser - the returned new user
     */
    public static User create(String userName, String name, String surname, String password, boolean isAdmin) {

        User newUser = new User(userName, name, surname, password, isAdmin, true);

        return newUser;
    }

    /**
     * Within this method the authorisation of that user could be changed.
     * @param userName - username of the user whose authorisation should be changed
     * @param isAdmin - former state of authorisation (expected: false = no admin)
     * @return isAdmin - new state of authorisation (expected: true = admin)
     */
    public static boolean changeAdmin(String userName, boolean isAdmin) {

        return isAdmin;
    }

    /**
     * Within this method it can be checked whether the user is an admin or not
     * @param userName - username of the user whose authorisation should be checked
     * @return true - if true returns he is admin, if false returns he is just a normal user
     */
    public static boolean checkIsAdmin(String userName) {

        return true;
    }

    /**
     * Within this method a user could be deleted from the database.
     * @param userName - username of the user which should be deleted
     * @return true - if true returns the user was deleted, if false returns the user could not be deleted
     */
    public static boolean deleteUser(String userName) {

        return true;
    }

    /**
     * Within this method the admin can give a new password to the user if he forgot his.
     * @param userName - username of the user whose password should be changed
     * @param newPassword - new password given by the admin
     * @return isInitial - if the password was changed by the admin it applies to be an new initial password
     */
    public static boolean changePassword(String userName, String newPassword) {

        return isInitial;
    }

    /**
     * Within this method the user can change his own password.
     * @param userName - username of the user whose password should be changed
     * @param oldPassword - the old password that should be changed
     * @param newPassword - the new password
     * @return true - if true returns the password was changed, if false returns the password could not be changed
     */
    public static boolean changePassword(String userName, String oldPassword, String newPassword) {

        return true;
    }

    /**
     * With this method the user can log out.
     * @return true
     */
    public static boolean logOut() {

        return true;
    }
}
