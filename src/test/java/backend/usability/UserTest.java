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
//        Assert.assertNotNull();
    } //is the function running without any failures?

    @Test
    public void testCreate() throws SQLException {
        new DbConnector().startConnection();
        Assert.assertNotNull(new User("creation", "password", true, false, "", ""));
    } //is the function running without any failures?


}
