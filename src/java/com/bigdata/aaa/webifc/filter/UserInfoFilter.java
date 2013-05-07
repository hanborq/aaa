package com.bigdata.aaa.webifc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bigdata.aaa.db.User;

import edu.yale.its.tp.cas.client.filter.CASFilter;

public class UserInfoFilter implements Filter {

    public static Logger LOG = Logger.getLogger(UserInfoFilter.class);

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        if (!(request instanceof HttpServletRequest)) {
            LOG.info("Error request instance : "+request.getClass().getName());
            throw new ServletException("Error request instance : "+request.getClass().getName());
        }
        
        HttpServletRequest req = (HttpServletRequest)request;
        User user = (User)req.getSession().getAttribute("USER");
        if (user == null) {
            String userString = (String) req.getSession().getAttribute(
                    CASFilter.CAS_FILTER_USER);
            LOG.info("Deserialize new User : " + userString);
            user = User.deserialize(userString);
            req.getSession().setAttribute("USER", user);
        }

        chain.doFilter(request, response);
        return;
    }

    @Override
    public void init(FilterConfig conf) throws ServletException {
        LOG.info("Init Fileter : "+this.getClass().getName());
    }

}
