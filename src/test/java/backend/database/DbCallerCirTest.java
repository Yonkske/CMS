package backend.database;

import backend.usability.Cir;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;

import javax.management.RuntimeErrorException;
import java.sql.*;
import java.sql.SQLException;

public class DbCallerCirTest {
    boolean bTest;
    @Test
    public void getCirById () throws SQLException {
       new DbConnector().startConnection();
        Assert.assertNotNull(new DbCallerCir().getCirById(1));
    }

    @Test
    public void getCirById1 () throws SQLException {
        new DbConnector().startConnection();
        try {
            new DbCallerCir().getCirById(0);
            bTest = false;
        } catch (final SQLNonTransientException e) {
            bTest = true;
        }
        Assert.assertTrue(bTest);
    }
    @Test
    public void getCirById2 () throws SQLException {
        try {
            new DbCallerCir().getCirById(20000);
            bTest = false;
        } catch (final SQLNonTransientException e) {
            bTest = true;
        }
        Assert.assertTrue(bTest);
    }
}
