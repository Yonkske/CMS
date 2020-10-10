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
    // Testen über eine Set Methode ob der insert funktioneirt
    @Test
    public void insertCir() throws SQLException {
        new DbConnector().startConnection();
        Cir cirTest = new DbCallerCir().getCirById(1);
        cirTest.setCirID(3);
        Assert.assertTrue(new DbCallerCir().insertCir(cirTest));
    }
    @Test
    // Testen was passiert wenn es die CIR ID schon gibt
    public void insertCir1() throws SQLException {
        new DbConnector().startConnection();
        Cir cirTest = new DbCallerCir().getCirById(1);
        Assert.assertFalse(new DbCallerCir().insertCir(cirTest));
    }
    @Test
    // Testen was passiert wenn es die Cir ID gar nicht gibt
    public void insertCir2() throws SQLException {
        try{
            new DbConnector().startConnection();
            Cir cirTest = new DbCallerCir().getCirById(10000);
            Assert.assertTrue(new DbCallerCir().insertCir(cirTest));
        }
        catch(SQLNonTransientException a){
            Assert.assertFalse(false);
        }
    }
}
