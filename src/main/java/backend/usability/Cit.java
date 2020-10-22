package backend.usability;

public class Cit {

    // FIXME: change DbCallers to non-static
    private int id;
    private String typename;
    private String[] attributes;

    /**
     * Constructor to create a new instance of CIT
     *
     * @param citId           int - the id of the CIT
     * @param citName         String - the name
     * @param inputAttributes String[] max length 7 - the attributes for the CIT
     */
    public Cit(int citId, String citName, String[] inputAttributes) {
        id = citId;
        attributes = new String[8];
        typename = citName;
        attributes[0] = "Name";


        for (int i = 0; i < inputAttributes.length; i++) {
            this.attributes[i + 1] = inputAttributes[i];
        }
    }

    /**
     * Just a methode to return a list of attributes
     *
     * @return a list of attributes
     */
    public String[] getCitAttributes() {
        return attributes;
    }

    /**
     * Just a methode to return the ID of CIT
     *
     * @return ID of CIT
     */
    public int getCitID() {
        return id;
    }

    /**
     * Just a methode to return the name of CIT
     *
     * @return name of CIT
     */
    public String getCitName() {
        return typename;
    }

    @Override
    public String toString() {
        return typename;
    }

}

