package backend.database;

import backend.usability.Cir;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;

import javax.management.RuntimeErrorException;
import java.sql.*;
import java.sql.SQLException;

public class DbCallerCirTest {
    private String[] sTestAttribute = {"1", "1","Samsung-Drucker","Samsung","Samsung-322", "Ja", "127.0.0.1", "206",
            "Stuttgart","Koenigsstrasse"};
    private String[] sTestAttribute1 = {"2", "1","Samsung-Drucker","Samsung","Samsung-321", "Ja", "192.193.194.1",
            "4711","Köln","Rheinufer"};
    private String[] sTestAttribute2 = {"3", "1","Samsung-Drucker","Samsung","Samsung-321", "Ja", "192.193.194.1",
            "4711","Köln"};
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
    @Test
    // updateCir funktioniert das Update mit den Test Daten
    public  void updateCir() throws SQLException{
        new DbConnector().startConnection();
        Cir cirTest = new Cir(sTestAttribute);
        Assert.assertTrue(new DbCallerCir().updateCir(cirTest));

    }
    @Test
    // updateCir was wenn Cir mit nicht allen Attributen übergeben wird
    //erzeugt werden
    public  void updateCir1() throws SQLException{
        new DbConnector().startConnection();
        Cir cirTest = new Cir(sTestAttribute2);
        Assert.assertTrue(new DbCallerCir().updateCir(cirTest));

    }
    @Test
    // updateCir was passiert wenn die gleichen Daten geupdatet werden
    public void updateCir2() throws SQLException{
        new DbConnector().startConnection();
        Cir cirTest = new Cir(sTestAttribute1);
        Assert.assertTrue(new DbCallerCir().updateCir(cirTest));
    }




}
