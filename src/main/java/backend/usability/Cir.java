package backend.usability;

public class Cir {

    private int id;
    private Cit cit;
    private String[] attributes;
    private String name;

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

    /**
     * Id of the current CIR Object
     *
     * @return id - CirId
     */
    public int getCirID() {
        return id;
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

    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Sets the attributes of the CIR
     *
     * @param newAttributes String[] length 7 with the new attributes
     */
    public void setAttributes(String[] newAttributes) {
        this.attributes = newAttributes;
    }

}
