package com.bigdata.aaa.webifc.servlet;

import java.io.File;
import java.io.IOException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bigdata.aaa.db.User;

public class InitServlet extends HttpServlet {

    public static Logger LOG = Logger.getLogger(InitServlet.class);

    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException {

        LOG.info("Init IninServlet, set default HostnameVerifier.");
        
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
                return true;
            }});
        
        File aaaHome = new File(System.getProperty("catalina.home"))
                .getParentFile();
        String caFile = new File(new File(new File(aaaHome, "conf"), "ssl"),
                "bigdata.aaa.cacerts").getAbsolutePath();
        System.setProperty("javax.net.ssl.trustStore", caFile);
        LOG.info("Set javax.net.ssl.trustStore : "+caFile);
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            User user = (User) req.getSession().getAttribute("USER");
            if (user.getUsertype() == User.ADMIN) {
                LOG.info("Admin : " + user.getUsername());
                resp.sendRedirect("admin/index.jsp");
            } else if (user.getUsertype() == User.ORDINARY) {
                LOG.info("User : " + user.getUsername());
                resp.sendRedirect("user/index.jsp");
            } else {
                LOG.error("Unknown User : " + user.getUsername() + ", type = "
                        + user.getUsertype());
                resp.sendRedirect("error.jsp?msg=Unknown User : " + user.getUsername() + ", type = "
                        + user.getUsertype());
            }
        } catch(Exception e) {
            LOG.error("Get User info ERROR : " + e);
            e.printStackTrace();
            resp.sendRedirect("error.jsp?msg=Get User info ERROR : " + e);
        }
    }
    
}
