package by.epam.dao;

import by.epam.DBConnection;
import by.epam.GenericDao;
import by.epam.entity.Issue;

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
public class IssueDao implements GenericDao<Issue, Integer> {
    private Connection connection;

    public IssueDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Issue entity) throws SQLException {
        final String insertIssue = "insert into issues(summary, description, status, type, priority, project, " +
                "assignee) values(?, ?, ?, ?, ?, ?, ?)";
        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, entity.getSummary());
        params.put(2, entity.getDescription());
        params.put(3, entity.getStatus());
        params.put(4, entity.getType());
        params.put(5, entity.getPriority());
        params.put(6, entity.getProject());
        params.put(7, entity.getAssignee());
        new DBConnection().executeUpdate(connection, insertIssue, params);
        return null;
    }

    @Override
    public Issue read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Issue> readAll() throws SQLException {
        final String sql = "SELECT issue_id, priority_name, assignee, type_name, status_name, summary FROM issues";
        ArrayList<Issue> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Issue issue = new Issue();
                    issue.setCreateDate(rs.getDate("create_date"));
                    issue.setCreatedBy(rs.getString("created_by"));
                    issue.setModifyDate(rs.getDate("modify_date"));
                    issue.setModifiedBy(rs.getString("modified_by"));
                    issue.setSummary(rs.getString("summary"));
                    issue.setDescription(rs.getString("description"));
                    issue.setStatus(rs.getInt("status"));
                    issue.setResolution(rs.getInt("resolution"));
                    issue.setType(rs.getInt("type"));
                    issue.setType(rs.getInt("priority"));
                    issue.setProject(rs.getInt("project"));
                    issue.setAssignee(rs.getString("assignee"));
                    list.add(issue);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Issue entity) throws SQLException {
        final String updateIssue = "update issues set summary=?, description=?, status=?, resolution=?, type=?, " +
                "priority=?, project=?, assignee=?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getSummary());
        sqlParams.put(2, entity.getDescription());
        sqlParams.put(3, entity.getStatus());
        sqlParams.put(4, entity.getResolution());
        sqlParams.put(5, entity.getType());
        sqlParams.put(6, entity.getPriority());
        sqlParams.put(7, entity.getProject());
        sqlParams.put(8, entity.getAssignee());
        new DBConnection().executeUpdate(connection, updateIssue, sqlParams);
    }

    @Override
    public void delete(Issue entity) throws SQLException {

    }
}
