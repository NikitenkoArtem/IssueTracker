package by.epam.dao;

import by.epam.GenericDao;
import by.epam.entity.Build;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Price on 17.09.2016.
 */
public class BuildDao implements GenericDao<Build, String> {
    private Connection connection;

    public BuildDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String create(Build entity) throws SQLException {
        return null;
    }

    @Override
    public Build read(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Build> readAll() throws SQLException {
        final String sql = "SELECT build FROM builds";
        ArrayList<Build> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Build build = new Build();
                    build.setBuild(rs.getString("build"));
                    list.add(build);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Build entity) throws SQLException {

    }

    @Override
    public void delete(Build entity) throws SQLException {

    }
}
