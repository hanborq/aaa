package com.bigdata.aaa.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBUtil {

    public static Logger LOG = Logger.getLogger(DBUtil.class);
    
    static InitialContext ctx;
    static DataSource ds;
    static{
        try {
            ctx=new InitialContext();
            LOG.info("Init InitialContext OK.");
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
            if (ds == null) {
                LOG.fatal("Cannot init DataSource, exit.");
                System.exit(-1);
            } else {
                LOG.info("Init DataSource OK.");
            }
        } catch (NamingException e) {
            LOG.fatal("Cannot init InitialContext, exit : "+e);
            System.exit(-1);
        }
    }
    
    static boolean exec(String sql) throws SQLException {
        Connection conn = ds.getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        } catch(SQLException e) {
            throw e;
        } finally {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
    }
}
