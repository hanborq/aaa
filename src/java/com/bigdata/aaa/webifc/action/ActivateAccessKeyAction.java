package com.bigdata.aaa.webifc.action;

import org.apache.log4j.Logger;

import com.bigdata.aaa.db.AccessKeyDB;
import com.bigdata.aaa.db.User;
import com.opensymphony.xwork2.ActionContext;

public class ActivateAccessKeyAction extends BaseAction {
    
    public static Logger LOG = Logger.getLogger(ActivateAccessKeyAction.class);

    private String accesskey;

    /**
     * @param accesskey the accesskey to set
     */
    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
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
        
        User user = (User) ActionContext.getContext().getSession().get("USER");
        
        LOG.info("Activate access key : "+user.getUsername()+", "+accesskey);
        
        try {
            AccessKeyDB.activationAccessKey(accesskey);
            LOG.info("Activate access key OK.");
            setOK();
        } catch (Exception e){
            LOG.error("Activate access key ERROR : "+e);
            setERROR("Activate access key ERROR : "+e);
        }
        return SUCCESS;
    }
}
