package by.itracker.dao;

import by.itracker.dao.generic.AbstractGenericDaoImpl;
import by.itracker.entity.Priority;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Price on 17.09.2016.
 */
public class PriorityDao extends AbstractGenericDaoImpl<Priority, Integer> {
//    private Connection connection;

    public PriorityDao(Connection connection, Class<Priority> clazz) {
        super(connection, clazz);
    }

    @Override
    public Integer create(Priority entity) {
        HashMap<Integer, Object> sqlParam = new HashMap<>();
        sqlParam.put(1, entity.getPriorityName());
        super.setSql("INSERT INTO priorities(priority_name) VALUES(?)");
        super.setSqlParams(sqlParam);
        return super.create(entity);
    }

    @Override
    public Priority read(Integer id) {
        super.setSql("SELECT * FROM priorities WHERE priority_id = " + id);
        return super.read(id);
    }

    @Override
    public List<Priority> readAll() {
        super.setSql("SELECT * FROM priorities");
        return super.readAll();
    }

    @Override
    public void update(Priority entity) {
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getPriorityName());
        sqlParams.put(2, entity.getPriorityId());
        super.setSql("UPDATE projects SET priority_name = ? WHERE priority_id = ?");
        super.setSqlParams(sqlParams);
        super.update(entity);
    }

    @Override
    protected void selectRow(ResultSet rs, Priority entity) throws SQLException {
        entity.setPriorityId(rs.getInt("priority_id"));
        entity.setPriorityName(rs.getString("priority_name"));
    }

/*    @Override
    public Integer create(Priority entity) throws SQLException {
        final String sql = "INSERT INTO priorities(priority_name) VALUES(?)";
        HashMap<Integer, Object> sqlParam = new HashMap<>();
        sqlParam.put(1, entity.getPriorityName());
        new DBConnection().executeUpdate(connection, sql, sqlParam);
        return null;
    }

    @Override
    public Priority read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM priorities WHERE priority_id = " + id;
        Priority priority = new Priority();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, priority);
                }
            }
        }
        return priority;
    }

    @Override
    public List<Priority> readAll() throws SQLException {
        final String sql = "SELECT * FROM priorities";
        ArrayList<Priority> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Priority priority = new Priority();
                    selectRow(rs, priority);
                    list.add(priority);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Priority entity) throws SQLException {
        final String updatePriority = "UPDATE projects SET priority_name = ? WHERE priority_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getPriorityName());
        sqlParams.put(2, entity.getPriorityId());
        new DBConnection().executeUpdate(connection, updatePriority, sqlParams);
    }

    @Override
    public void delete(Priority entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Priority priority) throws SQLException {
        priority.setPriorityId(rs.getInt("priority_id"));
        priority.setPriorityName(rs.getString("priority_name"));
    }*/
}
