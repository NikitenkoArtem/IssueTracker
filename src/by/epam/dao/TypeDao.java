package by.epam.dao;

import by.epam.DBConnection;
import by.epam.GenericDao;
import by.epam.entity.Type;

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
public class TypeDao implements GenericDao<Type, Integer> {
    private Connection connection;

    public TypeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Type entity) throws SQLException {
        final String sql = "INSERT INTO types VALUES(?)";
        HashMap<Integer, Object> param = new HashMap<>();
        param.put(1, entity.getTypeName());
        new DBConnection().executeUpdate(connection, sql, param);
        return entity.getTypeId();
    }

    @Override
    public Type read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Type> readAll() throws SQLException {
        final String sql = "SELECT type_name FROM types";
        ArrayList<Type> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Type resolution = new Type();
                    resolution.setTypeName(rs.getString("type_name"));
                    list.add(resolution);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Type entity) throws SQLException {
        final String sql = "UPDATE types SET type_name = ? WHERE type_id = ?";
        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, entity.getTypeName());
        params.put(2, entity.getTypeId());
        new DBConnection().executeUpdate(connection, sql, params);
    }

    @Override
    public void delete(Type entity) throws SQLException {

    }
}
