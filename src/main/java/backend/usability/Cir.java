package backend.usability;

import backend.database.DbCallerCir;
import backend.database.DbConnector;
import javafx.css.converter.StringConverter;

import java.sql.SQLException;
import java.util.ArrayList;

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
     *
     * @param attributes - String Array with a length of 10 of the change CIR
     * @param id - Iteger id of the CIR
     * @return
     * @throws SQLException
     */
    public boolean change(String[] attributes, int id) throws SQLException {
        //Variablen für die Methode
        boolean bTest= false;
        //Über die ID das alte Cir aus der Datenbank holen
        Cir cirName = showCir(id);
        // Neuse CIR mit den vielleicht geänderten Daten erstellen
        Cir cirVerName = create(attributes);
        // Prüfen ob sich das alte und neue Cir unterscheiden und fals ja das neue in die DB schreiben
        if(cirName.equals(cirVerName)) {
            bTest = false;

        }
        else{
            // todo: did the class DbCallerCir have a Method with the name updateCir
          //  bTest = DBCallerCIR.updateCir(cirVerName);

        }

        return bTest;
    }

    public boolean delete(int id){
        boolean bCirDeleted = false;
        //zu löschendes CIR aus DBCallerCIR auslesen todo: DbCallerCir Methode testen
        //Cir cirName = showCir(id);

        // zu löschendes CIR an den DBCallerCIR übergeben todo: DbCallerCir Methode testen
        // bCirDeleted = DBCallerCIR.deleteCir(cirName);


        return bCirDeleted;
    }

    public static ArrayList<Cir> getAllForType(Cit citType) {
        // todo:Anpassen der Rückgabe und der Methode
       ArrayList<Cir> CirListe = new ArrayList<Cir>();
        // Methode getAllCirForType aus DBCallerCIR
        //CirListe = DbCallerCir.getAllCirForType(citType);  // Liste aller Cir zu CIT

        return null;
    }

    public static int getCount(){
        int iAnzahlCir = 0;
        //todo: Anpassen der Metode und des Rückgabewertes
        //iAnzahlCir = DBCallerCIR.getCirCount();

        return iAnzahlCir;
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