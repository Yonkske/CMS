package backend.usability;

import backend.database.DbCallerUser;

public class User {

    private final String userName;
    private String name;
    private String surname;
    private String password;
    private boolean isAdmin;
    private boolean isInitial;

    /**
     * Its the constructor for this class.
     * @param userName - a username of the future user
     * @param name - a name of the future user
     * @param surname - a surname of the future user
     * @param password - the password of the future user
     * @param isAdmin - specification if the future user is an admin
     * @param isInitial - specification if the password is that the admin gave the future user or if he already changed his password
     */
    public User(String userName, String password, boolean isInitial, boolean isAdmin, String name, String surname) {
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isInitial = isInitial;
    }

    /**
     * Within this method a user can be read out of the database.
     * @param userName - the username of the user which should be got
     * @return User - return of the user that should be got
     */
    public static User getUser(String userName) {

        User existingUser = DbCallerUser.getUser(userName);
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
    public static User create(String userName, String password, boolean isAdmin, String name, String surname) {

        User newUser = new User(userName, name, true, isAdmin, surname, password);
        DbCallerUser.insertUser(newUser);

        return newUser;
    }

    /**
     * Within this method the authorisation of that user could be changed.
     * @param userName - username of the user whose authorisation should be changed
     * @param isAdmin - former state of authorisation (expected: false = no admin)
     * @return isAdmin - new state of authorisation (expected: true = admin)
     */
    public static boolean changeAdmin(String userName, boolean isAdmin) {

        User userToChange = getUser(userName);
        userToChange.isAdmin = isAdmin;

        DbCallerUser.updateUser(userToChange);

        return userToChange.isAdmin;
    }

    /**
     * Within this method it can be checked whether the user is an admin or not.
     * @param userName - username of the user whose authorisation should be checked
     * @return true - if true returns he is admin, if false returns he is just a normal user
     */
    public boolean checkIsAdmin(String userName) {

        User userToCheckAdmin = getUser(userName);

        return userToCheckAdmin.isAdmin;
    }

    /**
     * Within this method a user could be deleted from the database.
     * @param userName - username of the user which should be deleted
     * @return true - if true returns the user was deleted, if false returns the user could not be deleted
     */
    public boolean deleteUser(String userName) {

        User userToDelete = getUser(userName);

        if (DbCallerUser.deleteUser(userToDelete)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Within this method the admin can give a new password to the user if he forgot his.
     * @param userName - username of the user whose password should be changed
     * @param newPassword - new password given by the admin
     * @return boolean - if the password was changed: true, else: false
     */
    public boolean changePassword(String userName, String newPassword) {

        User userToChangePwByAdmin = getUser(userName);

        if (userToChangePwByAdmin.userName == userName) {
            userToChangePwByAdmin.password = newPassword;
            userToChangePwByAdmin.isInitial = true;

            DbCallerUser.updateUser(userToChangePwByAdmin);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Within this method the user can change his own password.
     * @param userName - username of the user whose password should be changed
     * @param oldPassword - the old password that should be changed
     * @param newPassword - the new password
     * @return boolean - if true returns the password was changed, if false returns the password could not be changed
     */
    public boolean changePassword(String userName, String oldPassword, String newPassword) {
    // TODO: functionality when oldPassword != userToChangePw.password

        User userToChangePw = getUser(userName);

        if (userToChangePw.userName == userName & userToChangePw.password == oldPassword) {
            userToChangePw.password = newPassword;

            DbCallerUser.updateUser(userToChangePw);
            return true;
        } else {
            return false;
        }

    }

    public String getUserName() {
        return this.userName;
    }

    public String getName() {
        return this.name;
    }

    public String getSurName() {
        return this.surname;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public boolean getIsInitial() {
        return this.isInitial;
    }

    /**
     * With this method the user can log out.
     * @return true
     */
    public boolean logOut() {

        return true;
    }
}
