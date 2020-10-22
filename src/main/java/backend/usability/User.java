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
        return new DbCallerUser().getUser(userName);
    }


    /**
     * Within this method the user can change his own password.
     * @param newPassword - the new password
     * @return boolean - if true returns the password was changed, if false returns the password could not be changed
     */
    public void setNewPassword(String newPassword) {
        this.password = newPassword;
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

    public void setIsInitial(boolean newInitial) {
        this.isInitial = newInitial;
    }

    public String getRight() {
        String right;
        if(getIsAdmin()) {
            right = "Admin";
        } else {
         right = "User";
        }
        return right;
    }

}
