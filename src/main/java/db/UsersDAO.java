package db;

import accounts.UserProfile;
import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {
    private db.executor.Executor exec;

    public UsersDAO(Connection connection) {
        this.exec = new db.executor.Executor(connection);
    }

    public void createTable() throws SQLException {
        exec.execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(256), primary key (id))");
    }

    public void insertUser(UserProfile profile) throws SQLException {
        exec.execUpdate("insert into users (login, password) values ('" + profile.getLogin() + "','" + profile.getPassword() + "')");
    }

    public UsersDataSet get(String login) throws SQLException {
        return exec.execQuery("select * from users where login='" + login + "'", result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2), result.getString(3));
        });
    }

    public long getUserId(String login) throws SQLException {
        return exec.execQuery("select id from users where login='" + login + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void cleanup() throws SQLException {
        exec.execUpdate("drop table users");
    }

}