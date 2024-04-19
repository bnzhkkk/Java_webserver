package db;

import org.h2.jdbcx.JdbcDataSource;
import accounts.UserProfile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private Connection connection;

    public DBService() throws SQLException {
        this.connection = getH2Connection();
    }


    private static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "test";
            String pass = "test";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create() throws Exception {
        try {
            System.out.println("Creating table users if needed");
            (new UsersDAO(connection)).createTable();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public long addUser(UserProfile userProfile) throws Exception {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.insertUser(userProfile);
            connection.commit();
            return dao.getUserId(userProfile.getLogin());
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new Exception(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public UserProfile getUser(String login) throws Exception {
        try {
            UsersDAO dao = new UsersDAO(connection);
            UsersDataSet dataSet = dao.get(login);
            return new UserProfile(dataSet.getLogin(), dataSet.getPassword());
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public void cleanUp() throws Exception {
        UsersDAO dao = new UsersDAO(connection);
        try {
            dao.cleanup();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

}