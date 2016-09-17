package by.epam.dao;

import by.epam.DBConnection;
import by.epam.GenericDao;
import by.epam.entity.Status;

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
public class StatusDao implements GenericDao<Status, String> {
    private Connection connection;

    public StatusDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String create(Status entity) throws SQLException {
        return null;
    }

    @Override
    public Status read(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Status> readAll() throws SQLException {
        final String sql = "SELECT status_name FROM statuses";
        ArrayList<Status> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Status status = new Status();
                    status.setStatusName(rs.getString("status_name"));
                    list.add(status);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Status entity) throws SQLException {
        final String updateStatuses = "UPDATE statuses SET status_name = ? WHERE status_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getStatusName());
        sqlParams.put(2, entity.getStatusName());
        new DBConnection().executeUpdate(connection, updateStatuses, sqlParams);
    }

    @Override
    public void delete(Status entity) throws SQLException {

    }
}
