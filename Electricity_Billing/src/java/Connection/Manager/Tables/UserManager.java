/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection.Manager.Tables;

import Connection.Manager.DBType;
import Connection.Manager.DBUtil;
import Connection.Testing.Beans.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserManager {
    static Connection conn = null;
    static PreparedStatement stmt = null;
    static Statement stmts = null;
    static ResultSet rs = null;

    public static void displayAllRows() throws SQLException, ClassNotFoundException {

        String sql = "SELECT id, userName, password FROM usertable";
        try {
            conn = DBUtil.getConnection();
            stmts = conn.createStatement();
            rs = stmt.executeQuery(sql);

            System.out.println("User Table:");
            while (rs.next()) {
                StringBuffer bf = new StringBuffer();
                bf.append(rs.getInt("id"));
                bf.append(rs.getString("userName"));
                bf.append(rs.getString("password"));
                System.out.println(bf.toString());
            }
        } catch (SQLException e) {
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static User getRow(int userid) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM usertable WHERE id = ?";
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userid);
            rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(userid);
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("Password"));
                return user;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static boolean insert(User bean) throws Exception {

        String sql = "INSERT into usertable (userName, password) "
                + "VALUES (?, ?)";
        ResultSet keys = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                keys = stmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                bean.setId(newKey);
            } else {
                System.err.println("No rows affected");
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (keys != null) {
                keys.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return true;
    }
    
    
    public static User Checking(String userName, String password) throws Exception {

        String sql = "SELECT * FROM usertable WHERE userName = ? && password=?";
        ResultSet rs = null;
        

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("Password"));
                return user;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static boolean update(User bean) throws Exception {

        String sql
                = "UPDATE usertable SET "
                + "userName = ?, password = ? "
                + "WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());
            stmt.setInt(3, bean.getId());

            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {

            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }
}
