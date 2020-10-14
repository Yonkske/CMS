package backend.database;

import backend.database.DbCallerUser;
import backend.database.DbConnector;
import backend.usability.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class UserCallerTest {

    User testUser1 = new User("123", "456", true, false, "hallo", "mensch");
    User testUser2 = new User("123", "neuesPasswort", true, false, "hallo", "mensch");

    @Test
    public void testGetUser() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertNotNull(new DbCallerUser().getUser("admin"));
    } //is the function running without any failures?
/*
    @Test
    public void testInsertUser() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertEquals(true, new DbCallerUser().insertUser(testUser1));
    } //is the function running without any failures?
 */

    @Test
    public void testUpdateUser() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertEquals(true, new DbCallerUser().updateUser(testUser2));
    } //is the function running without any failures?

    @Test
    public void testDeleteUser() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertEquals(true, new DbCallerUser().deleteUser(testUser2));
    } //is the function running without any failures?

    @Test
    public void testCheckUser() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertEquals(true, new DbCallerUser().checkUser("admin", "admin"));
    } //is the function running without any failures?

    @Test
    public void testGetAllUsers() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertNotNull(new DbCallerUser().getAllUsers());
    } //is the function running without any failures?
}
