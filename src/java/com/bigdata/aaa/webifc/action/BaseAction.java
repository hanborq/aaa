package com.bigdata.aaa.webifc.action;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

    protected String result;
    protected String message;
    
    protected void setOK() {
        result = "OK";
        message = "OK";
    }

    protected void setERROR() {
        result = "ERROR";
        message = "ERROR";
    }

    protected void setERROR(String message) {
        result = "ERROR";
        this.message = message;
    }
}
