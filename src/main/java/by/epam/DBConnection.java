package by.epam;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.sql.Date;
import java.util.*;

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

    public static ArrayList<HashMap<String, Object>> getList(Connection conn, String sql) throws SQLException {
        return new DBConnection().execQuery(conn, sql);
    }

//    public static List<? extends Serializable> getEntityList(Connection conn, String sql) throws SQLException {
//        return new DBConnection().execQuery(conn, sql);
//    }

    private PreparedStatement getPreparedUpdate(Connection conn, String sql, Map<Integer, Object> params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (Map.Entry<Integer, Object> entry : params.entrySet()) {
            final int index = entry.getKey().intValue();
            final Object value = entry.getValue();
            if (value instanceof Integer) {
                stmt.setInt(index, ((Integer) value).intValue());
            }
            if (value instanceof Date) {
                stmt.setDate(index, (Date) value);
            }
            if (value instanceof String) {
                stmt.setString(index, value.toString());
            }
        }
        return stmt;
    }

    public void executeUpdate(Connection conn, String sql, Map<Integer, Object> params) throws SQLException {
        try (PreparedStatement stmt = getPreparedUpdate(conn, sql, params)) {
            stmt.executeUpdate();
            if (!conn.getAutoCommit()) {
                conn.commit();
            }
        }
    }

    public PreparedStatement prepareUpdate(Connection conn, String sql, Map<Integer, String> table) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (Map.Entry<Integer, String> entry : table.entrySet()) {
            stmt.setString(entry.getKey().intValue(), entry.getValue());
        }
        return stmt;
    }

    private void execUpdate(Connection conn, String sql, Map<Integer, String> table) throws SQLException {
        try (PreparedStatement stmt = prepareUpdate(conn, sql, table)) {
            stmt.executeUpdate();
            if (!conn.getAutoCommit()) {
                conn.commit();
            }
        }
    }

    public ArrayList<HashMap<String, Object>> execQuery(Connection conn, String sql) throws SQLException {
        ArrayList<HashMap<String, Object>> table = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                final ResultSetMetaData metaData = rs.getMetaData();
                final int columnCount = metaData.getColumnCount();
                while (rs.next()) {
                    HashMap<String, Object> values = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        final String label = metaData.getColumnLabel(i);
                        final Object value = rs.getObject(label);
                        values.put(label, value);
                    }
                    table.add(values);
                }
            }
        }
        return table;
    }
}
