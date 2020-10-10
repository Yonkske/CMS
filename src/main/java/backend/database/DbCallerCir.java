package backend.database;

import backend.usability.Cir;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbCallerCir extends DbConnector{

    /**
     *
     * @param id - Int ID of the CIR's
     * @return cirName - the CIR Objekt
     * @throws SQLException
     */
    public Cir getCirById(int id) throws SQLException {

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

    public boolean insertCir() {

        return false;
    }

    public boolean updateCir() {

        return false;
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
