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
        final String sql = "INSERT INTO issues(create_date, created_by, modify_date, modified_by, summary, description, " +
                "status, resolution, type, priority, project, build, assignee, comment, file) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getCreateDate());
        sqlParams.put(2, entity.getCreatedBy());
        sqlParams.put(3, entity.getModifyDate());
        sqlParams.put(4, entity.getModifiedBy());
        sqlParams.put(5, entity.getSummary());
        sqlParams.put(6, entity.getDescription());
        sqlParams.put(7, entity.getStatus());
        sqlParams.put(8, entity.getResolution());
        sqlParams.put(9, entity.getType());
        sqlParams.put(10, entity.getPriority());
        sqlParams.put(11, entity.getProject());
        sqlParams.put(12, entity.getBuild());
        sqlParams.put(13, entity.getAssignee());
        sqlParams.put(14, entity.getComment());
        sqlParams.put(15, entity.getFile());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
        return null;
    }

    @Override
    public Issue read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM issues WHERE issue_id = " + id;
        Issue issue = new Issue();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, issue);
                }
            }
        }
        return issue;
    }

    @Override
    public List<Issue> readAll() throws SQLException {
        final String sql = "SELECT * FROM issues";
        ArrayList<Issue> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Issue issue = new Issue();
                    selectRow(rs, issue);
                    list.add(issue);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Issue entity) throws SQLException {
        final String updateIssue = "UPDATE issues SET summary = ?, description = ?, status = ?, resolution = ?, " +
                "type = ?, priority = ?, project = ?, build = ?, assignee = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getSummary());
        sqlParams.put(2, entity.getDescription());
        sqlParams.put(3, entity.getStatus());
        sqlParams.put(4, entity.getResolution());
        sqlParams.put(5, entity.getType());
        sqlParams.put(6, entity.getPriority());
        sqlParams.put(6, entity.getBuild());
        sqlParams.put(7, entity.getProject());
        sqlParams.put(8, entity.getAssignee());
        new DBConnection().executeUpdate(connection, updateIssue, sqlParams);
    }

    @Override
    public void delete(Issue entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Issue issue) throws SQLException {
        issue.setIssueId(rs.getInt("issue_id"));
        issue.setCreateDate(rs.getDate("create_date"));
        issue.setCreatedBy(rs.getInt("created_by"));
        issue.setModifyDate(rs.getDate("modify_date"));
        issue.setModifiedBy(rs.getInt("modified_by"));
        issue.setSummary(rs.getString("summary"));
        issue.setDescription(rs.getString("description"));
        issue.setStatus(rs.getInt("status"));
        issue.setResolution(rs.getInt("resolution"));
        issue.setType(rs.getInt("type"));
        issue.setPriority(rs.getInt("priority"));
        issue.setProject(rs.getInt("project"));
        issue.setBuild(rs.getInt("build"));
        issue.setAssignee(rs.getInt("assignee"));
        issue.setComment(rs.getInt("comment"));
        issue.setFile(rs.getInt("file"));
    }
}
