/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection.Manager.Tables;

import Connection.Manager.DBUtil;
import Connection.Testing.Beans.UserBilling;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserBillingManager {

    static Connection conn = null;
    static PreparedStatement stmt = null;
    static Statement stmts = null;
    static ResultSet rs = null;

    public static boolean insert(UserBilling bean) throws Exception {

        String sql = "INSERT into userbilling (userID,type,total_unit,cost,pdate)"
                + "VALUES (?, ?,?,?,?)";
        ResultSet keys = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,bean.getUserId());
            stmt.setString(2,bean.getType());
            stmt.setInt(3,bean.getTotalUnit());
            stmt.setInt(4,bean.getCost());            
            stmt.setString(5,bean.getPdate());
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                keys = stmt.getGeneratedKeys();
                keys.next();
                String newKey = keys.getString(1);
                bean.setUserId(newKey);
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

    public static UserBilling[] getAllRow() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM userbilling ";
                UserBilling ucontrol[] = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.last();
            int i = rs.getRow();
            if (i == 0) {
                return null;
            } else {
                ucontrol = new UserBilling[i];
                rs.beforeFirst();
                int b = 0;
                while (rs.next()) {
                    UserBilling user = new UserBilling();
                    user.setUserId(rs.getString("userID"));
                    user.setType(rs.getString("type"));
                    user.setCost(rs.getInt("cost"));
                    user.setTotalUnit(rs.getInt("total_unit"));
                    user.setPdate(rs.getString("pdate"));
                    ucontrol[b] = user;
                    b++;

                }
                return ucontrol;
            }

        } catch (SQLException e) {
            DBUtil.processException(e);
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
}
