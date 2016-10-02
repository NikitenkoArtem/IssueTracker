package by.itracker.dao;

import by.itracker.dao.generic.AbstractGenericDaoImpl;
import by.itracker.entity.Manager;
import by.itracker.entity.Project;
import by.itracker.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Price on 17.09.2016.
 */
public class ProjectDao extends AbstractGenericDaoImpl<Project, Integer> {
//    private Connection connection;

    public ProjectDao(Connection connection, Class<Project> clazz) {
        super(connection, clazz);
    }

    @Override
    public Integer create(Project entity) {
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getProjectName());
        sqlParams.put(2, entity.getDescription());
        sqlParams.put(3, entity.getManagerId());
        super.setSql("INSERT INTO projects(project_name, description, manager_id) VALUES(?, ?, ?)");
        super.setSqlParams(sqlParams);
        return super.create(entity);
    }

    @Override
    public Project read(Integer id) {
        super.setSql("SELECT * FROM projects WHERE project_id = " + id);
        return super.read(id);
    }

    @Override
    public List<Project> readAll() {
        super.setSql("SELECT project_id, project_name, description, u.first_name AS manager\n" +
                "FROM projects\n" +
                "INNER JOIN managers mgr ON projects.MANAGER_ID = mgr.MANAGER_ID\n" +
                "INNER JOIN users u ON mgr.USER_ID = u.USER_ID");
        return super.readAll();
    }

    @Override
    public void update(Project entity) {
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getProjectName());
        sqlParams.put(2, entity.getDescription());
        sqlParams.put(3, entity.getManagerId());
        sqlParams.put(4, entity.getProjectId());
        super.setSql("UPDATE projects SET project_name = ?, description = ?, manager_id = ? WHERE project_id = ?");
        super.setSqlParams(sqlParams);
        super.update(entity);
    }

    @Override
    protected void selectRow(ResultSet rs, Project entity) throws SQLException {
        entity.setProjectId(rs.getInt("project_id"));
        entity.setProjectName(rs.getString("project_name"));
        entity.setDescription(rs.getString("description"));
        User user = new User();
        user.setFirstName(rs.getString("manager"));
        Manager manager = new Manager();
        manager.setUserId(user);
        entity.setManagerId(manager);
    }
/*
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
    }*/
}
