// Autor: Ion Tabyrca
package backend.usability;

import backend.database.DbCallerCit;

public class Cit {

    private int id;
    private String cit_bezeichnung;
    private String[] attributes;


    public Cit(int cit_ID, String[] attributeList){
        id = cit_ID;                                            //id wird über Konstruktor zugeteilt
        attributes = new String[8];                             // Erzeugen des Attribute-Arrays
        cit_bezeichnung = attributeList[0];                     //Die Bezeichnung des CIT steht an 2. Stelle im Array
        attributes[1] = "Name";                                 //Das Attribut Name gibt es in jedem CIT


        for (int i = 2; i <= attributeList.length; i++) {       //Alle anderen Attribute werden über den Übergabeparameter weitergegeben.
            this.attributes[i] = attributeList[i-1];
            System.out.println(attributes[i]);                  //Hilfestellung zur überprüfung der Attribute
        }

    }


    public Cit show(int id){
        return DbCallerCit.getCit(id);                          //Methode des DBCallerCit wird aufgerufen um Eintrag zu holen.

    }

    public boolean delete(int id){
        return DbCallerCit.deleteCit(id);                       //Methode des DBCallerCit wird aufgerufen um Eintrag zu löschen.
    }

    public static Cit[] showAll(){                              //Methode des DBCallerCit wird aufgerufen um alle Einträge zu zeigen.
        return DbCallerCit.getAllCits();
    }

    public static int getCount(){                               //Methode des DBCallerCit wird aufgerufen Anzahl an Einträgen zu zeigen.
        return DbCallerCit.getCount();
    }

}
