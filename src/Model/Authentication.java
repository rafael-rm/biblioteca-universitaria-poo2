package Model;

import Infrastructure.DatabaseMysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authentication {
    public static boolean login(String login, String senha) {
        DatabaseMysql db = new DatabaseMysql();
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM usuarios WHERE login = '" + login + "' AND senha = '" + senha + "';";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
