package com.bigdata.aaa.webifc.action;

import org.apache.log4j.Logger;

import com.bigdata.aaa.db.User;
import com.bigdata.aaa.db.UserDB;
import com.bigdata.aaa.util.MD5HashUtil;
import com.opensymphony.xwork2.ActionContext;

public class ChangePwdAction extends BaseAction {

    public static Logger LOG = Logger.getLogger(RegActAction.class);

    private String oldpassword;
    private String newpassword;
    
    /**
     * @param oldpassword the oldpassword to set
     */
    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }
    /**
     * @param newpassword the newpassword to set
     */
    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
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

        if (oldpassword == null || newpassword == null
                || oldpassword.equals("") || newpassword.equals("")
               ) {
            LOG.error("Input param error : oldpassword = " + oldpassword
                    + ", newpassword = " + newpassword);
            setERROR("param error.");
            return SUCCESS;
        }
        
        User user = (User) ActionContext.getContext().getSession().get("USER");
        LOG.info("Input param : user = "+user.getUsername()+", oldpassword = " + oldpassword
                + ", newpassword = " + newpassword);

        User userInDB = UserDB.scanUser("user_name", user.getUsername());
        if (userInDB == null) {
            LOG.error("Cannot find user in db : "+user.getUsername());
            setERROR("Cannot find user in db : "+user.getUsername());
            return SUCCESS;
        }
        
        if (!userInDB.getPassword().equals(MD5HashUtil.hashCode(oldpassword))) {
            LOG.error("Old password not right.");
            setERROR("Old password not right.");
            return SUCCESS;
        }
        
        if (!UserDB.changePwd(user.getUsername(), newpassword)) {
            LOG.error("Change Password Fail.");
            setERROR("Change Password Fail.");
        }
        
        setOK();
        return SUCCESS;
    }
}
