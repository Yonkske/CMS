//Autor: Ion Tabyrca
package backend.usability;

import backend.database.DbCallerCit;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cit {

    // FIXME: change DbCallers to non-static
    private int id;
    private String typename;
    private String[] attributes;

    /** Constructor to create an object of CIT
     *
     * @param cit_ID is the current id of the CIT
     * @param attributeList is the list of attributes which are defined by admin
     */
    public Cit(int cit_ID, String[] attributeList){
        id = cit_ID;                                            //id wird über Konstruktor zugeteilt
        attributes = new String[8];                             // Erzeugen des Attribute-Arrays
        typename = attributeList[0];                     //Die Bezeichnung des CIT steht an 2. Stelle im Array
        attributes[0] = "Name";                                 //Das Attribut Name gibt es in jedem CIT


        for (int i = 1; i < attributeList.length; i++) {       //Alle anderen Attribute werden über den Übergabeparameter weitergegeben.
            this.attributes[i] = attributeList[i];
            }
    }

    /**
     * Shows the CIT of the given ID
     *
     * @param id of the CIT to be shown
     * @return the CIT matching the id
     */
    public Cit show(int id) throws SQLException { return new DbCallerCit().getCit(id);}                            //Methode des DBCallerCit wird aufgerufen um Eintrag zu holen.

    /**
     *Delete the CIT of the given ID
     *
     * @param citToDelete - the CIT to be deleted
     * @return boolean either it was successfully or not
     */
    public boolean delete(Cit citToDelete) throws SQLException { return new DbCallerCit().deleteCit(citToDelete);}                    //Methode des DBCallerCit wird aufgerufen um Eintrag zu löschen

    /**
     *Shows all CIT
     *
     * @return a list of CIT which are stored in the database
     */
    public static ArrayList<Cit> showAll() throws SQLException {                              //Methode des DBCallerCit wird aufgerufen um alle Einträge zu zeigen.
        return new DbCallerCit().getAllCits();
    }

    /**
     *Get the number of records in the CIT table
     *
     * @return a number of records which are stored in the database
     */
    public static int getCount() throws SQLException { return new DbCallerCit().getCitCount(); }                        //Methode des DBCallerCit wird aufgerufen Anzahl an Einträgen zu zeigen.

    /**  Just a methode to return a list of attributes
     *
     * @return a list of attributes
     */
    public String[] getCitAttributes(){
        return attributes;

    }

    /** Just a methode to return the ID of CIT
     *
     * @return ID of CIT
     */
    public int getCitID(){

        return id;
    }

    /** Just a methode to return the name of CIT
     *
     * @return name of CIT
     */
    public String getCitName(){
        return typename;

    }

    public String toString() {
        return typename;
    }

    @Override
    public String toString() {
        return cit_bezeichnung;
    }

}

