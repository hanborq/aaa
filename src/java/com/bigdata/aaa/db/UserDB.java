package com.bigdata.aaa.db;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bigdata.aaa.util.MD5HashUtil;

public class UserDB {
    
    public static boolean insertUser(String username, String password,
            String dispname, String actKey) throws SQLException {

        long time = System.currentTimeMillis();
        String userID = null;
        String passMd5 = null;
        try {
            userID = MD5HashUtil.hashCode(username + ":" + time);
            passMd5 = MD5HashUtil.hashCode(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO user VALUES('" + userID + "', '" + username
                + "','" + passMd5 + "', '" + dispname + "', 1, " + time
                + ", 0, '" + actKey + "');";
        return DBUtil.exec(sql);
    }
 
    public static boolean existUser(String username) throws SQLException {
        
        Connection conn = DBUtil.ds.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM user WHERE user_name='"+username+"';";
            rs = stmt.executeQuery(sql);
            return rs.next();
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
    
    public static boolean deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM user WHERE user_name='"+username+"';";
        return DBUtil.exec(sql);
    }
    
    public static boolean activateUser(String actKey) throws SQLException {
        User user = scanUser("act_key", actKey);
        
        if (user.getActTime() != 0)
            return false;
        
        String sql = "UPDATE user SET act_time=" + System.currentTimeMillis()
                + " WHERE user_id='" + user.getUserID() + "';";
        return DBUtil.exec(sql);
    }

    public static boolean changePwd(String username, String password) throws SQLException {
        
        String pwdMd5 = null;
        try {
            pwdMd5 = MD5HashUtil.hashCode(password);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        String sql = "UPDATE user SET password='"
                + pwdMd5 + "' WHERE user_name='"
                + username + "';";
        return DBUtil.exec(sql);
    }
    
    public static User scanUser(String field, String value) throws SQLException {
        Connection conn = DBUtil.ds.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM user WHERE "+field+"='"+value+"';";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("user_id")); 
                user.setUsername(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setDispname(rs.getString("disp_name"));
                user.setRegTime(rs.getLong("reg_time"));
                user.setActTime(rs.getLong("act_time"));
                user.setUsertype(rs.getInt("user_type"));
                return user;
            } else {
                return null;
            }
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
    
    public static User scanUserWithConsoleKey(String field, String value) throws SQLException {
        Connection conn = DBUtil.ds.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT user.user_id, user.user_name, user.password, user.disp_name, user.reg_time, user.act_time, user.user_type, access_keys.access_key, access_keys.security_key FROM user, access_keys WHERE "
                    + field
                    + "='"
                    + value
                    + "' AND access_keys.status=2 AND user.user_id=access_keys.user_id;";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("user_id")); 
                user.setUsername(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setDispname(rs.getString("disp_name"));
                user.setRegTime(rs.getLong("reg_time"));
                user.setActTime(rs.getLong("act_time"));
                user.setUsertype(rs.getInt("user_type"));
                user.setConsoleAccessKey(rs.getString("access_key"));
                user.setConsoleSecurityKey(rs.getString("security_key"));
                return user;
            } else {
                return null;
            }
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
}
