package backend.usability;


import backend.database.DbConnector;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.SQLNonTransientException;

public class CirTest {
    private String[] sTestAttribute = {"1", "1","CIR name", "A1", "A2", "A3", "A4", "A5","A6","A7"};
    private String[] sTestAttribute1 = {"1"};
    private String[] sTestAttribute2 = {"1", "1", "A1", "A2", "A3", "A4", "A5", "","","","","","",""};
    private String[] sTestAttribute3 = {"2", "1","Samsung-Drucker","Samsung","Samsung-321", "Ja", "192.193.194.1",
            "4711","Köln","Rheinufer"};
    private String[] sTestAttribute4 = {"2", "1","HP-Drucker","HP","HP-123", "Ja", "192.193.194.1",
            "205","Stuttgart","Koenigsstrasse"};
    private  String[] sTestAttribute5 = {"1", "1","HP-Drucker","HP","HP-123", "Ja", "192.193.194.1",
            "205","Stuttgart","Koenigsstrasse"};
    private Cir cirTest = new Cir(sTestAttribute);

    // Methode Create, liefert nicht null zurück
    @Test
    public void testCreate(){

        Assert.assertNotNull(new Cir(sTestAttribute).create(sTestAttribute));
    }
    // Methode Create, Sting Array zu klein, liefert ein Fehler
    @Test
    public void testCreate1(){
        try {
            cirTest = Cir.create(sTestAttribute1);
        } catch (final RuntimeException e) {
            Assert.assertTrue(true);
        }
    }
    // Methode Create, String Array zu groß, wirft einen Fehler weil das Konstruktor Array nur 8 Strings fasst
    @Test
    public void testCreate2(){
        boolean btest = false;
        try {
            cirTest = Cir.create(sTestAttribute2);
            btest = true;

        } catch (final RuntimeException e) {
            btest = false;
        }
        Assert.assertFalse(btest);
    }
    @Test
    // Zeige Objekt eines bestehenden Cir's
    public void  showTest() throws SQLException {
        new DbConnector().startConnection();
        int iTest= 1;
        Assert.assertNotNull(Cir.showCir(iTest));
    }
    @Test
    // Test auf ein nicht bestehendes Objekt
    public void  showTest1() throws SQLException {
        new DbConnector().startConnection();
        int iTest= 100;
        boolean btest;
        try{
            cirTest =  Cir.showCir(iTest);
            btest= true;
        }
        catch(SQLNonTransientException e)
        {
            btest=false;
        }
        Assert.assertFalse(btest);
    }
    @Test
    // Test mit einer viel zu hohen id
    public void  showTest2() throws SQLException {
        new DbConnector().startConnection();
        int iTest= 10000;
        boolean btest;
        try{
            cirTest =  Cir.showCir(iTest);
            btest= true;
        }
        catch(SQLNonTransientException e)
        {
            btest=false;
        }
        Assert.assertFalse(btest);
    }
   @Test
    // Test Cir Change ändern in der Datenbank wenn sich ein CIR nicht verändert hat
    public void change() throws SQLException{
       Assert.assertTrue(Cir.change(sTestAttribute5,1));
   }
   @Test
    //Test CIR Change ändern in Datenbak wenn sich ein CIR verändert hat
   public void change1() throws SQLException{
       Assert.assertTrue(Cir.change(sTestAttribute4,2));
   }
    //Test CIR Cange wenn es die ID nicht gibt
    public void change2() throws SQLException{
        Assert.assertFalse(Cir.change(sTestAttribute4,2000));
    }


}
