package backend.usability;

public class User {
    private static Object User;
    private static String userName;
    private static String name;
    private static String surName;
    private static String password;
    private static boolean isAdmin;
    private static boolean isInitial;

    /**
     * @param userName
     * @param name
     * @param surname
     * @param password
     * @param isAdmin
     * @param isInitial
     */
    public User(String userName, String name, String surname, String password, boolean isAdmin, boolean isInitial) {

    }

    /**
     * @param userName
     * @return User
     */
    public static Object getUser(String userName) {
        
        return User;
    }

    /**
     * @param userName
     * @param name
     * @param surname
     * @param password
     * @param isAdmin
     * @return User
     */
    public static Object create(String userName, String name, String surname, String password, boolean isAdmin) {

        User = new User(userName, name, surname, password, isAdmin, true);

        return User;
    }

    /**
     * @param userName
     * @param isAdmin
     * @return isAdmin
     */
    public static boolean changeAdmin(String userName, boolean isAdmin) {

        return isAdmin;
    }

    /**
     * @param userName
     * @return true
     */
    public static boolean checkIsAdmin(String userName) {

        return true;
    }

    /**
     * @param userName
     * @return true
     */
    public static boolean deleteUser(String userName) {

        return true;
    }

    /**
     * @param userName
     * @param newPassword
     * @return true
     */
    public static boolean changePassword(String userName, String newPassword) {

        return true;
    }

    /**
     * @param userName
     * @param oldPassword
     * @param newPassword
     * @return true
     */
    public static boolean changePassword(String userName, String oldPassword, String newPassword) {

        return true;
    }

    /**
     * @return true
     */
    public static boolean logOut() {

        return true;
    }
}
