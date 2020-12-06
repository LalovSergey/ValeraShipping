package sample;

import java.sql.*;

public class MySqlCon {
    public static String conState="заглушка";
    public static Connection con = null;
    public static Connection getCon(String log, String pass) {
        String connectionUrl = "jdbc:mysql://localhost:3306/db_shop?serverTimezone=UTC";
        if (con == null) {
            try {
                conState = "Заглушка";
                con = DriverManager.getConnection(connectionUrl, log, pass);
            } catch (SQLException e) {
                if (e.getSQLState().toString().equals("08S01")) {
                    conState = "08S01";
                    System.out.println("Нет связи с сервером вообще!"+" "+conState );
                } else {
                    conState = "Заглушка";
                    //System.out.println(e.getSQLState() + "  " + e.getMessage());
                }
            }
        }
        return con;
    }
}