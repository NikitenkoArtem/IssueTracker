package by.epam.dao;

import by.epam.DBConnection;
import by.epam.GenericDao;
import by.epam.entity.Project;

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
public class ProjectDao implements GenericDao<Project, Integer> {
    private Connection connection;

    public ProjectDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Project entity) throws SQLException {
        final String sql = "INSERT INTO projects VALUES(?, ?, ?, ?)";
        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, entity.getProjectName());
        params.put(2, entity.getDescription());
        params.put(3, entity.getBuild());
        params.put(4, entity.getManager());
        new DBConnection().executeUpdate(connection, sql, params);
        return entity.getId();
    }

    @Override
    public Project read(Integer id) throws SQLException {
        final String selectProject = "SELECT project_name, description, build, manager FROM projects WHERE project_id=?";
        return null;
    }

    @Override
    public List<Project> readAll() throws SQLException {
        final String sql = "SELECT manager_name FROM managers";
        ArrayList<Project> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setProjectName(rs.getString("project_name"));
                    list.add(project);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Project entity) throws SQLException {
        final String sql = "UPDATE projects SET project_name=?, description=?, build=?, manager=? WHERE project_id=?";
        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, entity.getProjectName());
        params.put(2, entity.getDescription());
        params.put(3, entity.getBuild());
        params.put(4, entity.getManager());
        params.put(5, entity.getId());
        new DBConnection().executeUpdate(connection, sql, params);
    }

    @Override
    public void delete(Project entity) throws SQLException {

    }
}
