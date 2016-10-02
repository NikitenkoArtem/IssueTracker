package by.itracker.dao;

import by.itracker.entity.Type;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.*;

/**
 * Created by Price on 02.10.2016.
 */
public class TypeDaoTest {
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        clazz.newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/itracker", "root", "root");
    }

    @Test
    public void create() throws Exception {
        Type type = new Type();
        type.setTypeName("test type");
        new TypeDao(connection, Type.class).create(type);
    }

    @Test
    public void read() throws Exception {

    }

    @Test
    public void readAll() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void selectRow() throws Exception {

    }

}