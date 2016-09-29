package by.epam.dao;

import by.epam.DBConnection;
import by.epam.GenericDao;
import by.epam.entity.Build;

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
public class BuildDao implements GenericDao<Build, Integer> {
    private Connection connection;

    public BuildDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Build entity) throws SQLException {
        final String sql = "INSERT INTO builds(build, project) VALUES(?, ?)";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getBuild());
        sqlParams.put(2, entity.getProject());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
        return null;
    }

    @Override
    public Build read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM builds WHERE build_id = " + id;
        Build build = new Build();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, build);
                }
            }
        }
        return build;
    }

    @Override
    public List<Build> readAll() throws SQLException {
        final String sql = "SELECT * FROM builds";
        return getList(sql);
    }

    @Override
    public void update(Build entity) throws SQLException {
        final String sql = "UPDATE builds SET build = ?, project = ? WHERE build_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getBuild());
        sqlParams.put(2, entity.getProject());
        sqlParams.put(3, entity.getBuildId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    @Override
    public void delete(Build entity) throws SQLException {

    }

    public List<Build> readByProject(Integer id) throws SQLException {
        final String sql = "SELECT * FROM builds WHERE project = " + id;
        return getList(sql);
    }

    private List<Build> getList(String sql) throws SQLException {
        ArrayList<Build> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Build build = new Build();
                    selectRow(rs, build);
                    list.add(build);
                }
            }
        }
        return list;
    }

    private void selectRow(ResultSet rs, Build build) throws SQLException {
        build.setBuildId(rs.getInt("build_id"));
        build.setBuild(rs.getString("build"));
        build.setProject(rs.getInt("project"));
    }
}
