//Autor: Ion Tabyrca
package backend.usability;

import backend.database.DbCallerCit;

import java.util.ArrayList;

public class Cit {

    // FIXME: change DbCallers to non-static
    private int id;
    private String cit_bezeichnung;
    private String[] attributes;

    /** Constructor to create an object of CIT
     *
     * @param cit_ID is the current id of the CIT
     * @param attributeList is the list of attributes which are defined by admin
     */
    public Cit(int cit_ID, String[] attributeList){
        id = cit_ID;                                            //id wird über Konstruktor zugeteilt
        attributes = new String[8];                             // Erzeugen des Attribute-Arrays
        cit_bezeichnung = attributeList[0];                     //Die Bezeichnung des CIT steht an 2. Stelle im Array
        attributes[1] = "Name";                                 //Das Attribut Name gibt es in jedem CIT

        for (int i = 2; i <= attributeList.length; i++) {       //Alle anderen Attribute werden über den Übergabeparameter weitergegeben.
            this.attributes[i] = attributeList[i-1];
            System.out.println(attributes[i]);                  //Hilfestellung zur Überprüfung der Attribute
        }
    }

    /**
     * Shows the CIT of the given ID
     *
     * @param id of the CIT to be shown
     * @return the CIT matching the id
     */
    public Cit show(int id){ return new DbCallerCit().getCit(id);}                            //Methode des DBCallerCit wird aufgerufen um Eintrag zu holen.

    /**
     *Delete the CIT of the given ID
     *
     * @param citToDelete - the CIT to be deleted
     * @return boolean either it was successfully or not
     */
    public boolean delete(Cit citToDelete){ return new DbCallerCit().deleteCit(citToDelete);}                    //Methode des DBCallerCit wird aufgerufen um Eintrag zu löschen

    /**
     *Shows all CIT
     *
     * @return a list of CIT which are stored in the database
     */
    public static ArrayList<Cit> showAll(){                              //Methode des DBCallerCit wird aufgerufen um alle Einträge zu zeigen.
        return new DbCallerCit().getAllCits();
    }

    /**
     *Get the number of records in the CIT table
     *
     * @return a number of records which are stored in the database
     */
    public static int getCount(){                               //Methode des DBCallerCit wird aufgerufen Anzahl an Einträgen zu zeigen.
        return new DbCallerCit().getCitCount();
    }

}
