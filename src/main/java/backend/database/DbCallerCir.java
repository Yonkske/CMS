package backend.database;

import backend.usability.Cir;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class DbCallerCir extends DbConnector{

    /**
     * Reads the CIR object from the database, needs the CIR ID
     *
     * @param id - Int ID of the CIR's
     * @return cirName - the CIR Objekt
     * @throws SQLException
     */
    public static Cir getCirById(int id) throws SQLException {

        String[] sCirArray = new String[10]; // String zum Speichern der Resultset Daten
        ResultSet rs = stmt.executeQuery("SELECT * FROM CIR WHERE ITEM_ID = " + id); // SQL Abfrage

        rs.first();
        for(int i= 0; i <= 9; i++) // Übertragen des Result Sets auf ein Array
        {
            sCirArray[i] = rs.getString(i+1);
        }

        Cir cirName; // initialisieren eines neuen Cir's
        cirName = Cir.create(sCirArray); // Erzeugen des Cir's
        return cirName; // Rückgabe des Cir's
    }

    /**
     * Writes a new CIR into the database, requires a CIR object
     *
     * @param cirName - Type Cir
     * @return bWorks - Boolean
     * @throws SQLException
     */
    public boolean insertCir(Cir cirName) throws SQLException {
        String[] sCirAttributes = cirName.getCirAttributes();
        boolean bWorks;
        try{
            PreparedStatement prepStmt = con.prepareStatement
                    ("INSERT INTO CIR VALUES (?,?,?,?,?,?,?,?,?,?)"); // SQL Statement

            prepStmt.setInt(1,cirName.getCirID());
            prepStmt.setString(2, cirName.getCitID()); //todo: change String to CIT type
            prepStmt.setString(3,cirName.getCirName());
            prepStmt.setString(4,sCirAttributes[0]);
            prepStmt.setString(5,sCirAttributes[1]);
            prepStmt.setString(6,sCirAttributes[2]);
            prepStmt.setString(7,sCirAttributes[3]);
            prepStmt.setString(8,sCirAttributes[4]);
            prepStmt.setString(9,sCirAttributes[5]);
            prepStmt.setString(10,sCirAttributes[6]);
            prepStmt.executeUpdate();
            prepStmt.close();
            bWorks = true;
        }
        catch(SQLSyntaxErrorException a)
        {
            bWorks = false;
        }
        catch (SQLIntegrityConstraintViolationException b)
        {
            bWorks = false;
        }


        return bWorks;
    }

    /**
     * Update a CIR in the database, requires a CIR object and returns a boolean value
     *
     * @param cirName - CirObjekt
     * @return bUpdateCir - Boolean with true/false
     * @throws SQLException
     */
    public boolean updateCir(Cir cirName) throws SQLException  {
        boolean bUpdateCir;
        String[] sCirAttributes = cirName.getCirAttributes();
        try{
            PreparedStatement prepStmt = con.prepareStatement
                    ("UPDATE CIR SET RECORD_NAME = ?," +
                            "ATTRIBUTE_VALUE_1 = ?," +
                            "ATTRIBUTE_VALUE_2 = ?," +
                            "ATTRIBUTE_VALUE_3 = ?," +
                            "ATTRIBUTE_VALUE_4 = ?," +
                            "ATTRIBUTE_VALUE_5 = ?," +
                            "ATTRIBUTE_VALUE_6 = ? ," +
                            "ATTRIBUTE_VALUE_7 = ?" +
                            "WHERE ITEM_ID ="+ cirName.getCirID()); // SQL Statement

            prepStmt.setString(1,cirName.getCirName());
            prepStmt.setString(2,sCirAttributes[0]);
            prepStmt.setString(3,sCirAttributes[1]);
            prepStmt.setString(4,sCirAttributes[2]);
            prepStmt.setString(5,sCirAttributes[3]);
            prepStmt.setString(6,sCirAttributes[4]);
            prepStmt.setString(7,sCirAttributes[5]);
            prepStmt.setString(8,sCirAttributes[6]);
            prepStmt.executeUpdate();
            prepStmt.close();
            bUpdateCir = true;
        }
        catch(SQLSyntaxErrorException a)
        {
            bUpdateCir = false;
        }
        catch (SQLIntegrityConstraintViolationException b)
        {
            bUpdateCir = false;
        }

        return bUpdateCir;
    }

    public boolean deleteCir() {

        return false;
    }

    public ArrayList<Cir> getAllCirsForType(Cir record) {

        return null;
    }

    public int getCirCount() {

        return 0;
    }

    public int getCirCountForType(Cir type) {

        return 0;
    }
}
