package by.epam;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    public DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            InitialContext ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:/comp/env");
            DataSource ds = (DataSource) env.lookup("jdbc/epam");
            conn = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public boolean executeUpdate(Connection conn, String sql) throws SQLException {
        PreparedStatement stmt = null;
        int insert = 0;
        try {
            stmt = conn.prepareStatement(sql);
            insert = stmt.executeUpdate();
        } finally {
            stmt.close();
        }
        if (insert > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ResultSet executeQuery(Connection conn, String sql) throws SQLException {
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
