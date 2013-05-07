package com.bigdata.aaa.webifc.action;

import org.apache.log4j.Logger;

import com.bigdata.aaa.db.AccessKeyDB;
import com.bigdata.aaa.db.User;
import com.bigdata.aaa.db.UserDB;

public class RegActAction extends BaseAction {

    public static Logger LOG = Logger.getLogger(RegActAction.class);

    private String key;

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
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

        if (key == null || key.equals("")) {
            LOG.error("Input param error : key = " + key);
            return ERROR;
        }
        
        LOG.info("Req : key = " + key);
        
        try {
            if (UserDB.activateUser(key)) {
                LOG.info("Activate OK : key = " + key);
                User user = UserDB.scanUser("act_key", key);
                try {
                    AccessKeyDB.newAccessKey(user, true);
                    LOG.info("Gen new access key for : "+user.getUsername());
                } catch(Exception e) {
                    LOG.error("Gen new access key ERROR for : "+user.getUsername()+" : "+e);
                }
                return SUCCESS;
            } else {
                setERROR("activate user error.");
                LOG.error("Activate ERROR : key = " + key);
                setERROR("Activate ERROR : key = " + key);
                return ERROR;
            }
        } catch(Exception e) {
            LOG.error("Act user "+key+" catch exception : "+e);
            setERROR(e.getMessage());
            return ERROR;
        }
    }

}
