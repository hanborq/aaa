package com.bigdata.aaa.webifc.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.bigdata.aaa.db.AccessKey;
import com.bigdata.aaa.db.AccessKeyDB;
import com.bigdata.aaa.db.User;
import com.opensymphony.xwork2.ActionContext;

public class ListAccessKeyAction extends BaseAction {

    public static Logger LOG = Logger.getLogger(ListAccessKeyAction.class);

    private List<AccessKey> accessKeyList;

    /**
     * @return the accessKeyList
     */
    public List<AccessKey> getAccessKeyList() {
        return accessKeyList;
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
        
        LOG.info("List access keys : "+user.getUsername());
        
        try {
            accessKeyList = AccessKeyDB.scanAccessKey(user.getUserID());
        } catch (Exception e) {
            LOG.error("List access keys ERROR : "+e);
            setERROR("List access keys ERROR : "+e);
            return SUCCESS;
        }
        
        if (accessKeyList == null) {
            LOG.error("List access keys ERROR : accessKeyList == null");
            setERROR("List access keys ERROR : accessKeyList == null");
            return SUCCESS;
        }
        
        setOK();
        return SUCCESS;
    }
}
