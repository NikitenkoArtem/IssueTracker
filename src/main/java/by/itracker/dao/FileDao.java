package by.itracker.dao;

import by.itracker.DBConnection;
import by.itracker.GenericDao;
import by.itracker.entity.File;
import by.itracker.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Price on 19.09.2016.
 */
public class FileDao implements GenericDao<File, Integer> {
    private Connection connection;

    public FileDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(File entity) throws SQLException {
        final String sql = "INSERT INTO files(added_by, add_date, file_name, content_type, file_size, data) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getAddedBy());
        sqlParams.put(2, entity.getAddDate());
        sqlParams.put(3, entity.getFileName());
        sqlParams.put(4, entity.getContentType());
        sqlParams.put(5, entity.getFileSize());
        sqlParams.put(6, entity.getData());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
        return null;
    }

    @Override
    public File read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM files WHERE file_id = " + id;
        File file = new File();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, file);
                }
            }
        }
        return file;
    }

    @Override
    public List<File> readAll() throws SQLException {
        final String sql = "SELECT * FROM files";
        ArrayList<File> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    File file = new File();
                    selectRow(rs, file);
                    list.add(file);
                }
            }
        }
        return list;
    }

    @Override
    public void update(File entity) throws SQLException {
        final String sql = "UPDATE files SET file_name = ?, content_type = ?, file_size = ? WHERE file_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getFileName());
        sqlParams.put(2, entity.getContentType());
        sqlParams.put(3, entity.getFileSize());
        sqlParams.put(4, entity.getFileId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    @Override
    public void delete(File entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, File file) throws SQLException {
        file.setFileId(rs.getInt("file_id"));
        User user = new User();
        user.setUserId(rs.getInt("added_by"));
        file.setAddedBy(user);
        file.setAddDate(rs.getDate("add_date"));
        file.setFileName(rs.getString("file_name"));
        file.setContentType(rs.getString("content_type"));
        file.setFileSize(rs.getInt("file_size"));
    }
}
