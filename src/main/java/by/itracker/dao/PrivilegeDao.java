package by.itracker.dao;

import by.itracker.DBConnection;
import by.itracker.GenericDao;
import by.itracker.entity.Privilege;
import by.itracker.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Price on 19.09.2016.
 */
public class PrivilegeDao implements GenericDao<Privilege, Integer> {
    private Connection connection;

    public PrivilegeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Privilege entity) throws SQLException {
        final String sql = "INSERT INTO privileges(privilege_name, role_id) VALUES(?, ?)";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getPrivilegeName());
        sqlParams.put(2, entity.getRoleId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
        return null;
    }

    @Override
    public Privilege read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM privileges WHERE privilege_id = " + id;
        Privilege privilege = new Privilege();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, privilege);
                }
            }
        }
        return privilege;
    }

    @Override
    public List<Privilege> readAll() throws SQLException {
        final String sql = "SELECT * FROM privileges";
        ArrayList<Privilege> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Privilege privilege = new Privilege();
                    selectRow(rs, privilege);
                    list.add(privilege);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Privilege entity) throws SQLException {
        final String sql = "UPDATE privileges SET privilege_name = ?, role_id = ? WHERE privilege_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getPrivilegeName());
        sqlParams.put(2, entity.getRoleId());
        sqlParams.put(3, entity.getPrivilegeId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    @Override
    public void delete(Privilege entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Privilege privilege) throws SQLException {
        privilege.setPrivilegeId(rs.getInt("privilege_id"));
        privilege.setPrivilegeName(rs.getString("privilege_name"));
        Role role = new Role();
        role.setRoleId(rs.getInt("role_id"));
        privilege.setRoleId(role);
    }
}
