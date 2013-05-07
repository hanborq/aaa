package com.bigdata.aaa.db;

public class AccessKey {

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    public final static int ACTIVATY     = 0;
    public final static int DEACTIVATY   = 1;
    public final static int CONSOLE      = 2;
    
    private String userID;
    private String accessKey;
    private String securityKey;
    private long createTime;
    private int status;
    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }
    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
    /**
     * @return the createTime
     */
    public long getCreateTime() {
        return createTime;
    }
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    /**
     * @return the accessKey
     */
    public String getAccessKey() {
        return accessKey;
    }
    /**
     * @param accessKey the accessKey to set
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
    /**
     * @return the securityKey
     */
    public String getSecurityKey() {
        return securityKey;
    }
    /**
     * @param securityKey the securityKey to set
     */
    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }
}
