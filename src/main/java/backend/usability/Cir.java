package backend.usability;

public class Cir {

  private int id;
  private Cit cit;
  private String[] attribute;
  private String sCirName;
  private String sCitName;


    /**
     *
     * Kontruktor of the class Cir
     * @param attributes
     */
    public Cir(String[] attributes){
        attribute = new String[7];

        // TODO: figure out if cit comes as object or String
        sCitName = attributes[0];
        sCirName = attributes[1];

        for(int i=2;i<attributes.length;i++)
        {
            attribute[i-2] = attributes[i];
        }
    }

    /**
     * Beschreibung der Methode
     *
     * @param attributes mit allen Cir Attributen aus dem Frontend.
     * @return Cir Objekt.
     *
     * */
    public static Cir create(String[]attributes){

        Cir cirName = new Cir(attributes);

        return cirName;
    }

    /**
     * Returns the CIR object via the CIR ID
     *
     * @param id as an int
     * @return cir Objekt
     */
    public Cir showCir(int id){
        // todo: did the class DbCallerCir have a Method with the name getCirbyID
      //  Cir cirName = DbCallerCir.getCirbyID(id);

        return null;
    }

    public boolean change(String[] attributes, int id){
        //Variablen für die Methode
        boolean btest= false;
        //Über die ID das alte Cir aus der Datenbank holen todo: DbCallerCir Methode testen
        Cir cirName = showCir(id);
        // Neuse CIR mit den vielleicht geänderten Daten erstellen todo: DbCallerCir Methode testen
        Cir cirVerName = create(attributes);
        // Prüfen ob sich das alte und neue Cir unterscheiden und fals ja das neue in die DB schreiben
        if(cirName.equals(cirVerName)) {
            btest = false;

        }
        else{
            // todo: did the class DbCallerCir have a Method with the name updateCir
          //  btest = DBCallerCIR.updateCir(cirVerName);

        }

        return btest;
    }

    public boolean delete(int id){
        boolean bCirDeleted = false;
        //zu löschendes CIR aus DBCallerCIR auslesen todo: DbCallerCir Methode testen
        //Cir cirName = showCir(id);

        // zu löschendes CIR an den DBCallerCIR übergeben todo: DbCallerCir Methode testen
        // bCirDeleted = DBCallerCIR.deleteCir(cirName);


        return bCirDeleted;
    }




    // Nur Test Methode für jUnit
    public  int additionTest( int i, int i1) {
        return i1 + i;

    }

} //Cir
