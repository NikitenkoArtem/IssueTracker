package by.itracker.dao;

import by.itracker.entity.Build;
import by.itracker.entity.Project;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * Created by Price on 02.10.2016.
 */
public class BuildDaoTest {

    private static Connection connection;

    @BeforeClass
    public static void setUpClass() throws Exception {
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        clazz.newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/itracker", "root", "root");
    }

    @Test(expected = NullPointerException.class)
    public void create() throws Exception {
        Build build = new Build();
        BuildDao buildDao = new BuildDao(connection, Build.class);
        buildDao.create(build);
    }

    @Test
    public void createBuild() throws Exception {
        Build build = new Build();
        build.setBuild("test build");
        Project project = new Project();
        project.setProjectId(1);
        build.setProjectId(project);
        BuildDao buildDao = new BuildDao(connection, Build.class);
        Assert.assertNull(buildDao.create(build));
    }

    @Test
    public void readByNull() throws Exception {
        Build build = new BuildDao(connection, Build.class).read(null);
        readIsNull(build, "ReadByNull");
    }

    @Test
    public void readByIdNull() throws Exception {
        Build build = new BuildDao(connection, Build.class).read(-100);
        readIsNull(build, "ReadByIdNull");
    }

    @Test
    public void read() throws Exception {
        Build build = new BuildDao(connection, Build.class).read(1);
        readNotNull(build, "Read");
    }

    @Test
    public void readByProject() throws Exception {
        List<Build> builds = new BuildDao(connection, Build.class).readByProject(1);
        readNotNull(builds, "readByProject");
    }

    @Test
    public void readAll() throws Exception {
        List<Build> builds = new BuildDao(connection, Build.class).readAll();
        readNotNull(builds, "readAll");
    }

    private void readNotNull(Build build, String methodName) {
        System.out.println(methodName + "\t\tID:" + build.getBuildId() + "\tBUILD:" + build.getBuild() + "\tPROJECTID:" + build.getProjectId().getProjectId());
        Assert.assertNotNull(build.getBuildId());
        Assert.assertNotNull(build.getBuild());
        Assert.assertNotNull(build.getProjectId().getProjectId());
    }

    private void readIsNull(Build build, String methodName) {
        System.out.println(methodName + "\t\tID:" + build.getBuildId() + "\tBUILD:" + build.getBuild() + "\tPROJECTID:" + build.getProjectId());
        Assert.assertNull(build.getBuildId());
        Assert.assertNull(build.getBuild());
        Assert.assertNull(build.getProjectId());
    }

    private void readIsNull(List<Build> builds, String methodName) {
        for (Build build : builds) {
            Assert.assertNull(build.getBuildId());
            Assert.assertNull(build.getBuild());
            Assert.assertNull(build.getProjectId().getProjectId());
            System.out.println(methodName + "\t\tID:" + build.getBuildId() + "\tBUILD:" + build.getBuild() + "\tPROJECTID:" + build.getProjectId());
        }
    }

    private void readNotNull(List<Build> builds, String methodName) {
        for (Build build : builds) {
            Assert.assertNotNull(build.getBuildId());
            Assert.assertNotNull(build.getBuild());
            Assert.assertNotNull(build.getProjectId().getProjectId());
            System.out.println(methodName + "\t\tID:" + build.getBuildId() + "\tBUILD:" + build.getBuild() + "\tPROJECTID:" + build.getProjectId().getProjectId());
        }
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void selectRow() throws Exception {

    }
}
