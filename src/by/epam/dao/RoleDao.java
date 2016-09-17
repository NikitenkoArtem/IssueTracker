package by.epam.dao;

import by.epam.GenericDao;
import by.epam.entity.Resolution;
import by.epam.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Price on 17.09.2016.
 */
public class RoleDao implements GenericDao<Role, String> {
    private Connection connection;

    public RoleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String create(Role entity) throws SQLException {
        return null;
    }

    @Override
    public Role read(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Role> readAll() throws SQLException {
        final String sql = "SELECT role_name FROM roles";
        ArrayList<Role> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Role role = new Role();
                    role.setRoleName(rs.getString("role_name"));
                    list.add(role);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Role entity) throws SQLException {

    }

    @Override
    public void delete(Role entity) throws SQLException {

    }
}
