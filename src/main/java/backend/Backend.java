package backend;

import java.sql.*;

public class Backend {

    public void call() throws SQLException {
        final String fileName = "CMS.db";
        final String completePath = System.getProperty("user.home") + "\\JFXdb\\" + fileName;


        Connection con = DriverManager.getConnection("jdbc:h2:file:\\" + completePath + ";", "sa", "");
        Statement stm = con.createStatement();


        stm.execute("CREATE TABLE redbulls (id int(3))");
        stm.execute("INSERT INTO redbulls (id) values '123';");

        ResultSet rs = stm.executeQuery("SELECT * FROM redbulls");

        while (rs.next()) {
            System.out.println(rs.getString("id"));
        }
    }



}
