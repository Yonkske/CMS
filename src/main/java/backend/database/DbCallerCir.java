package backend.database;

import backend.usability.Cir;
import backend.usability.Cit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbCallerCir extends DbConnector {

    /**
     * Writes a new CIR into the database, requires a CIR object
     *
     * @param cirToInsert Cir - the CIR to be inserted
     * @return bWorks - Boolean
     * @throws SQLException - on database access error or other errors
     */
    public boolean insertCir(Cir cirToInsert) {
        String[] attributes = cirToInsert.getCirAttributes();
        boolean successful;
        try {
            PreparedStatement prepStmt = con.prepareStatement
                    ("INSERT INTO CIR VALUES (?,?,?,?,?,?,?,?,?,?)");

            prepStmt.setInt(1, cirToInsert.getCirID());
            prepStmt.setInt(2, cirToInsert.getCit().getCitID());
            prepStmt.setString(3, cirToInsert.getCirName());

            for (int i = 0; i < attributes.length; i++) {
                prepStmt.setString(i + 4, attributes[i]);
            }

            prepStmt.executeUpdate();
            prepStmt.close();
            successful = true;
        } catch (SQLException e) {
            successful = false;
        }

        return successful;
    }

    /**
     * Update a CIR in the database, requires a CIR object and returns a boolean value
     *
     * @param cirToUpdate - CirObjekt
     * @return bUpdateCir - Boolean with true/false
     * @throws SQLException - on database access error or other errors
     */
    public boolean updateCir(Cir cirToUpdate) {
        boolean successful;
        String[] attributes = cirToUpdate.getCirAttributes();
        try {
            PreparedStatement prepStmt = con.prepareStatement
                    ("UPDATE CIR SET RECORD_NAME = ?," +
                            "ATTRIBUTE_VALUE_1 = ?," +
                            "ATTRIBUTE_VALUE_2 = ?," +
                            "ATTRIBUTE_VALUE_3 = ?," +
                            "ATTRIBUTE_VALUE_4 = ?," +
                            "ATTRIBUTE_VALUE_5 = ?," +
                            "ATTRIBUTE_VALUE_6 = ?," +
                            "ATTRIBUTE_VALUE_7 = ? " +
                            "WHERE ITEM_ID =" + cirToUpdate.getCirID());

            prepStmt.setString(1, cirToUpdate.getCirName());

            for (int i = 0; i < attributes.length; i++) {
                prepStmt.setString(i + 2, attributes[i]);
            }

            prepStmt.executeUpdate();
            prepStmt.close();
            successful = true;
        } catch (SQLException c) {
            successful = false;
        }

        return successful;
    }

    /**
     * Deletes the CIR object from the database, needs the CIR Objekt
     *
     * @param cirName - CIR Objekt
     * @return bDeleteCir - boolean
     * @throws SQLException - on database access error or other errors
     */
    static public boolean deleteCir(Cir cirName) {
        boolean successful;
        try {
            stmt.execute("DELETE FROM CIR WHERE ITEM_ID = " + cirName.getCirID());
            successful = true;
        } catch (SQLException c) {
            successful = false;
        }

        return successful;
    }

    /**
     * The number of all CIR's
     *
     * @return iCirCount - int CIR Count of all Cir's
     * @throws SQLException - on database access error or other errors
     */
    public int getCirCount() {
        int iCirCount;
        try {
            ResultSet rs = stmt.executeQuery("SELECT count(ITEM_ID) FROM CIR");
            rs.first();
            iCirCount = rs.getInt(1);
        } catch (SQLException c) {
            iCirCount = 0;
        }

        return iCirCount;
    }

    /**
     * The Number of all Cir's from a specific Cit
     *
     * @param type which should
     * @return count of Cir form a specific CIT
     * @throws SQLException - on database access error or other errors
     */
    public int getCirCountForType(Cit type) {
        int iCountCIRofCIT;
        try {
            ResultSet rs = stmt.executeQuery("SELECT count(ITEM_ID) FROM CIR WHERE TYPE_ID =" + type.getCitID());
            rs.first();
            iCountCIRofCIT = rs.getInt(1);
        } catch (SQLException c) {
            iCountCIRofCIT = 0;
            c.printStackTrace();
        }
        return iCountCIRofCIT;

    }

    /**
     * Get the highest item_id from the database
     *
     * @return the highest item_id if there are entries, 0 if there are no entires in the db
     * @throws SQLException - on database access error or other errors
     */
    public int getMaxItemId() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("Select max(ITEM_ID) FROM CIR");
            rs.first();
            return rs.getInt(1);
        } catch (SQLException throwables) {
            return 0;
        }

    }

    /**
     * Gets all CIRs from the database
     *
     * @return ArrayList containing all CIRs
     */
    public ArrayList<Cir> getRecords() {
        String query = "SELECT * FROM CIR R JOIN CIT T on T.TYPE_ID = R.TYPE_ID";

        return getCirs(query);
    }

    /**
     * Returns all CIRs as specified in the query
     *
     * @param query String - sql query
     * @return ArrayList containing all CIRs returned by the query
     */
    private ArrayList<Cir> getCirs(String query) {
        ArrayList<Cir> records = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                // Create the CIT
                int typId = rs.getInt("TYPE_ID");
                String typeName = rs.getString("TYPE_NAME");

                String[] attributeNames = new String[7];
                for (int i = 0; i < attributeNames.length; i++) {
                    attributeNames[i] = rs.getString("ATTRIBUTE_NAME_" + (i + 1));
                }

                Cit type = new Cit(typId, typeName, attributeNames);

                // Create the CIR
                int recordId = rs.getInt("ITEM_ID");
                String recordName = rs.getString("RECORD_NAME");

                String[] attributeValues = new String[7];
                for (int i = 0; i < attributeValues.length; i++) {
                    attributeValues[i] = rs.getString("ATTRIBUTE_VALUE_" + (i + 1));
                }

                records.add(new Cir(recordId, type, recordName, attributeValues));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

}
