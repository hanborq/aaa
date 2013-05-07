package com.bigdata.aaa.db;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="User")
@XmlType(propOrder={
        "userID", 
        "username",
        "password",
        "dispname",
        "usertype",
        "regTime",
        "actTime",
        "consoleAccessKey",
        "consoleSecurityKey"
        })
public class User {

    public final static int ADMIN = 0;
    public final static int ORDINARY = 1;
    
    private static JAXBContext serializeContext;
    private static JAXBContext deserializeContext;
    
    static{
        try {
            serializeContext = JAXBContext.newInstance(User.class);
            deserializeContext = JAXBContext.newInstance(User.class);
        } catch (JAXBException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
    }
    
    public static String serialize(User user) {
        try {
            Marshaller marshaller = serializeContext.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(user, sw);
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static User deserialize(String string) {
        try {
            Unmarshaller unmarshaller = deserializeContext.createUnmarshaller();
            StringReader sr = new StringReader(string);
            return (User)unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private String userID;
    private String username;
    private String password;
    private String dispname;
    private int usertype;
    private long regTime;
    private long actTime;
    private String consoleAccessKey;
    private String consoleSecurityKey;
    /**
     * @return the userID
     */
    @XmlElement(name="userID")
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
     * @return the username
     */
    @XmlElement(name="username")
    public String getUsername() {
        return username;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return the password
     */
    @XmlElement(name="password")
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the dispname
     */
    @XmlElement(name="dispname")
    public String getDispname() {
        return dispname;
    }
    /**
     * @param dispname the dispname to set
     */
    public void setDispname(String dispname) {
        this.dispname = dispname;
    }
    /**
     * @return the regTime
     */
    @XmlElement(name="regTime")
    public long getRegTime() {
        return regTime;
    }
    /**
     * @param regTime the regTime to set
     */
    public void setRegTime(long regTime) {
        this.regTime = regTime;
    }
    /**
     * @return the actTime
     */
    @XmlElement(name="actTime")
    public long getActTime() {
        return actTime;
    }
    /**
     * @param actTime the actTime to set
     */
    public void setActTime(long actTime) {
        this.actTime = actTime;
    }
    /**
     * @return the usertype
     */
    @XmlElement(name="usertype")
    public int getUsertype() {
        return usertype;
    }
    /**
     * @param usertype the usertype to set
     */
    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
    /**
     * @return the consoleAccessKey
     */
    @XmlElement(name="consoleAccessKey")
    public String getConsoleAccessKey() {
        return consoleAccessKey;
    }
    /**
     * @param consoleAccessKey the consoleAccessKey to set
     */
    public void setConsoleAccessKey(String consoleAccessKey) {
        this.consoleAccessKey = consoleAccessKey;
    }
    /**
     * @return the consoleSecurityKey
     */
    @XmlElement(name="consoleSecurityKey")
    public String getConsoleSecurityKey() {
        return consoleSecurityKey;
    }
    /**
     * @param consoleSecurityKey the consoleSecurityKey to set
     */
    public void setConsoleSecurityKey(String consoleSecurityKey) {
        this.consoleSecurityKey = consoleSecurityKey;
    }
}
