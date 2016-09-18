package by.epam.dao;

import by.epam.DBConnection;
import by.epam.GenericDao;
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
public class PriorityDao implements GenericDao<Priority, Integer> {
    private Connection connection;

    public PriorityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Priority entity) throws SQLException {
        final String insertPriority = "INSERT INTO priorities VALUES(?)";
        HashMap<Integer, Object> params = new HashMap<>();
        final String  priorityName = entity.getPriorityName();
        params.put(1, priorityName);
        new DBConnection().executeUpdate(connection, insertPriority, params);
        return entity.getPriorityId();
    }

    @Override
    public Priority read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Priority> readAll() throws SQLException {
        final String sql = "SELECT priority_name FROM priorities";
        ArrayList<Priority> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Priority priority = new Priority();
                    priority.setPriorityName(rs.getString("priority_name"));
                    list.add(priority);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Priority entity) throws SQLException {
        final String updatePriority = "UPDATE projects SET priority_name = ? WHERE priority_id = ?";
        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, entity.getPriorityName());
        params.put(2, entity.getPriorityId());
        new DBConnection().executeUpdate(connection, updatePriority, params);
    }

    @Override
    public void delete(Priority entity) throws SQLException {

    }
}
