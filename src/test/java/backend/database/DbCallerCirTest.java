package backend.database;

import backend.usability.Cir;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;
import java.sql.*;
import java.sql.SQLException;

public class DbCallerCirTest {
    int id = 1;
    @Test
    public void getCirById () throws SQLException {
       new DbConnector().startConnection();
        Assert.assertNotNull(new DbCallerCir().getCirById(id));
    }
    public void getCirById1 () throws SQLException {


    }
    public void getCirById2 () throws SQLException {


    }
}
