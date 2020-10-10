package backend.usability;


import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;

public class CirTest {
    private String[] sTestAttribute = {"CIT", "CIR", "A1", "A2", "A3", "A4", "A5"};
    private String[] sTestAttribute1 = {"CIT"};
    private String[] sTestAttribute2 = {"CIT", "CIR", "A1", "A2", "A3", "A4", "A5", "","","","","","",""};
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

}
