package by.itracker.dao;

import by.itracker.entity.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Price on 17.09.2016.
 */
public class IssueDao extends AbstractGenericDaoImpl<Issue, Integer> {
//    private Connection connection;

    public IssueDao(Connection connection, Class<Issue> clazz) {
        super(connection, clazz);
    }

    @Override
    public Integer create(Issue entity) {
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
        super.setSql("INSERT INTO issues(create_date, created_by, modify_date, modified_by, summary, description, " +
                "status, resolution, type, priority, project, build, assignee, comment, file) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        super.setSqlParams(sqlParams);
        return super.create(entity);
    }

    @Override
    public Issue read(Integer id) {
        super.setSql("SELECT * FROM issues WHERE issue_id = " + id);
        return super.read(id);
    }

    @Override
    public List<Issue> readAll() {
//        super.setSql("SELECT * FROM issues");
        super.setSql("SELECT\n" +
                "  i.ISSUE_ID\n" +
                "  , i.CREATE_DATE\n" +
                "  , created_by.FIRST_NAME AS CREATED_BY\n" +
                "  , i.MODIFY_DATE\n" +
                "  , MODIFIED_BY.FIRST_NAME AS MODIFIED_BY\n" +
                "  , i.SUMMARY\n" +
                "  , i.DESCRIPTION\n" +
                "  , s.STATUS_NAME\n" +
                "  , r.RESOLUTION_NAME\n" +
                "  , t.TYPE_NAME\n" +
                "  , p.PRIORITY_NAME\n" +
                "  , proj.PROJECT_NAME\n" +
                "  , b.BUILD\n" +
                "  , assignee_id.FIRST_NAME AS ASSIGNEE\n" +
                "  , c.COMMENT\n" +
                "  , i.FILE_ID\n" +
                "FROM issues i\n" +
                "  INNER JOIN users created_by ON i.CREATED_BY = created_by.USER_ID\n" +
                "  INNER JOIN users modified_by ON i.MODIFIED_BY = modified_by.USER_ID\n" +
                "  INNER JOIN statuses s ON i.STATUS_ID = s.STATUS_ID\n" +
                "  INNER JOIN resolutions r ON i.RESOLUTION_ID = r.RESOLUTION_ID\n" +
                "  INNER JOIN types t ON i.TYPE_ID = t.TYPE_ID\n" +
                "  INNER JOIN priorities p ON i.PRIORITY_ID = p.PRIORITY_ID\n" +
                "  INNER JOIN projects proj ON i.PROJECT_ID = proj.PROJECT_ID\n" +
                "  INNER JOIN builds b ON i.BUILD_ID = b.BUILD_ID\n" +
                "  INNER JOIN users assignee_id ON i.ASSIGNEE_ID = assignee_id.USER_ID\n" +
                "  LEFT JOIN comments c ON i.COMMENT_ID = c.COMMENT_ID");
        return super.readAll();
    }

    @Override
    public void update(Issue entity) {
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
        super.setSql("UPDATE issues SET summary = ?, description = ?, status_id = ?, resolution_id = ?, " +
                "type_id = ?, priority_id = ?, project_id = ?, build_id = ?, assignee_id = ?");
        super.setSqlParams(sqlParams);
        super.update(entity);
    }

    @Override
    protected void selectRow(ResultSet rs, Issue entity) throws SQLException {
        entity.setIssueId(rs.getInt("issue_id"));
        entity.setCreateDate(rs.getDate("create_date"));
        User createdBy = new User();
        createdBy.setUserId(rs.getInt("created_by"));
        entity.setCreatedBy(createdBy);
        entity.setModifyDate(rs.getDate("modify_date"));
        User modifiedBy = new User();
        modifiedBy.setUserId(rs.getInt("modified_by"));
        entity.setCreatedBy(modifiedBy);
        entity.setModifiedBy(modifiedBy);
        entity.setSummary(rs.getString("summary"));
        entity.setDescription(rs.getString("description"));
        Status status = new Status();
        status.setStatusId(rs.getInt("status_id"));
        entity.setStatusId(status);
        Resolution resolution = new Resolution();
        resolution.setResolutionId(rs.getInt("resolution_id"));
        entity.setResolutionId(resolution);
        Type type = new Type();
        type.setTypeId(rs.getInt("type_id"));
        entity.setTypeId(type);
        Priority priority = new Priority();
        priority.setPriorityId(rs.getInt("priority_id"));
        entity.setPriorityId(priority);
        Project project = new Project();
        project.setProjectId(rs.getInt("project_id"));
        entity.setProjectId(project);
        Build build = new Build();
        build.setBuildId(rs.getInt("build_id"));
        entity.setBuildId(build);
        User assignee = new User();
        assignee.setUserId(rs.getInt("assignee_id"));
        entity.setAssigneeId(assignee);
        Comment comment = new Comment();
        comment.setCommentId(rs.getInt("comment_id"));
        entity.setCommentId(comment);
        File file = new File();
        file.setFileId(rs.getInt("file_id"));
        entity.setFileId(file);
    }
/*
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
    }*/
}
