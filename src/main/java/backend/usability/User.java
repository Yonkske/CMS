package backend.usability;

public class User {
    private static Object User;
    private static String userName;
    private static String name;
    private static String surName;
    private static String password;
    private static boolean isAdmin;
    private static boolean isInitial;

    public static Object getUser(String userName) {
        
        return User;
    }

    public static Object create(String userName, String name, String surname, String password, boolean isAdmin) {

        return User;
    }

    public static boolean changeAdmin(String userName, boolean isAdmin) {

        return isAdmin;
    }

    public static boolean checkIsAdmin(String userName) {

        return true;
    }

    public static boolean deleteUser(String userName) {

        return true;
    }

    public static boolean changePassword(String userName, String newPassword) {

        return true;
    }

    public static boolean changePassword(String userName, String oldPassword, String newPassword) {

        return true;
    }

    public static boolean logOut() {

        return true;
    }
}
