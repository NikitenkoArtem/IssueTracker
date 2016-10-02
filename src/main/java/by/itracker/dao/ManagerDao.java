package by.itracker.dao;

import by.itracker.dao.generic.AbstractGenericDaoImpl;
import by.itracker.entity.Manager;
import by.itracker.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Price on 17.09.2016.
 */
public class ManagerDao extends AbstractGenericDaoImpl<Manager, Integer> {
//    private Connection connection;

    public ManagerDao(Connection connection, Class<Manager> clazz) {
        super(connection, clazz);
    }

    @Override
    public Integer create(Manager entity) {
        HashMap<Integer, Object> sqlParam = new HashMap<>();
        sqlParam.put(1, entity.getUserId());
        super.setSql("INSERT INTO managers(user_id) VALUES(?)");
        super.setSqlParams(sqlParam);
        return super.create(entity);
    }

    @Override
    public Manager read(Integer id) {
        super.setSql("SELECT * FROM managers WHERE manager_id = " + id);
        return super.read(id);
    }

    @Override
    public List<Manager> readAll() {
        super.setSql("SELECT * FROM managers");
        return super.readAll();
    }

    @Override
    public void update(Manager entity) {
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getUserId());
        sqlParams.put(2, entity.getManagerId());
        super.setSql("UPDATE managers SET user_id = ? WHERE manager_id = ?");
        super.setSqlParams(sqlParams);
        super.update(entity);
    }

    @Override
    protected void selectRow(ResultSet rs, Manager entity) throws SQLException {
        entity.setManagerId(rs.getInt("manager_id"));
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        entity.setUserId(user);
    }
/*

    @Override
    public Integer create(Manager entity) throws SQLException {
        final String sql = "INSERT INTO managers(user_id) VALUES(?)";
        HashMap<Integer, Object> sqlParam = new HashMap<>();
        sqlParam.put(1, entity.getUserId());
        new DBConnection().executeUpdate(connection, sql, sqlParam);
        return null;
    }

    @Override
    public Manager read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM managers WHERE manager_id = " + id;
        Manager manager = new Manager();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, manager);
                }
            }
        }
        return manager;
    }

    @Override
    public List<Manager> readAll() throws SQLException {
        final String sql = "SELECT * FROM managers";
        ArrayList<Manager> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Manager manager = new Manager();
                    selectRow(rs, manager);
                    list.add(manager);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Manager entity) throws SQLException {
        final String sql = "UPDATE managers SET user_id = ? WHERE manager_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getUserId());
        sqlParams.put(2, entity.getManagerId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    @Override
    public void delete(Manager entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Manager manager) throws SQLException {
        manager.setManagerId(rs.getInt("manager_id"));
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        manager.setUserId(user);
    }
*/
}
