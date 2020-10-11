package backend.usability;

import backend.database.DbCallerCir;
import backend.database.DbConnector;
import javafx.css.converter.StringConverter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Cir {

    // FIXME: change DbCallers to non-static
  private int id;
  private Cit cit;
  private String[] attribute;
  private String name;
  private String type; // TODO: change to Cit

    /**
     * constructor creates the CIR object
     *
     * @param attributes - Sting Array with a length of 10
     */
    public Cir(String[] attributes){
        attribute = new String[7];

        // TODO: figure out if cit comes as object or String
        id =  Integer.parseInt(attributes[0]);
        type = attributes[1];
        name = attributes[2];

        for(int i=3;i<attributes.length;i++)
        {
            attribute[i-3] = attributes[i];
        }
    }

    /**
     * Creates the CIR Obekt and returns the reference
     *
     * @param attributes - Sting Array with a length of 10
     * @return cirName - CIR Objekt
     *
     * */
    public static Cir create(String[] attributes){

        Cir cirName = new Cir(attributes);

        return cirName;
    }

    /**
     * Returns the CIR object via the CIR ID
     *
     * @param id - ID of the Cir as an integer
     * @return cirName - Cir Objekt
     */
    public static Cir showCir(int id) throws SQLException {

        Cir cirName = DbCallerCir.getCirById(id);

        return cirName;
    }

    /**
     * Compares if data has changed and transfers the changes to the database with a DBCallerCIR call
     *
     * @param attributes - String Array with a length of 10 of the change CIR
     * @param id - Iteger id of the CIR
     * @return bTest - True if the CIR has changed or false if it contains the same data
     * @throws SQLException
     */
    public static boolean change(String[] attributes, int id) throws SQLException {

        boolean bTest; //Variablen für die Methode
        Cir cirName = showCir(id); //Über die ID das alte Cir aus der Datenbank holen
        cirName.name = attributes[2];
        for(int i=3;i<attributes.length;i++)
        {
            cirName.attribute[i-3] = attributes[i];
        }


        return new DbCallerCir().updateCir(cirName);
    }

    /**
     * deleting the CIR object from the database, needs the CIR ID
     *
     * @param id - id of the CIR Objekt
     * @return bCirDeleted - Boolean Deleted or not
     * @throws SQLException
     */
    public static boolean delete(int id) throws SQLException {

        boolean bCirDeleted;
        Cir cirName = showCir(id);  //zu löschendes CIR auslesen
        bCirDeleted = DbCallerCir.deleteCir(cirName);     // zu löschendes CIR an den DBCallerCIR übergeben

        return bCirDeleted;
    }

    public static ArrayList<Cir> getAllForType(Cit citType) {
        // todo:Anpassen der Rückgabe und der Methode
       ArrayList<Cir> CirListe = new ArrayList<Cir>();
        // Methode getAllCirForType aus DBCallerCIR
        //CirListe = DbCallerCir.getAllCirForType(citType);  // Liste aller Cir zu CIT

        return null;
    }

    public static int getCount() throws SQLException {
        int iCountAllCir;

        iCountAllCir = DbCallerCir.getCirCount();

        return iCountAllCir;
    }

    public int getCountForType(Cit citType){
        int iAnzahlCit = 0;
        //todo: Anpassen der Methode und des Rückgabewertes
        //iAnzahlCit = DbCallerCir.getCirCountForType(citType);

        return iAnzahlCit;
    }

    /**
     *
     * @return id - CirId
     */
    public int getCirID()
    {
        return id;
    }

    /**
     *
     * @return type - Cit name as String
     */
    public String getCitID()
    {
        return type;
    }

    /**
     *
     * @return attrubute - Cir Attribites in a String Array
     */
    public String[] getCirAttributes()
    {
        return attribute;
    }

    /**
     *
     * @return name - CirName as String
     */
    public String getCirName()
    {
        return name;
    }

    /**
     * This method is only used for testing and should never be used otherwise
     * @param id - int of the new Cir ID
     */
    public void setCirID(int id)
    {
        this.id = id;
    }

} //Cir
