package by.itracker.dao;

import by.itracker.entity.Comment;
import by.itracker.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Price on 17.09.2016.
 */
public class CommentDao extends AbstractGenericDaoImpl<Comment, Integer> {

    public CommentDao(Connection connection, Class<Comment> clazz) {
        super(connection, clazz);
    }

    @Override
    public Integer create(Comment entity) {
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getAddedBy());
        sqlParams.put(2, entity.getAddDate());
        sqlParams.put(3, entity.getComment());
        super.setSql("INSERT INTO comments(added_by, add_date, comment) VALUES(?, ?, ?)");
        super.setSqlParams(sqlParams);
        super.create(entity);
        return entity.getCommentId();
    }

    @Override
    public Comment read(Integer id) {
        super.setSql("SELECT * FROM comments WHERE comment_id = " + id);
        return super.read(id);
    }

    @Override
    public List<Comment> readAll() {
        super.setSql("SELECT * FROM comments");
        return super.readAll();
    }

    @Override
    public void update(Comment entity) {
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getComment());
        sqlParams.put(2, entity.getCommentId());
        super.setSql("UPDATE comments SET comment = ? WHERE comment_id = ?");
        super.setSqlParams(sqlParams);
        super.update(entity);
    }

    @Override
    protected void selectRow(ResultSet rs, Comment entity) throws SQLException {
        entity.setCommentId(rs.getInt("comment_id"));
        User user = new User();
        user.setUserId(rs.getInt("added_by"));
        entity.setAddedBy(user);
        entity.setAddDate(rs.getDate("add_date"));
        entity.setComment(rs.getString("comment"));
    }
    /*
    private Connection connection;

    public CommentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Comment entity) throws SQLException {
        final String sql = "INSERT INTO comments(added_by, add_date, comment) VALUES(?, ?, ?)";

        new DBConnection().executeUpdate(connection, sql, sqlParams);
        return null;
    }

    @Override
    public Comment read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM comments WHERE comment_id = " + id;
        Comment comment = new Comment();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, comment);
                }
            }
        }
        return comment;
    }

    @Override
    public List<Comment> readAll() throws SQLException {
        final String sql = "SELECT * FROM comments";
        ArrayList<Comment> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Comment build = new Comment();
                    selectRow(rs, build);
                    list.add(build);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Comment entity) throws SQLException {
        final String sql = "UPDATE comments SET comment = ? WHERE comment_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getComment());
        sqlParams.put(2, entity.getCommentId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    @Override
    public void delete(Comment entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Comment comment) throws SQLException {
        comment.setCommentId(rs.getInt("comment_id"));
        User user = new User();
        user.setUserId(rs.getInt("added_by"));
        comment.setAddedBy(user);
        comment.setAddDate(rs.getDate("add_date"));
        comment.setComment(rs.getString("comment"));
    }
    */
}
