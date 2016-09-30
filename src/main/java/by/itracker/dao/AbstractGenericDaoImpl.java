package by.itracker.dao;

import by.itracker.GenericDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Price on 30.09.2016.
 */
abstract class AbstractGenericDaoImpl<E, PK> implements GenericDao<E, PK> {
    private Connection connection;

    @Override
    public PK create(E entity) throws SQLException {
        return null;
    }

    @Override
    public E read(PK id) throws SQLException {
        return null;
    }

    @Override
    public List<E> readAll() throws SQLException {
        return null;
    }

    @Override
    public void update(E entity) throws SQLException {

    }

    @Override
    public void delete(E entity) throws SQLException {

    }
}
