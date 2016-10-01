package by.itracker.dao;

import by.itracker.entity.Build;
import by.itracker.entity.Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Price on 17.09.2016.
 */
public class BuildDao extends AbstractGenericDaoImpl<Build, Integer> {
//    private Connection connection;

    public BuildDao(Connection connection, Class<Build> clazz) {
        super(connection, clazz);
    }

    @Override
    public Integer create(Build entity) {
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getBuild());
        sqlParams.put(2, entity.getProjectId());
        super.setSql("INSERT INTO builds(build, project_id) VALUES(?, ?)");
        super.setSqlParams(sqlParams);
        return super.create(entity);
    }

    @Override
    public Build read(Integer id) {
        super.setSql("SELECT * FROM builds WHERE build_id = " + id);
        return super.read(id);
    }

    @Override
    public List<Build> readAll() {
        super.setSql("SELECT * FROM builds");
        return super.readAll();
    }

    @Override
    public void update(Build entity) {
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getBuild());
        sqlParams.put(2, entity.getProjectId());
        sqlParams.put(3, entity.getBuildId());
        super.setSql("UPDATE builds SET build = ?, project_id = ? WHERE build_id = ?");
        super.setSqlParams(sqlParams);
        super.update(entity);
    }

    @Override
    protected void selectRow(ResultSet rs, Build entity) throws SQLException {
        entity.setBuildId(rs.getInt("build_id"));
        entity.setBuild(rs.getString("build"));
        Project project = new Project();
        project.setProjectId(rs.getInt("project_id"));
        entity.setProjectId(project);
    }

    public List<Build> readByProject(Integer id) {
        super.setSql("SELECT * FROM builds WHERE project_id = " + id);
        return super.readAll();
    }

/*
    @Override
    public Integer create(Build entity) throws SQLException {
        final String sql = "INSERT INTO builds(build, project_id) VALUES(?, ?)";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getBuild());
        sqlParams.put(2, entity.getProjectId());
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
        final String sql = "UPDATE builds SET build = ?, project_id = ? WHERE build_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getBuild());
        sqlParams.put(2, entity.getProjectId());
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
        Project project = new Project();
        project.setProjectId(rs.getInt("project_id"));
        build.setProjectId(project);
    }
*/
}
