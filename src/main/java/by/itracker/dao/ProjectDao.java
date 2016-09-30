package by.itracker.dao;

import by.itracker.DBConnection;
import by.itracker.GenericDao;
import by.itracker.entity.Manager;
import by.itracker.entity.Project;

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
        final String sql = "INSERT INTO projects(project_name, description, manager_id) VALUES(?, ?, ?)";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getProjectName());
        sqlParams.put(2, entity.getDescription());
        sqlParams.put(3, entity.getManagerId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
        return null;
    }

    @Override
    public Project read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM projects WHERE project_id = " + id;
        Project project = new Project();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, project);
                }
            }
        }
        return project;
    }

    @Override
    public List<Project> readAll() throws SQLException {
        final String sql = "SELECT * FROM projects";
        ArrayList<Project> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    selectRow(rs, project);
                    list.add(project);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Project entity) throws SQLException {
        final String sql = "UPDATE projects SET project_name = ?, description = ?, manager_id = ? WHERE project_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getProjectName());
        sqlParams.put(2, entity.getDescription());
        sqlParams.put(3, entity.getManagerId());
        sqlParams.put(4, entity.getProjectId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    @Override
    public void delete(Project entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Project project) throws SQLException {
        project.setProjectId(rs.getInt("project_id"));
        project.setProjectName(rs.getString("project_name"));
        project.setDescription(rs.getString("description"));
        Manager manager = new Manager();
        manager.setManagerId(rs.getInt("manager_id"));
        project.setManagerId(manager);
    }
}
