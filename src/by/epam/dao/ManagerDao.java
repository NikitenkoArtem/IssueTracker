package by.epam.dao;

import by.epam.DBConnection;
import by.epam.GenericDao;
import by.epam.entity.Manager;
import by.epam.entity.Priority;

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
public class ManagerDao implements GenericDao<Manager, Integer> {
    private Connection connection;

    public ManagerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Manager entity) throws SQLException {
        final String sql = "INSERT INTO managers(user) VALUES(?)";
        HashMap<Integer, Object> sqlParam = new HashMap<>();
        sqlParam.put(1, entity.getUser());
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
        final String sql = "UPDATE managers SET user = ? WHERE manager_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getUser());
        sqlParams.put(2, entity.getManagerId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    @Override
    public void delete(Manager entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Manager manager) throws SQLException {
        manager.setManagerId(rs.getInt("manager_id"));
        manager.setUser(rs.getInt("user"));
    }
}
