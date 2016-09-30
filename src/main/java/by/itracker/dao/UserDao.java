package by.itracker.dao;

import by.itracker.DBConnection;
import by.itracker.GenericDao;
import by.itracker.entity.Role;
import by.itracker.entity.User;

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
public class UserDao implements GenericDao<User, Integer> {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(User entity) throws SQLException {
        final String sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getEmail());
        sqlParams.put(2, entity.getFirstName());
        sqlParams.put(3, entity.getLastName());
        sqlParams.put(4, entity.getRoleId());
        sqlParams.put(5, entity.getPassword());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
        return null;
    }

    @Override
    public User read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM users WHERE user_id = " + id;
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
        final String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, role_id = ? WHERE user_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getFirstName());
        sqlParams.put(2, entity.getLastName());
        sqlParams.put(3, entity.getRoleId());
        sqlParams.put(4, entity.getEmail());
        sqlParams.put(5, entity.getUserId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    @Override
    public void delete(User entity) throws SQLException {

    }

    public User read(String email) throws SQLException {
        final String sql = "SELECT * FROM users WHERE email = '" + email + "'";
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

    public void updatePassword(User entity) throws SQLException {
        final String sql = "UPDATE users SET password = ? WHERE email = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getPassword());
        sqlParams.put(2, entity.getEmail());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    private void selectRow(ResultSet rs, User user) throws SQLException {
        user.setUserId(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        Role role = new Role();
        role.setRoleId(rs.getInt("role_id"));
        user.setRoleId(role);
        user.setPassword(rs.getString("password"));
    }
}
