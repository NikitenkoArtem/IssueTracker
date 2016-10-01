package by.itracker;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DBConnection {
    public DBConnection() {
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            InitialContext ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:/comp/env");
            DataSource ds = (DataSource) env.lookup("jdbc/itracker");
            try {
                conn = ds.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
