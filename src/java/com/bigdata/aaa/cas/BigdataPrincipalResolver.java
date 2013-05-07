package com.bigdata.aaa.cas;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.CredentialsToPrincipalResolver;
import org.jasig.cas.authentication.principal.Principal;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

import com.bigdata.aaa.db.User;
import com.bigdata.aaa.db.UserDB;

public class BigdataPrincipalResolver implements
        CredentialsToPrincipalResolver {

    public static Logger LOG = Logger.getLogger(BigdataPrincipalResolver.class);

    @Override
    public Principal resolvePrincipal(Credentials credentials) {
        final String principalId = ((UsernamePasswordCredentials)credentials).getUsername();
        LOG.info("principalId = " + principalId);
        try {
            User user = UserDB.scanUserWithConsoleKey("user_name", principalId);
            String ret = User.serialize(user);
            return new SimplePrincipal(ret);
        } catch (SQLException e) {
            LOG.info("fetch user ERROR : " + e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean supports(final Credentials credentials) {
        return credentials != null
            && UsernamePasswordCredentials.class.isAssignableFrom(credentials
                .getClass());
    }

}
