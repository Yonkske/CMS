package backend.database;

import java.sql.*;

public class DbConnector {

    private static Connection con;
    private final String FILE_NAME = "CMS.db";
    private final String COMPLETE_PATH = System.getProperty("user.home") + "\\CMSdb\\" + FILE_NAME;
    private final String URL = "jdbc:h2:file:\\" + COMPLETE_PATH + ";";

    /**
     * Starts the connection to the integrated h2-database
     *
     * @throws SQLException
     */
    public void startConnection() throws SQLException {

        con = DriverManager.getConnection(URL, "sa", "");
    }

    /**
     * Initializes the database and creates all the necessary tables
     *
     * @throws SQLException
     */
    private void initializeDb() throws SQLException{

        createTableCit();
        createTableCir();
        createTableUser();
    }

    /**
     * Creates the CIT-Table in the database
     * 
     * @throws SQLException
     */
    private void createTableCit() throws SQLException{
        String query = "Create Table CIT "
                + "(Type_ID int NOT NULL, "
                + "Type_Name varchar(255) NOT NULL, "
                + "Attribute_Name_1 varchar(255), "
                + "Attribute_Name_2 varchar(255), "
                + "Attribute_Name_3 varchar(255), "
                + "Attribute_Name_4 varchar(255), "
                + "Attribute_Name_5 varchar(255), "
                + "Attribute_Name_6 varchar(255), "
                + "Attribute_Name_7 varchar(255), "
                + "PRIMARY KEY (Type_ID))";

        Statement stmt = con.createStatement();
        stmt.execute(query);
    }

    /**
     * Creates the CIR-table in the database
     * 
     * @throws SQLException
     */
    private void createTableCir() throws SQLException{
        String query = "Create Table CIR "
                + "(Item_ID int NOT NULL, "
                + "Type_ID int NOT NULL, "
                + "Record_Name varchar(255) NOT NULL, "
                + "Attribute_Value_1 varchar(255), "
                + "Attribute_Value_2 varchar(255), "
                + "Attribute_Value_3 varchar(255), "
                + "Attribute_Value_4 varchar(255), "
                + "Attribute_Value_5 varchar(255), "
                + "Attribute_Value_6 varchar(255), "
                + "Attribute_Value_7 varchar(255), "
                + "PRIMARY KEY (Item_ID), "
                + "FOREIGN KEY (Type_ID) REFERENCES CIT(Type_ID))";

        Statement stmt = con.createStatement();
        stmt.execute(query);
    }

    /**
     * Creates the User-table in the database
     * 
     * @throws SQLException
     */
    private void createTableUser() throws SQLException{
        String query = "Create Table User "
                + "(User_Name varchar(255) NOT NULL, "
                + "Password varchar(255) NOT NULL, "
                + "Is_Initial boolean DEFAULT 'true', "
                + "Is_Admin boolean DEFAULT 'false', "
                + "Name varchar(255), "
                + "Surname varchar(255), "
                + "PRIMARY KEY (User_Name))";

        Statement stmt = con.createStatement();
        stmt.execute(query);
    }
}
