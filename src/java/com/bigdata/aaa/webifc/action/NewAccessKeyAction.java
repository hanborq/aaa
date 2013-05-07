package com.bigdata.aaa.webifc.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.bigdata.aaa.db.AccessKeyDB;
import com.bigdata.aaa.db.User;
import com.opensymphony.xwork2.ActionContext;

public class NewAccessKeyAction extends BaseAction {

    public static Logger LOG = Logger.getLogger(NewAccessKeyAction.class);

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
        
        LOG.info("Apply for new access key : "+user.getUsername());
        
        try {
            AccessKeyDB.newAccessKey(user, false);
            LOG.info("Apply for new access key OK.");
            setOK();
        } catch (Exception e){
            LOG.error("Apply for new access key ERROR : "+e);
            setERROR("Apply for new access key ERROR : "+e);
        }
        return SUCCESS;
    }
}