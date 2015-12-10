/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sadika
 */
public class DBUtil {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String H_CONN_STRING = "jdbc:hsqldb:data/explorecalifornia";
    private static final String M_CONN_STRING = "jdbc:mysql://localhost:3306/user";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String O_CONN_STRING = "jdbc:mysql://localhost/explorecalifornia";
    private static final String S_CONN_STRING = "jdbc:mysql://localhost/explorecalifornia";
    private static final String M_CONN_STRING2 = "jdbc:mysql://localhost:3306/user?zeroDateTimeBehavior=convertToNull";
    private static final DBType dbType = DBType.MYSQL;

    public static Connection getConnection(DBType dbType) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        switch (dbType) {
            case MYSQL:
                return DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
            case HSQLDB:
                return DriverManager.getConnection(H_CONN_STRING, USERNAME, PASSWORD);
            case MSSQL:
                return DriverManager.getConnection(S_CONN_STRING, USERNAME, PASSWORD);
            case ORACLE:
                return DriverManager.getConnection(O_CONN_STRING, USERNAME, PASSWORD);
            default:
                return null;
        }

    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        switch (dbType) {
            case MYSQL:
                return DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
            case HSQLDB:
                return DriverManager.getConnection(H_CONN_STRING, USERNAME, PASSWORD);
            case MSSQL:
                return DriverManager.getConnection(S_CONN_STRING, USERNAME, PASSWORD);
            case ORACLE:
                return DriverManager.getConnection(O_CONN_STRING, USERNAME, PASSWORD);
            default:
                return null;
        }
    }

    public static Connection getConnection2() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        switch (dbType) {
            case MYSQL:
                return DriverManager.getConnection(M_CONN_STRING2, USERNAME, PASSWORD);
            case HSQLDB:
                return DriverManager.getConnection(H_CONN_STRING, USERNAME, PASSWORD);
            case MSSQL:
                return DriverManager.getConnection(S_CONN_STRING, USERNAME, PASSWORD);
            case ORACLE:
                return DriverManager.getConnection(O_CONN_STRING, USERNAME, PASSWORD);
            default:
                return null;

        }

    }

    public static void processException(SQLException e) {
        System.err.println("Error message: " + e.getMessage());
        System.err.println("Error code: " + e.getErrorCode());
        System.err.println("SQL state: " + e.getSQLState());
    }
}
