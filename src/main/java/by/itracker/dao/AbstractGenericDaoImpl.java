package by.itracker.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Price on 30.09.2016.
 */
abstract class AbstractGenericDaoImpl<E, PK> implements GenericDao<E, PK> {
    private Connection connection;
    private String sql;
    private Map<Integer, Object> sqlParams;
    private Class<E> clazz;

    public AbstractGenericDaoImpl(Connection connection, Class<E> clazz) {
        this.connection = connection;
        this.clazz = clazz;
    }

    @Override
    public PK create(E entity) {
        try {
            executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public E read(PK id) {
        final E entity = getNewEntityInstance(clazz);
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        selectRow(rs, entity);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<E> readAll() {
        ArrayList<E> list = new ArrayList<>();
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        final E entity = getNewEntityInstance(clazz);
                        selectRow(rs, entity);
                        list.add(entity);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(E entity) {
        try {
            executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(E entity) {

    }

    private int executeUpdate() throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Map.Entry<Integer, Object> entry : sqlParams.entrySet()) {
                final int index = entry.getKey().intValue();
                final Object value = entry.getValue();
                if (value instanceof Integer) {
                    stmt.setInt(index, ((Integer) value).intValue());
                }
                if (value instanceof Date) {
                    stmt.setDate(index, (Date) value);
                }
                if (value instanceof String) {
                    stmt.setString(index, value.toString());
                }
            }
            return stmt.executeUpdate();
//            if (!connection.getAutoCommit()) {
//                connection.commit();
//            }
        }
    }

    private E getNewEntityInstance(Class<E> entityClass) {
        E entity = null;
        try {
            entity = (E) entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return entity;
    }

    protected void selectRow1(ResultSet rs, E entity) throws SQLException {
        final Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.getClass();
        }
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<Integer, Object> getSqlParams() {
        return sqlParams;
    }

    public void setSqlParams(Map<Integer, Object> sqlParams) {
        this.sqlParams = sqlParams;
    }

    protected abstract void selectRow(ResultSet rs, E entity) throws SQLException;
}
