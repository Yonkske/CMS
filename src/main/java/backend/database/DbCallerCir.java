package backend.database;

import backend.usability.Cir;
import backend.usability.Cit;

import java.sql.*;
import java.util.ArrayList;

public class DbCallerCir extends DbConnector {

    /**
     * Reads the CIR object from the database, needs the CIR ID
     *
     * @param id - Int ID of the CIR's
     * @return cirName - the CIR Objekt
     * @throws SQLException - on database access error or other errors
     */
    public Cir getCirById(int id) throws SQLException {

        String[] sCirArray = new String[10]; // String zum Speichern der Resultset Daten
        ResultSet rs = stmt.executeQuery("SELECT * FROM CIR WHERE ITEM_ID = " + id); // SQL Abfrage

        rs.first();
        for (int i = 0; i <= 9; i++) // Übertragen des Result Sets auf ein Array
        {
            sCirArray[i] = rs.getString(i + 1);
        }

        Cir cirName; // initialisieren eines neuen Cir's
        cirName = Cir.create(sCirArray); // Erzeugen des Cir's
        return cirName; // Rückgabe des Cir's
    }

    /**
     * Writes a new CIR into the database, requires a CIR object
     *
     * @param cirToInsert - Type Cir
     * @return bWorks - Boolean
     * @throws SQLException - on database access error or other errors
     */
    public boolean insertCir(Cir cirToInsert) throws SQLException {
        String[] attributes = cirToInsert.getCirAttributes();
        boolean successful;
        try {
            PreparedStatement prepStmt = con.prepareStatement
                    ("INSERT INTO CIR VALUES (?,?,?,?,?,?,?,?,?,?)"); // SQL Statement

            prepStmt.setInt(1, cirToInsert.getCirID());
            prepStmt.setString(2, cirToInsert.getCitID());
            prepStmt.setString(3, cirToInsert.getCirName());

            for(int i = 0; i < attributes.length; i++) {
                prepStmt.setString(i+2, attributes[i]);
            }

            prepStmt.executeUpdate();
            prepStmt.close();
            successful = true;
        } catch (SQLNonTransientException c) {
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
    public boolean updateCir(Cir cirToUpdate) throws SQLException {
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
                            "ATTRIBUTE_VALUE_7 = ?" +
                            "WHERE ITEM_ID =" + cirToUpdate.getCirID()); // SQL Statement

            prepStmt.setString(1, cirToUpdate.getCirName());

            for(int i = 0; i < attributes.length; i++) {
                prepStmt.setString(i+2, attributes[i]);
            }

            prepStmt.executeUpdate();
            prepStmt.close();
            successful = true;
        } catch (SQLNonTransientException c) {
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
    static public boolean deleteCir(Cir cirName) throws SQLException {
        boolean bDeleteCir;
        try {
            stmt.execute("DELETE FROM CIR WHERE ITEM_ID = " + cirName.getCirID()); // SQL Abfrage
            bDeleteCir = true;
        } catch (SQLNonTransientException c) {
            bDeleteCir = false;
        }

        return bDeleteCir;
    }

    /**
     * The number of all CIR's
     *
     * @return iCirCount - int CIR Count of all Cir's
     * @throws SQLException - on database access error or other errors
     */
    public int getCirCount() throws SQLException {
        int iCirCount;
        try {
            ResultSet rs = stmt.executeQuery("SELECT count(ITEM_ID) FROM CIR");
            rs.first();
            iCirCount = rs.getInt(1);
        } catch (SQLNonTransientException c) {
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
    public int getCirCountForType(Cit type) throws SQLException {
        int iCountCIRofCIT;
        try {
            ResultSet rs = stmt.executeQuery("SELECT count(ITEM_ID) FROM CIR WHERE TYPE_ID =" + type.getCitID());
            rs.first();
            iCountCIRofCIT = rs.getInt(1);
        } catch (SQLNonTransientException c) {
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
    public int getMaxItemId() throws SQLException {
        startConnection();
        ResultSet rs = stmt.executeQuery("Select max(ITEM_ID) FROM CIR");

        if (rs.first()) {
            return rs.getInt(1);
        } else {
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
     * Gets all CIRs containing the searchValue in either the type name or
     * record name
     *
     * @param searchValue String - value that should be searched for
     * @return ArrayList containing all CIRs matching the search criteria
     */
    public ArrayList<Cir> getRecords(String searchValue) {
        String query = "SELECT * FROM CIR R JOIN CIT T on T.TYPE_ID = R.TYPE_ID"
                + " WHERE (LOWER(R.RECORD_NAME) LIKE LOWER('%" + searchValue
                + "%') OR LOWER(T.TYPE_NAME) LIKE LOWER('%" + searchValue + "%'))";

        return getCirs(query);
    }

    /**
     * Gets all CIRs containing the searchValue in either the type name or
     * the record name and matching the filterType
     *
     * @param searchValue String - value that should be searched for
     * @param filterType  Cit - type that should be filtered on
     * @return ArrayList containing all CIRs matching the search criteria
     */
    public ArrayList<Cir> getRecords(String searchValue, Cit filterType) {
        String query = "SELECT * FROM CIR R JOIN CIT T on T.TYPE_ID = R.TYPE_ID WHERE R.TYPE_ID = "
                + filterType.getCitID() + " AND (LOWER(R.RECORD_NAME) LIKE LOWER('%"
                + searchValue + "%') OR LOWER(T.TYPE_NAME) LIKE LOWER('%" + searchValue + "%'))";

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
                for (int i = 13; i <= rs.getMetaData().getColumnCount(); i++) {
                    attributeNames[i - 13] = rs.getString(i);
                }

                Cit type = new Cit(typId, typeName, attributeNames);

                // Create the CIT
                int recordId = rs.getInt("ITEM_ID");
                String recordName = rs.getString("RECORD_NAME");

                String[] attributeValues = new String[7];
                for (int i = 0; i < attributeValues.length; i++) {
                    attributeValues[i] = rs.getString(i + 4);
                }

                records.add(new Cir(recordId, type, recordName, attributeValues));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

}
