package backend.usability;

import backend.database.DbConnector;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class UserTest {

    User testUser1 = new User("creation", "password", true, false, "", "");

    @Test
    public void testGetUser() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertNotNull(User.getUser("123"));
    } //is the function running without any failures?

    @Test
    public void testCreate() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertNotNull(User.create("creation", "password", false, "", ""));
    } //is the function running without any failures?

    @Test
    public void testChangePw() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertEquals(true, User.changePassword("creation", "true"));
    } //is the function running without any failures?

}
