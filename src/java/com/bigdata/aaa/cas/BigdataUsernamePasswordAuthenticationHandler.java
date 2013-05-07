package com.bigdata.aaa.cas;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

import com.bigdata.aaa.db.User;
import com.bigdata.aaa.db.UserDB;
import com.bigdata.aaa.util.MD5HashUtil;

public class BigdataUsernamePasswordAuthenticationHandler extends
        AbstractUsernamePasswordAuthenticationHandler {

    public static Logger LOG = Logger.getLogger(BigdataUsernamePasswordAuthenticationHandler.class);

    @Override
    protected boolean authenticateUsernamePasswordInternal(
            UsernamePasswordCredentials credentials) throws AuthenticationException {

        final String username = credentials.getUsername();
        final String password = credentials.getPassword();

        try {
            User user = UserDB.scanUser("user_name", username);
            if (user == null) {
                LOG.error("Cannot find user : "+username);
                return false;
            }
            if (user.getActTime() == 0) {
                LOG.error("Not activated user : "+username);
                return false;
            }
            String pasMd5 = null;
            try {
                pasMd5 = MD5HashUtil.hashCode(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            if (!pasMd5.equals(user.getPassword())) {
                LOG.error("Password not match, user : "+username);
                return false;
            } 
            LOG.info("Login OK, user : "+username);
            return true;
            
        } catch (SQLException e) {
            LOG.error("Scan user in DB ERROR : " + e);
            e.printStackTrace();
            return false;
        }
    }

}
