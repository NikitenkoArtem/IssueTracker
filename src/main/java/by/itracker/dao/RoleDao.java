package by.itracker.dao;

import by.itracker.DBConnection;
import by.itracker.GenericDao;
import by.itracker.entity.Role;

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
public class RoleDao implements GenericDao<Role, Integer> {
    private Connection connection;

    public RoleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Role entity) throws SQLException {
        final String sql = "INSERT INTO roles(role_name) VALUES(?)";
        HashMap<Integer, Object> sqlParam = new HashMap<>();
        sqlParam.put(1, entity.getRoleName());
        new DBConnection().executeUpdate(connection, sql, sqlParam);
        return null;
    }

    @Override
    public Role read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM roles WHERE role_id = " + id;
        Role role = new Role();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, role);
                }
            }
        }
        return role;
    }

    @Override
    public List<Role> readAll() throws SQLException {
        final String sql = "SELECT * FROM roles";
        ArrayList<Role> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Role role = new Role();
                    selectRow(rs, role);
                    list.add(role);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Role entity) throws SQLException {
        final String sql = "UPDATE roles SET role_name = ? WHERE role_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getRoleName());
        sqlParams.put(2, entity.getRoleId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    @Override
    public void delete(Role entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Role role) throws SQLException {
        role.setRoleId(rs.getInt("role_id"));
        role.setRoleName(rs.getString("role_name"));
    }
}
