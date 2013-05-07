package com.bigdata.aaa.webifc.action;

import org.apache.log4j.Logger;

import com.bigdata.aaa.db.AccessKeyDB;
import com.bigdata.aaa.db.User;
import com.bigdata.aaa.db.UserDB;
import com.bigdata.aaa.util.MD5HashUtil;
import com.bigdata.aaa.util.Mail;
import com.opensymphony.xwork2.ActionSupport;

public class RegAction extends BaseAction {
    
    public static Logger LOG = Logger.getLogger(RegAction.class);

    private String username;
    private String password;
    private String dispname;
    
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @param dispname the dispname to set
     */
    public void setDispname(String dispname) {
        this.dispname = dispname;
    }
    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
    public String execute() throws Exception {
        
        if (username == null || password == null || dispname == null
                || username.equals("") || password.equals("")
                || dispname.equals("")) {
            LOG.error("Input param error : username = " + username
                    + ", password = " + password + ", dispname = " + dispname);
            setERROR("param error.");
            return SUCCESS;
        }
        
        LOG.info("Req : username = " + username + ", password = " + password
                + ", dispname = " + dispname);
        
        User user = UserDB.scanUser("user_name", username);
        if (user != null) {
            LOG.error("The user already exist : "+user.getUsername());
            setERROR("The user already exist : "+user.getUsername());
            return SUCCESS;
        }
        
        String actKey = MD5HashUtil.hashCode(username + ":" + password + ":"
                + dispname + ":" + System.currentTimeMillis());
        
        try {
            boolean ret = UserDB.insertUser(username, password, dispname, actKey);
            if (ret) {
                LOG.info("create user in DB OK : " + username + ", " + dispname);
            } else {
                LOG.error("create user in DB ERROR : " + username + ", " + dispname);
                setERROR();
                return SUCCESS;
            }
        } catch(Exception e) {
            LOG.error("create user in DB ERROR : " + username + ", " + dispname
                    + ", ERROR = " + e);
            e.printStackTrace();
            setERROR(e.getMessage());
            return SUCCESS;
        }
        
        try {
            if (!Mail.sendActiveMail(username, dispname, actKey)){
                LOG.error("Send email to "+username+" ERROR, rollback.");
                UserDB.deleteUser(username);
                setERROR("Send email ERROR.");
                return SUCCESS;
            } else {
                LOG.info("Send email to "+username+" OK.");
                setOK();
                return SUCCESS;
            }
        } catch (Exception e) {
            LOG.error("Send email to "+username+" ERROR, rollback : "+e);
            UserDB.deleteUser(username);
            setERROR("Send email ERROR.");
            return SUCCESS;
        }
    }
}
