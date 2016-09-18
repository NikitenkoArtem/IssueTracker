package by.epam.dao;

import by.epam.DBConnection;
import by.epam.GenericDao;
import by.epam.entity.Resolution;

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
public class ResolutionDao implements GenericDao<Resolution, Integer> {
    private Connection connection;

    public ResolutionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Resolution entity) throws SQLException {
        final String sql = "INSERT INTO resolutions VALUES(?)";
        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, entity.getResolutionName());
        new DBConnection().executeUpdate(connection, sql, params);
        return null;
    }

    @Override
    public Resolution read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Resolution> readAll() throws SQLException {
        final String sql = "SELECT resolution_name FROM resolutions";
        ArrayList<Resolution> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Resolution resolution = new Resolution();
                    resolution.setResolutionName(rs.getString("resolution_name"));
                    list.add(resolution);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Resolution entity) throws SQLException {
        final String sql = "UPDATE resolutions SET resolution_name = ? WHERE resolution_id = ?";
        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, entity.getResolutionName());
        params.put(2, entity.getResolutionId());
        new DBConnection().executeUpdate(connection, sql, params);
    }

    @Override
    public void delete(Resolution entity) throws SQLException {

    }
}
