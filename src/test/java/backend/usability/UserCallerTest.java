package backend.usability;

import backend.database.DbCallerUser;
import backend.database.DbConnector;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class UserCallerTest {

    User testUser1 = new User("123", "456", true, false, "hallo", "mensch");
    User testUser2 = new User("123", "neuesPasswort", true, false, "hallo", "mensch");

    @Test
    public void testGetUser() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertNotNull(DbCallerUser.getUser("admin"));
    } //is the function running without any failures?

    @Test
    public void testInsertUser() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertEquals(true, DbCallerUser.insertUser(testUser1));
    } //is the function running without any failures?

    @Test
    public void testUpdateUser() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertEquals(true, DbCallerUser.updateUser(testUser2));
    } //is the function running without any failures?

    @Test
    public void testDeleteUser() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertEquals(true, DbCallerUser.deleteUser(testUser2));
    } //is the function running without any failures?
}
