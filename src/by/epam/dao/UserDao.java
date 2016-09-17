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
        final String sql = "INSERT INTO users VALUES(?, ?, ?, 1, ?)";
        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, entity.getEmail());
        params.put(2, entity.getFirstName());
        params.put(3, entity.getLastName());
        params.put(4, entity.getRole());
        params.put(5, entity.getPassword());
        new DBConnection().executeUpdate(connection, sql, params);
        return null;
    }

    @Override
    public User read(String id) throws SQLException {
        return null;
    }

    @Override
    public List<User> readAll() throws SQLException {
        final String sql = "SELECT * FROM users";
        ArrayList<User> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setEmail(rs.getString("resolution_name"));
                    list.add(user);
                }
            }
        }
        return list;
    }

    @Override
    public void update(User entity) throws SQLException {
        final String sql = "UPDATE users SET ? = ?";
        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, entity.getEmail());
        params.put(2, entity.getFirstName());
        params.put(3, entity.getLastName());
        params.put(4, entity.getRole());
        new DBConnection().executeUpdate(connection, sql, params);
    }

    @Override
    public void delete(User entity) throws SQLException {

    }

    public void updatePassword(User entity) throws SQLException {
        final String sql = "UPDATE users SET password = ? WHERE email = ?";
        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, entity.getEmail());
        params.put(2, entity.getPassword());
        new DBConnection().executeUpdate(connection, sql, params);
    }
}