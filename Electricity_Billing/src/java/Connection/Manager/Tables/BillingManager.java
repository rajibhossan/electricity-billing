/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection.Manager.Tables;

import Connection.Manager.DBType;
import Connection.Manager.DBUtil;
import Connection.Testing.Beans.BillingControl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.NULL;

public class BillingManager {

    static Connection conn = null;
    static PreparedStatement stmt = null;
    static Statement stmts = null;
    static ResultSet rs = null;

    public static boolean insert(BillingControl bean) throws Exception {

        String sql = "INSERT into billing (type,unit,sdate,edate) "
                + "VALUES (?, ?,?,?)";
        ResultSet keys = null;
        try {
            conn = DBUtil.getConnection(DBType.MYSQL);
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, bean.getType());
            stmt.setInt(2, bean.getUnit());
            stmt.setString(3, bean.getSdate().toString());
            if (bean.getEdate().length() == 0) {
                stmt.setNull(4, NULL);
            } else {
                stmt.setString(4, bean.getEdate().toString());
            }
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

    public static BillingControl getRow(String type) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM billing WHERE type = ? && edate IS NULL";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, type);
            rs = stmt.executeQuery();

            if (rs.next()) {
                BillingControl bcontrol = new BillingControl();
                bcontrol.setUnit(rs.getInt("unit"));
                return bcontrol;
            } else {
                return null;
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

    public static BillingControl[] getAllRow() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM billing ";
        BillingControl[] bcontrol;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.wasNull();
            rs.last();
            int i = rs.getRow();

            if (i == 0) {
                return null;
            } else {
                bcontrol = new BillingControl[i];
                rs.beforeFirst();
                int b = 0;

                while (rs.next()) {
                    BillingControl bcon = new BillingControl();
                    bcon.setId(rs.getInt("id"));
                    bcon.setType(rs.getString("type"));
                    bcon.setUnit(rs.getInt("unit"));
                    try {
                        bcon.setSdate(rs.getTimestamp("sdate").toString());
                    } catch (NullPointerException e) {
                        bcon.setSdate("NULL");
                    }
                    try {
                        bcon.setEdate(rs.getTimestamp("edate").toString());
                    } catch (NullPointerException e) {
                        bcon.setEdate("NULL");
                    }
                    bcontrol[b] = bcon;
                    b++;
                }
                return bcontrol;
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

    public static boolean update(BillingControl bean) throws Exception {

        String sql = "UPDATE billing SET edate = ? WHERE id =? && type=?";
        try {
            conn = DBUtil.getConnection(DBType.MYSQL);
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, bean.getEdate());
            stmt.setInt(2, bean.getId());
            stmt.setString(3, bean.getType());

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
