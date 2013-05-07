package com.bigdata.aaa.webifc.action;

import org.apache.log4j.Logger;

import com.bigdata.aaa.db.AccessKeyDB;
import com.bigdata.aaa.db.User;
import com.opensymphony.xwork2.ActionContext;

public class DelAccessKeyAction extends BaseAction {

    public static Logger LOG = Logger.getLogger(DelAccessKeyAction.class);

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
        
        LOG.info("Delete access key : "+user.getUsername()+", "+accesskey);
        
        try {
            AccessKeyDB.delAccessKey(accesskey);
            LOG.info("Delete access key OK.");
            setOK();
        } catch (Exception e){
            LOG.error("Delete access key ERROR : "+e);
            setERROR("Delete access key ERROR : "+e);
        }
        return SUCCESS;
    }
}
