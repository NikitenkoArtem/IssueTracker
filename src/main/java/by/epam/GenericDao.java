package by.epam;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Price on 17.09.2016.
 */
public interface GenericDao <E, PK> {
    PK create(E entity) throws SQLException;
    E read(PK id) throws SQLException;
    List<E> readAll() throws SQLException;
    void update(E entity) throws SQLException;
    void delete(E entity) throws SQLException;
}
