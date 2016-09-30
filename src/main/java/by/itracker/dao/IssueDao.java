package by.itracker.dao;

import by.itracker.DBConnection;
import by.itracker.GenericDao;
import by.itracker.entity.*;

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
        sqlParams.put(7, entity.getStatusId());
        sqlParams.put(8, entity.getResolutionId());
        sqlParams.put(9, entity.getTypeId());
        sqlParams.put(10, entity.getPriorityId());
        sqlParams.put(11, entity.getProjectId());
        sqlParams.put(12, entity.getBuildId());
        sqlParams.put(13, entity.getAssigneeId());
        sqlParams.put(14, entity.getCommentId());
        sqlParams.put(15, entity.getFileId());
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
        final String updateIssue = "UPDATE issues SET summary = ?, description = ?, status_id = ?, resolution_id = ?, " +
                "type_id = ?, priority_id = ?, project_id = ?, build_id = ?, assignee_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getSummary());
        sqlParams.put(2, entity.getDescription());
        sqlParams.put(3, entity.getStatusId());
        sqlParams.put(4, entity.getResolutionId());
        sqlParams.put(5, entity.getTypeId());
        sqlParams.put(6, entity.getPriorityId());
        sqlParams.put(6, entity.getBuildId());
        sqlParams.put(7, entity.getProjectId());
        sqlParams.put(8, entity.getAssigneeId());
        new DBConnection().executeUpdate(connection, updateIssue, sqlParams);
    }

    @Override
    public void delete(Issue entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Issue issue) throws SQLException {
        issue.setIssueId(rs.getInt("issue_id"));
        issue.setCreateDate(rs.getDate("create_date"));
        User createdBy = new User();
        createdBy.setUserId(rs.getInt("created_by"));
        issue.setCreatedBy(createdBy);
        issue.setModifyDate(rs.getDate("modify_date"));
        User modifiedBy = new User();
        modifiedBy.setUserId(rs.getInt("modified_by"));
        issue.setCreatedBy(modifiedBy);
        issue.setModifiedBy(modifiedBy);
        issue.setSummary(rs.getString("summary"));
        issue.setDescription(rs.getString("description"));
        Status status = new Status();
        status.setStatusId(rs.getInt("status_id"));
        issue.setStatusId(status);
        Resolution resolution = new Resolution();
        resolution.setResolutionId(rs.getInt("resolution_id"));
        issue.setResolutionId(resolution);
        Type type = new Type();
        type.setTypeId(rs.getInt("type_id"));
        issue.setTypeId(type);
        Priority priority = new Priority();
        priority.setPriorityId(rs.getInt("priority_id"));
        issue.setPriorityId(priority);
        Project project = new Project();
        project.setProjectId(rs.getInt("project_id"));
        issue.setProjectId(project);
        Build build = new Build();
        build.setBuildId(rs.getInt("build_id"));
        issue.setBuildId(build);
        User assignee = new User();
        assignee.setUserId(rs.getInt("assignee_id"));
        issue.setAssigneeId(assignee);
        Comment comment = new Comment();
        comment.setCommentId(rs.getInt("comment_id"));
        issue.setCommentId(comment);
        File file = new File();
        file.setFileId(rs.getInt("file_id"));
        issue.setFileId(file);
    }
}
