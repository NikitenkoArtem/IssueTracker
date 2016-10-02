package by.itracker.dao;

import by.itracker.dao.generic.AbstractGenericDaoImpl;
import by.itracker.entity.Type;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Price on 17.09.2016.
 */
public class TypeDao extends AbstractGenericDaoImpl<Type, Integer> {
//    private Connection connection;

    public TypeDao(Connection connection, Class<Type> clazz) {
        super(connection, clazz);
    }

    @Override
    public Integer create(Type entity) {
        HashMap<Integer, Object> sqlParam = new HashMap<>();
        sqlParam.put(1, entity.getTypeName());
        super.setSql("INSERT INTO types(type_name) VALUES(?)");
        super.setSqlParams(sqlParam);
        return super.create(entity);
    }

    @Override
    public Type read(Integer id) {
        super.setSql("SELECT * FROM types WHERE type_id = " + id);
        return super.read(id);
    }

    @Override
    public List<Type> readAll() {
        super.setSql("SELECT * FROM types");
        return super.readAll();
    }

    @Override
    public void update(Type entity) {
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getTypeName());
        sqlParams.put(2, entity.getTypeId());
        super.setSql("UPDATE types SET type_name = ? WHERE type_id = ?");
        super.setSqlParams(sqlParams);
        super.update(entity);
    }

    @Override
    protected void selectRow(ResultSet rs, Type entity) throws SQLException {
        entity.setTypeId(rs.getInt("type_id"));
        entity.setTypeName(rs.getString("type_name"));
    }
/*
    @Override
    public Integer create(Type entity) throws SQLException {
        final String sql = "INSERT INTO types(type_name) VALUES(?)";
        HashMap<Integer, Object> sqlParam = new HashMap<>();
        sqlParam.put(1, entity.getTypeName());
        new DBConnection().executeUpdate(connection, sql, sqlParam);
        return null;
    }

    @Override
    public Type read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM types WHERE type_id = " + id;
        Type type = new Type();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, type);
                }
            }
        }
        return type;
    }

    @Override
    public List<Type> readAll() throws SQLException {
        final String sql = "SELECT * FROM types";
        ArrayList<Type> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Type type = new Type();
                    selectRow(rs, type);
                    list.add(type);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Type entity) throws SQLException {
        final String sql = "UPDATE types SET type_name = ? WHERE type_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getTypeName());
        sqlParams.put(2, entity.getTypeId());
        new DBConnection().executeUpdate(connection, sql, sqlParams);
    }

    @Override
    public void delete(Type entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Type type) throws SQLException {
        type.setTypeId(rs.getInt("type_id"));
        type.setTypeName(rs.getString("type_name"));
    }*/
}
