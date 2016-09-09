package by.epam;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Price on 09.09.2016.
 */
public class DBConnection {
    private static Connection conn;

    public static Connection getConnection() {
        try {
            InitialContext ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:/comp/env");
            DataSource ds = (DataSource) env.lookup("jdbc/epam");
            conn = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void connectionClose() throws SQLException {
        try {
        } finally {
            conn.close();
        }
    }

    public static void resultSetClose(ResultSet rs) throws SQLException {
        try {
        } finally {
            rs.close();
        }
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
        } finally {
            stmt.close();
        }
        return rs;
    }
}
