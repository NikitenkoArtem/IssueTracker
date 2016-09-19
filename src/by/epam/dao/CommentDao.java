package by.epam.dao;

import by.epam.DBConnection;
import by.epam.GenericDao;
import by.epam.entity.Comment;

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
public class CommentDao implements GenericDao<Comment, Integer> {
    private Connection connection;

    public CommentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Comment entity) throws SQLException {
        final String sql = "INSERT INTO comments(added_by, add_date, comment) VALUES(?, ?, ?)";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getAddedBy());
        sqlParams.put(2, entity.getAddDate());
        sqlParams.put(3, entity.getComment());
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
        comment.setAddedBy(rs.getString("added_by"));
        comment.setAddDate(rs.getDate("add_date"));
        comment.setComment(rs.getString("comment"));
    }
}
