package Infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseMysql {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://hyzen-database.mysql.database.azure.com";
    private static final String PORT = "3306";
    private static final String USER = "hyzen";
    private static final String PASS = "Uzumymw1@";
    private static final String DATABASE = "acervo";
    private static final String TIMEZONE = "useTimezone=true&serverTImezone=UTC";
    private Connection conn;
    private static DatabaseMysql instance;

    public DatabaseMysql() {

        String connect = URL + ":" + PORT + "/" + DATABASE + "?" + TIMEZONE;

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(connect, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        }

    }

    public static DatabaseMysql getInstance() throws SQLException{
        if(instance == null)
            instance = new DatabaseMysql();
        else if(instance.getConnection().isClosed())
            instance = new DatabaseMysql();

        return instance;
    }

    public Connection getConnection(){
        return conn;
    }

    public void execute(String sql) {
        try {
            DatabaseMysql db = DatabaseMysql.getInstance();
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
