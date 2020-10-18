package backend.usability;

import backend.database.DbCallerCir;
import backend.database.DbCallerCit;
import backend.database.DbConnector;

import java.sql.SQLException;

public class Cir {

    // FIXME: change DbCallers to non-static
    private int id;
    private Cit cit;
    private String[] attributes;
    private String name;
    private String type; // TODO: change to Cit

    // TODO: Get rid of it
    /**
     * constructor creates the CIR object
     *
     * @param inAttributes - Sting Array with a length of 10
     */
    @Deprecated
    public Cir(String[] inAttributes) {
        this.attributes = new String[7];

        // TODO: figure out if cit comes as object or String
        id = Integer.parseInt(inAttributes[0]);
        type = inAttributes[1];
        name = inAttributes[2];

        // FIXME: Exception handling
        try {
            new DbConnector().startConnection();
            //Fixme: CIT benötigt Methode zum nt ID augeben über Sting tpye Name (String)
            cit = new DbCallerCit().getCit(Integer.parseInt(type));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 3; i < inAttributes.length; i++) {
            this.attributes[i - 3] = inAttributes[i];
        }
    }

    /**
     * Constructor to create a new instance of CIR
     *
     * @param inId         int - id of the cir
     * @param inType       cit - cit of the record
     * @param inName       String - name of the record
     * @param inAttributes String[] max length 7 - the attribute values of the record
     */
    public Cir(int inId, Cit inType, String inName, String[] inAttributes) {
        id = inId;
        cit = inType;
        name = inName;
        attributes = inAttributes;
    }

    // TODO: Get rid of it
    /**
     * Creates the CIR Obekt and returns the reference
     *
     * @param attributes - Sting Array with a length of 10
     * @return cirName - CIR Objekt
     */
    public static Cir create(String[] attributes) {

        Cir cirName = new Cir(attributes);

        return cirName;
    }

    // TODO: Get rid of it
    /**
     * Returns the CIR object via the CIR ID
     *
     * @param id - ID of the Cir as an integer
     * @return cirName - Cir Objekt
     */
    public static Cir showCir(int id) throws SQLException {

        Cir cirName = new DbCallerCir().getCirById(id);

        return cirName;
    }

    /**
     * Compares if data has changed and transfers the changes to the database with a DBCallerCIR call
     *
     * @param attributes - String Array with a length of 10 of the change CIR
     * @param id         - Iteger id of the CIR
     * @return bTest - True if the CIR has changed or false if it contains the same data
     * @throws SQLException
     */
    public static boolean change(String[] attributes, int id) throws SQLException {
        Cir cirName = showCir(id);
        cirName.name = attributes[2];
        for (int i = 3; i < attributes.length; i++) {
            cirName.attributes[i - 3] = attributes[i];
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
        Cir cirName = showCir(id);
        bCirDeleted = DbCallerCir.deleteCir(cirName);

        return bCirDeleted;
    }

    /**
     * Id of the current CIR Object
     *
     * @return id - CirId
     */
    public int getCirID() {
        return id;
    }

    /**
     * CIT id of the current CIR Objekt
     *
     * @return type - Cit name as String
     */
    public String getCitID() {
        return type;
    }

    /**
     * Returns a string array with the CIR object attributes
     *
     * @return attrubute - Cir Attribites in a String Array
     */
    public String[] getCirAttributes() {
        return attributes;
    }

    /**
     * Returns the current name of the CIR object
     *
     * @return name - CirName as String
     */
    public String getCirName() {
        return name;
    }

    /**
     * This method is only used for testing and should never be used otherwise
     *
     * @param id - int of the new Cir ID
     */
    public void setCirID(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the associated CIT
     *
     * @return String - name of the CIT
     */
    public String getCitName() {
        return this.cit.getCitName();
    }

    /**
     * Returns the the associated CIT
     *
     * @return Cit - the CIT associated with this cir
     */
    public Cit getCit() {
        return this.cit;
    }

}
