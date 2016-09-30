package by.itracker.dao;

import by.itracker.DBConnection;
import by.itracker.GenericDao;
import by.itracker.entity.Priority;

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
public class PriorityDao implements GenericDao<Priority, Integer> {
    private Connection connection;

    public PriorityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Priority entity) throws SQLException {
        final String sql = "INSERT INTO priorities(priority_name) VALUES(?)";
        HashMap<Integer, Object> sqlParam = new HashMap<>();
        sqlParam.put(1, entity.getPriorityName());
        new DBConnection().executeUpdate(connection, sql, sqlParam);
        return null;
    }

    @Override
    public Priority read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM priorities WHERE priority_id = " + id;
        Priority priority = new Priority();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, priority);
                }
            }
        }
        return priority;
    }

    @Override
    public List<Priority> readAll() throws SQLException {
        final String sql = "SELECT * FROM priorities";
        ArrayList<Priority> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Priority priority = new Priority();
                    selectRow(rs, priority);
                    list.add(priority);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Priority entity) throws SQLException {
        final String updatePriority = "UPDATE projects SET priority_name = ? WHERE priority_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getPriorityName());
        sqlParams.put(2, entity.getPriorityId());
        new DBConnection().executeUpdate(connection, updatePriority, sqlParams);
    }

    @Override
    public void delete(Priority entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Priority priority) throws SQLException {
        priority.setPriorityId(rs.getInt("priority_id"));
        priority.setPriorityName(rs.getString("priority_name"));
    }
}
