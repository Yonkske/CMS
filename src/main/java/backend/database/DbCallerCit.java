package backend.database;

import backend.usability.Cit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientException;
import java.util.ArrayList;

public class DbCallerCit extends DbConnector {

    /**
     * Database Query to get the CIT where id= param
     *
     * @param id which should be selected in query
     * @return Object of CIT
     */
    public Cit getCit(int id) throws SQLException {

        String[] sCitArray = new String[7];
        ResultSet rs = stmt.executeQuery("SELECT * FROM CIT WHERE TYPE_ID = " + id);

        rs.first();
        String citName = rs.getString(2);
        for (int i = 0; i < sCitArray.length; i++) {
            sCitArray[i] = rs.getString(i + 3);
        }

        return new Cit(id, citName, sCitArray);
    }


    /**
     * Database Query to create a new CIT
     *
     * @param type which should be created
     * @return true if the CIT is created/ false if not
     */
    public boolean createCit(Cit type) throws SQLException {
        String[] sCitAttributes = type.getCitAttributes();
        try {
            PreparedStatement prepStmt = con.prepareStatement
                    ("INSERT INTO CIT VALUES (?,?,?,?,?,?,?,?,?)"); // SQL Statement

            prepStmt.setInt(1, type.getCitID());
            prepStmt.setString(2, type.getCitName());
            /*
             * Since sCitAttributes[0] is always "Name" and that attribute doesn't get saved
             * in the database the placeholders in the prepared statement are getting filled
             * with the values of sCitAttributes[1] to sCitAttributes[7]
             */
            prepStmt.setString(3, sCitAttributes[1]);
            prepStmt.setString(4, sCitAttributes[2]);
            prepStmt.setString(5, sCitAttributes[3]);
            prepStmt.setString(6, sCitAttributes[4]);
            prepStmt.setString(7, sCitAttributes[5]);
            prepStmt.setString(8, sCitAttributes[6]);
            prepStmt.setString(9, sCitAttributes[7]);
            prepStmt.executeUpdate();
            prepStmt.close();
            return true;
        } catch (SQLNonTransientException c) {
            return false;
        }
    }


    /**
     * Database Query to delete a CIT
     *
     * @param type which should be deleted
     * @return true if the CIT is deleted/ false if not
     */
    public boolean deleteCit(Cit type) throws SQLException {
        try {
            stmt.execute("DELETE FROM CIR WHERE TYPE_ID = " + type.getCitID());
            stmt.execute("DELETE FROM CIT WHERE TYPE_ID = " + type.getCitID());
            return true;
        } catch (SQLNonTransientException c) {
            return false;
        }
    }

    /**
     * Database Query to get all CIT in table
     *
     * @return a list of CIT records in table
     */
    public ArrayList<Cit> getAllCits() throws SQLException {

        ArrayList<Cit> citList = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("Select * From CIT");
        while (rs.next()) {
            /*
             * ReslutSet's index starts at 1. First column in the CIT-table is
             * TYPE_ID, second column is TYPE_NAME and columns 3 to 9 are the
             * attribute names for the CIT
             */
            int citId = rs.getInt(1);
            String citName = rs.getString(2);
            String[] attributes = new String[7];
            for (int i = 0; i < attributes.length; i++) {
                attributes[i] = rs.getString(i + 3);
            }
            citList.add(new Cit(citId, citName, attributes));
        }

        return citList;
    }

    /**
     * Database Query to get sum of all CIT in table
     *
     * @return a integer number of CIT records in table
     */
    public int getCitCount() throws SQLException {
        int iCountCIT;
        try {
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM CIT");
            rs.first();
            iCountCIT = rs.getInt(1);
        } catch (SQLNonTransientException c) {
            iCountCIT = 0;
        }
        return iCountCIT;
    }

    public int getMaxItemId() throws SQLException {
        ResultSet rs = stmt.executeQuery("Select max(TYPE_ID) FROM CIT");

        if (rs.first()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    /**
     * Checks if there is already a CIT with the given typeName
     *
     * @param typeName String - typeName to be checked for
     * @return true if the name is available, false if it is already in use
     */
    public boolean isTypeNameAvailable(String typeName) {
        String query = "SELECT count(*) FROM CIT WHERE TYPE_NAME = '" + typeName + "'";
        try {
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return rs.getInt(1) == 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
