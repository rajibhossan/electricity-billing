package Connection.Manager.Tables;

import Connection.Manager.DBType;
import Connection.Manager.DBUtil;
import static Connection.Manager.Tables.UserBillingManager.conn;
import Connection.Testing.Beans.BillType;
import Connection.Testing.Beans.UserBilling;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BillTypeManager {
    
    static Connection conn = null;
    static PreparedStatement stmt = null;
    static Statement stmts = null;
    static ResultSet rs = null;
    
    public static boolean insert(BillType bean) throws Exception {
        
        String sql = "INSERT into type (billType) VALUES (?)";
        ResultSet keys = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, bean.getBillType());
            int affected = stmt.executeUpdate();
            
            if (affected == 1) {
                keys = stmt.getGeneratedKeys();
                keys.next();
                String newKey = keys.getString(1);
                bean.setId(Integer.valueOf(newKey));
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
    
    public static BillType[] getAllRow() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM type ";
                BillType ucontrol[];

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.last();
            int i = rs.getRow();
            if (i == 0) {
                return null;
            } else {
                ucontrol = new BillType[i];
                rs.beforeFirst();
                int b = 0;
                while (rs.next()) {
                    BillType user = new BillType();
                    user.setBillType(rs.getString("billtype"));                    
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
