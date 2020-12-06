package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConMySql {
    public static Connection con = null;
    public static Connection getCon(String login, String password) {
        String connectionUrl = "jdbc:mysql://localhost:3306/db_shop?serverTimezone=UTC";
        if (con == null) {
            try {
                con = DriverManager.getConnection(connectionUrl, login, password);
            } catch (SQLException e) {
                    System.out.println(e.getSQLState() + "  " + e.getMessage());
            }
        }
        return con;
    }
}
