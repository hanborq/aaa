package com.bigdata.aaa.db;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bigdata.aaa.util.MD5HashUtil;

public class AccessKeyDB {

    public static List<AccessKey> scanAccessKey(String userID) throws SQLException {
        Connection conn = DBUtil.ds.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM access_keys WHERE user_id='" + userID
                    + "' AND status!=" + AccessKey.CONSOLE + ";";
            rs = stmt.executeQuery(sql);
            List<AccessKey> list = new ArrayList<AccessKey>();
            while (rs.next()) {
                AccessKey ak = new AccessKey();
                ak.setUserID(userID);
                ak.setAccessKey(rs.getString("access_key"));
                ak.setSecurityKey(rs.getString("security_key"));
                ak.setCreateTime(rs.getLong("create_time"));
                ak.setStatus(rs.getInt("status"));
                list.add(ak);
            }
            return list;
        } catch(SQLException e) {
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
    }
    
    public static boolean newAccessKey(User user, boolean init) throws SQLException {
        String accessKey = null;
        String securityKey = null;
        String consoleAccessKey = null;
        String consolesecurityKey = null;
        try {
            accessKey = MD5HashUtil.hashCode(
                    user.getUserID() + System.currentTimeMillis()).substring(0,
                    20);
            securityKey = MD5HashUtil.hashCode(
                    user.getUsername() + System.currentTimeMillis()
                            + Math.random()).substring(0, 20)
                    + MD5HashUtil.hashCode(
                            user.getPassword() + System.currentTimeMillis()
                                    + Math.random()).substring(0, 20);
            if (init) {
                consoleAccessKey = MD5HashUtil.hashCode("Console"+
                        user.getUserID() + System.currentTimeMillis()).substring(0,
                        20);
                consolesecurityKey = MD5HashUtil.hashCode("Console"+
                        user.getUsername() + System.currentTimeMillis()
                                + Math.random()).substring(0, 20)
                        + MD5HashUtil.hashCode("Console"+
                                user.getPassword() + System.currentTimeMillis()
                                        + Math.random()).substring(0, 20);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO access_keys values('" + user.getUserID() + "', '"
                + accessKey + "', '" + securityKey + "', "
                + System.currentTimeMillis() + "," + AccessKey.ACTIVATY + ");";
        DBUtil.exec(sql);
        
        if (init) {
            sql = "INSERT INTO access_keys values('" + user.getUserID() + "', '"
            + consoleAccessKey + "', '" + consolesecurityKey + "', "
            + System.currentTimeMillis() + "," + AccessKey.CONSOLE + ");";
            DBUtil.exec(sql);
        }
        return true;
    }
    
    public static boolean delAccessKey(String accessKey) throws SQLException {
        String sql = "DELETE FROM access_keys WHERE access_key='" + accessKey
                + "';";
        return DBUtil.exec(sql);
    }
    
    public static boolean activationAccessKey(String accessKey)
            throws SQLException {
        String sql = "UPDATE access_keys SET status=" + AccessKey.ACTIVATY
                + " WHERE access_key='" + accessKey + "';";
        return DBUtil.exec(sql);
    }
    
    public static boolean deactivationAccessKey(String accessKey) throws SQLException {
        String sql = "UPDATE access_keys SET status=" + AccessKey.DEACTIVATY
                + " WHERE access_key='" + accessKey + "';";
        return DBUtil.exec(sql);
    }
}
