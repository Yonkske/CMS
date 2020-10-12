package backend.database;

import backend.usability.Cit;

import java.util.ArrayList;

public class DbCallerCit extends DbConnector{

    /**Database Query to get the CIT where id= param
     *
     * @param id which should be selected in query
     * @return Object of CIT
     */
    public Cit getCit(int id) {

        return null;
    }

    /**Database Query to create a new CIT
     *
     * @param type which should be created
     * @return true if the CIT is created/ false if not
     */
    public boolean createCit(Cit type) {

        return false;
    }

    /**Database Query to delete a CIT
     *
     * @param type which should be deleted
     * @return true if the CIT is deleted/ false if not
     */
    public boolean deleteCit(Cit type) {

        return false;
    }

    /**Database Query to get all CIT in table
     *
     * @return a list of CIT records in table
     */
    public ArrayList<Cit> getAllCits() {

        return null;
    }

    /**Database Query to get sum of all CIT in table
     *
     * @return a integer number of CIT records in table
     */
    public int getCitCount() {

        return 0;
    }

}
