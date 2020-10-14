package backend.database;

import backend.usability.Cir;
import backend.usability.Cit;
import com.sun.javafx.scene.layout.region.Margins;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.io.Console;
import java.sql.*;
import java.util.ArrayList;

public class DbCallerCit extends DbConnector{

    /**Database Query to get the CIT where id= param
     *
     * @param id which should be selected in query
     * @return Object of CIT
     */
    public Cit getCit(int id) throws SQLException{

        String[] sCitArray = new String[10]; // String zum Speichern der Resultset Daten
        ResultSet rs = stmt.executeQuery("SELECT * FROM CIT WHERE TYPE_ID = " + id); // SQL Abfrage

        rs.first();
       for(int i= 0; i < sCitArray.length; i++) // Übertragen des Result Sets auf ein Array
        {
            sCitArray[i] = rs.getString(i+2);
        }

        Cit citName; // initialisieren eines neuen Cit's
        citName = new Cit(id, sCitArray); // Erzeugen des Cit's
        return citName; // Rückgabe des Cir's
    }



    /**Database Query to create a new CIT
     *
     * @param type which should be created
     * @return true if the CIT is created/ false if not
     */
    public boolean createCit(Cit type) throws SQLException{
        String[] sCitAttributes = type.getCitAttributes();
        boolean bWorks;
        try{
            PreparedStatement prepStmt = con.prepareStatement
                    ("INSERT INTO CIT VALUES (?,?,?,?,?,?,?,?,?,?)"); // SQL Statement

            prepStmt.setInt(1,type.getCitID());
            prepStmt.setString(3,type.getCitName());
            prepStmt.setString(4,sCitAttributes[0]);
            prepStmt.setString(5,sCitAttributes[1]);
            prepStmt.setString(6,sCitAttributes[2]);
            prepStmt.setString(7,sCitAttributes[3]);
            prepStmt.setString(8,sCitAttributes[4]);
            prepStmt.setString(9,sCitAttributes[5]);
            prepStmt.setString(10,sCitAttributes[6]);
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




    /**Database Query to delete a CIT
     *
     * @param type which should be deleted
     * @return true if the CIT is deleted/ false if not
     */
    public boolean deleteCit(Cit type) throws SQLException{
        boolean bDeleteCit;
        try{
            stmt.execute("DELETE FROM CIT WHERE Type_ID= "+ type.getCitID()); // SQL Abfrage
            bDeleteCit = true;
        }
        catch(SQLSyntaxErrorException a)
        {
            bDeleteCit = false;
        }
        catch(SQLIntegrityConstraintViolationException b)
        {
            bDeleteCit = false;
        }
        catch (SQLNonTransientException c)
        {
            bDeleteCit = false;
        }

        return bDeleteCit;
    }

    /**Database Query to get all CIT in table
     *
     * @return a list of CIT records in table
     */
    public ArrayList<Cit> getAllCits() throws SQLException{
        int iIDCit;                                                         //deklaration von der ID des CIT
        new DbConnector().startConnection(); // Warum???!                   // Connection zur DB
        ArrayList<Cit>  citListe= new ArrayList<Cit>();                     // deklaration einer neuen Liste mit CIT's
        ResultSet rs = stmt.executeQuery("Select * From CIT");          // deklaration eines Resultsets rs und initialisierung mit query
        while(rs.next())                                                    // Schleife zur weiterzählung des Resultsets.
        {
            iIDCit = rs.getInt(1);                               // ID des ResultSet
            String[] attributes = new String[9];
            for(int i = 0; i < attributes.length; i++) {
                attributes[i] = rs.getString(i+1);
            }
            citListe.add(new Cit(iIDCit, attributes)); // CIT Objekt in Liste eintragen

        }

        return citListe;
    }

    public String[] getCitNamelist() throws SQLException {
        final ObservableList citNameList = FXCollections.observableArrayList();
        ResultSet rs = stmt.executeQuery("Select TYPE_NAME from CIT");
        String[] citNames = new String[getCitCount()];
        while(rs.next()){
            for(int i = 0; i<getCitCount(); i++ ){
                citNameList.add(rs.getString("TYPE_NAME"));
                System.out.println(citNameList);
            }
        }
        stmt.close();
        rs.close();
        return citNames;


    }

    /**Database Query to get sum of all CIT in table
     *
     * @return a integer number of CIT records in table
     */
    public int getCitCount() throws SQLException {
        int iCountCIT;
        try{
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM CIT");
            rs.first();
            iCountCIT = rs.getInt(1);
        }
        catch (SQLSyntaxErrorException a)
        {
            iCountCIT = 0;
        }
        catch (SQLIntegrityConstraintViolationException b)
        {
            iCountCIT = 0;
        }
        catch (SQLNonTransientException c)
        {
            iCountCIT = 0;
        }
        return iCountCIT;
    }

    public static int converterNametoID(String name) throws SQLException{
        ResultSet rs = stmt.executeQuery("Select TYPE_ID from CIT where TYPE_NAME='"+name+"'");
        return rs.getInt(0);
    }
}
