package by.epam.dao;

import by.epam.DBConnection;
import by.epam.GenericDao;
import by.epam.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Price on 17.09.2016.
 */
public class UserDao implements GenericDao<User, String> {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String create(User entity) throws SQLException {
        final String sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getEmail());
        sqlParams.put(2, entity.getFirstName());
        sqlParams.put(3, entity.getLastName());
        sqlParams.put(4, entity.getRole());
        sqlParams.put(5, entity.getPassword());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
        return null;
    }

    @Override
    public User read(String id) throws SQLException {
        final String sql = "SELECT * FROM users WHERE email = '" + id + "'";
        User user = new User();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, user);
                }
            }
        }
        return user;
    }

    @Override
    public List<User> readAll() throws SQLException {
        final String sql = "SELECT * FROM users";
        ArrayList<User> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    selectRow(rs, user);
                    list.add(user);
                }
            }
        }
        return list;
    }

    @Override
    public void update(User entity) throws SQLException {
        final String sql = "UPDATE users SET first_name = ?, last_name = ?, role = ? WHERE email = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getFirstName());
        sqlParams.put(2, entity.getLastName());
        sqlParams.put(3, entity.getRole());
        sqlParams.put(4, entity.getEmail());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    @Override
    public void delete(User entity) throws SQLException {

    }

    public void updatePassword(User entity) throws SQLException {
        final String sql = "UPDATE users SET password = ? WHERE email = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getPassword());
        sqlParams.put(2, entity.getEmail());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    private void selectRow(ResultSet rs, User user) throws SQLException {
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setRole(rs.getInt("role"));
        user.setPassword(rs.getString("password"));
    }
}
