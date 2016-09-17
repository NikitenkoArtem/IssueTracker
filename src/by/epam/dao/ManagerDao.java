package by.epam.dao;

import by.epam.GenericDao;
import by.epam.entity.Manager;
import by.epam.entity.Priority;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        return null;
    }

    @Override
    public Manager read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Manager> readAll() throws SQLException {
        final String sql = "SELECT manager_name FROM managers";
        ArrayList<Manager> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Manager manager = new Manager();
                    manager.setManagerName(rs.getString("manager_name"));
                    list.add(manager);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Manager entity) throws SQLException {

    }

    @Override
    public void delete(Manager entity) throws SQLException {

    }
}
