package by.itracker.dao;

import by.itracker.DBConnection;
import by.itracker.GenericDao;
import by.itracker.entity.Resolution;

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
        final String sql = "INSERT INTO resolutions(resolution_name) VALUES(?)";
        HashMap<Integer, Object> sqlParam = new HashMap<>();
        sqlParam.put(1, entity.getResolutionName());
        new DBConnection().executeUpdate(connection, sql, sqlParam);
        return null;
    }

    @Override
    public Resolution read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM resolutions WHERE resolution_id = " + id;
        Resolution resolution = new Resolution();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, resolution);
                }
            }
        }
        return resolution;
    }

    @Override
    public List<Resolution> readAll() throws SQLException {
        final String sql = "SELECT * FROM resolutions";
        ArrayList<Resolution> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Resolution resolution = new Resolution();
                    selectRow(rs, resolution);
                    list.add(resolution);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Resolution entity) throws SQLException {
        final String sql = "UPDATE resolutions SET resolution_name = ? WHERE resolution_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getResolutionName());
        sqlParams.put(2, entity.getResolutionId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    @Override
    public void delete(Resolution entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Resolution resolution) throws SQLException {
        resolution.setResolutionId(rs.getInt("resolution_id"));
        resolution.setResolutionName(rs.getString("resolution_name"));
    }
}
