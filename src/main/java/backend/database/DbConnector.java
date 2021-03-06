package backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnector {

    static Connection con;
    static Statement stmt;
    private static final String FILE_NAME = "CMS";
    private static final String COMPLETE_PATH = System.getProperty("user.home") + "\\CMSdb\\" + FILE_NAME;
    private static final String URL = "jdbc:h2:file:\\" + COMPLETE_PATH + ";TRACE_LEVEL_FILE=0";


    /**
     * Starts the connection to the integrated h2-database
     *
     * @throws SQLException
     */
    public void startConnection() throws SQLException {

        con = DriverManager.getConnection(URL, "sa", "");
        stmt = con.createStatement();

        initializeDb();
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
        insertFirstUser();
    }

    /**
     * Creates the CIT-Table in the database
     * 
     * @throws SQLException
     */
    private void createTableCit() throws SQLException{
        String query = "Create Table IF NOT EXISTS CIT "
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

        stmt.execute(query);
    }

    /**
     * Creates the CIR-table in the database
     * 
     * @throws SQLException
     */
    private void createTableCir() throws SQLException{
        String query = "Create Table IF NOT EXISTS CIR "
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

        stmt.execute(query);
    }

    /**
     * Creates the User-table in the database
     * 
     * @throws SQLException
     */
    private void createTableUser() throws SQLException{
        String query = "Create Table IF NOT EXISTS User "
                + "(User_Name varchar(255) NOT NULL, "
                + "Password varchar(255) NOT NULL, "
                + "Is_Initial boolean DEFAULT 'true', "
                + "Is_Admin boolean DEFAULT 'false', "
                + "Name varchar(255), "
                + "Surname varchar(255), "
                + "PRIMARY KEY (User_Name))";

        stmt.execute(query);
    }

    /**
     * Inserts a user with username = "admin" and password = "admin" into the USER-table
     * if there are no users yet
     *
     * @throws SQLException
     */
    private void insertFirstUser() throws SQLException {
        String query = "INSERT INTO USER "
                + "SELECT 'admin', 'f3BZyl1b+cGrUn+HPjvPfkZcBRCfj0VoocwLEUbB4ZaTyEfyw2ppAOxQP05f64tW', false, true, 'Admini', 'Strator'"
                + "WHERE NOT EXISTS (SELECT * FROM USER)";

        stmt.execute(query);
    }

    /**
     * Closes the database connection if it is still open.
     */
    public static void closeConnection() {
        try {
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
