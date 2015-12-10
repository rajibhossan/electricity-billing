/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static ConnectionManager instance = null;
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String H_CONN_STRING = "jdbc:hsqldb:data/explorecalifornia";
    private final String M_CONN_STRING = "jdbc:mysql://localhost:3306/user";
    private final String O_CONN_STRING = "jdbc:mysql://localhost/explorecalifornia";
    private final String S_CONN_STRING = "jdbc:mysql://localhost/explorecalifornia";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private DBType dbType = DBType.MYSQL;
    private Connection conn = null;

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public void setDBType(DBType dbType) {
        this.dbType = dbType;
    }

    private boolean openConnection() throws ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
            switch (dbType) {
                case MYSQL:
                    conn = DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
                    return true;
                case ORACLE:
                    conn = DriverManager.getConnection(O_CONN_STRING, USERNAME, PASSWORD);
                    return true;
                case MSSQL:
                    conn = DriverManager.getConnection(S_CONN_STRING, USERNAME, PASSWORD);
                    return true;
                case HSQLDB:
                    conn = DriverManager.getConnection(H_CONN_STRING, USERNAME, PASSWORD);
                    return true;
                default:
                    return false;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }

    }

    public String getConnection() throws ClassNotFoundException {
        if (conn == null) {
            if (openConnection()) {
                // System.out.println("Connection opened");
                return "Connection opened";
            } else {
                return "Connection Not opened";
            }
        }
        return null;
    }

    public String close() {

        try {
            conn.close();
            conn = null;
        } catch (Exception e) {
        }
        return "Closing connection";
    }

}
