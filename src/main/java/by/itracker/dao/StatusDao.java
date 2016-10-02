package by.itracker.dao;

import by.itracker.dao.generic.AbstractGenericDaoImpl;
import by.itracker.entity.Status;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Price on 17.09.2016.
 */
public class StatusDao extends AbstractGenericDaoImpl<Status, Integer> {
//    private Connection connection;

    public StatusDao(Connection connection, Class<Status> clazz) {
        super(connection, clazz);
    }

    @Override
    public Integer create(Status entity) {
        HashMap<Integer, Object> sqlParam = new HashMap<>();
        sqlParam.put(1, entity.getStatusName());
        super.setSql("INSERT INTO statuses(status_name) VALUES(?)");
        super.setSqlParams(sqlParam);
        return super.create(entity);
    }

    @Override
    public Status read(Integer id) {
        super.setSql("SELECT * FROM statuses WHERE status_id = " + id);
        return super.read(id);
    }

    @Override
    public List<Status> readAll() {
        super.setSql("SELECT * FROM statuses");
        return super.readAll();
    }

    @Override
    public void update(Status entity) {
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getStatusName());
        sqlParams.put(2, entity.getStatusName());
        super.setSql("UPDATE statuses SET status_name = ? WHERE status_id = ?");
        super.setSqlParams(sqlParams);
        super.update(entity);
    }

    @Override
    protected void selectRow(ResultSet rs, Status entity) throws SQLException {
        entity.setStatusId(rs.getInt("status_id"));
        entity.setStatusName(rs.getString("status_name"));
    }

/*    @Override
    public Integer create(Status entity) throws SQLException {
        final String sql = "INSERT INTO statuses(status_name) VALUES(?)";
        HashMap<Integer, Object> sqlParam = new HashMap<>();
        sqlParam.put(1, entity.getStatusName());
        new DBConnection().executeUpdate(connection, sql, sqlParam);
        return null;
    }

    @Override
    public Status read(Integer id) throws SQLException {
        final String sql = "SELECT * FROM statuses WHERE status_id = " + id;
        Status status = new Status();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selectRow(rs, status);
                }
            }
        }
        return status;
    }

    @Override
    public List<Status> readAll() throws SQLException {
        final String sql = "SELECT * FROM statuses";
        ArrayList<Status> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Status status = new Status();
                    selectRow(rs, status);
                    list.add(status);
                }
            }
        }
        return list;
    }

    @Override
    public void update(Status entity) throws SQLException {
        final String updateStatuses = "UPDATE statuses SET status_name = ? WHERE status_id = ?";
        HashMap<Integer, Object> sqlParams = new HashMap<>();
        sqlParams.put(1, entity.getStatusName());
        sqlParams.put(2, entity.getStatusName());
        new DBConnection().executeUpdate(connection, updateStatuses, sqlParams);
    }

    @Override
    public void delete(Status entity) throws SQLException {

    }

    private void selectRow(ResultSet rs, Status status) throws SQLException {
        status.setStatusId(rs.getInt("status_id"));
        status.setStatusName(rs.getString("status_name"));
    }*/
}
