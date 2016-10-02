package by.itracker.dao;

import by.itracker.dao.generic.AbstractGenericDaoImpl;
import by.itracker.entity.Privilege;
import by.itracker.entity.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Price on 19.09.2016.
 */
public class PrivilegeDao extends AbstractGenericDaoImpl<Privilege, Integer> {
//    private Connection connection;

    public PrivilegeDao(Connection connection, Class<Privilege> clazz) {
        super(connection, clazz);
    }

    @Override
    public Integer create(Privilege entity) {
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getPrivilegeName());
        sqlParams.put(2, entity.getRoleId());
        super.setSql("INSERT INTO privileges(privilege_name, role_id) VALUES(?, ?)");
        super.setSqlParams(sqlParams);
        return super.create(entity);
    }

    @Override
    public Privilege read(Integer id) {
        super.setSql("SELECT * FROM privileges WHERE privilege_id = " + id);
        return super.read(id);
    }

    @Override
    public List<Privilege> readAll() {
        super.setSql("SELECT * FROM privileges");
        return super.readAll();
    }

    @Override
    public void update(Privilege entity) {
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getPrivilegeName());
        sqlParams.put(2, entity.getRoleId());
        sqlParams.put(3, entity.getPrivilegeId());
        super.setSql("UPDATE privileges SET privilege_name = ?, role_id = ? WHERE privilege_id = ?");
        super.setSqlParams(sqlParams);
        super.update(entity);
    }

    @Override
    protected void selectRow(ResultSet rs, Privilege entity) throws SQLException {
        entity.setPrivilegeId(rs.getInt("privilege_id"));
        entity.setPrivilegeName(rs.getString("privilege_name"));
        Role role = new Role();
        role.setRoleId(rs.getInt("role_id"));
        entity.setRoleId(role);
    }
/*
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
    }*/
}
