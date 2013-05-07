package com.bigdata.aaa.util;

import java.security.Provider;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    public static boolean sendActiveMail(String email, String dispname, String actKey) {

        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", Conf.getConf().getString("mail.smtp.host"));
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", Conf.getConf().getString("mail.smtp.port"));
        props.setProperty("mail.smtp.socketFactory.port", Conf.getConf().getString("mail.smtp.port"));
        props.put("mail.smtp.auth", "true");
        final String username = Conf.getConf().getString("mail.smtp.username");
        final String password = Conf.getConf().getString("mail.smtp.password");
        Session session = Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }});
        
        try {
            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(Conf.getConf().getString("mail.address")));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
                    email, false));
            msg.setSubject("Welcome to Bigdata");
            msg.setText("Hi "+dispname+",\n    Welcome to Bigdata, click the link below to activate your account.\n    "+Conf.getConf().getString("host.name.nonssl")+"/RegAct.action?key="+actKey);
            msg.setSentDate(new Date());
            Transport.send(msg);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void main(String[] args){
        sendActiveMail("yuanfeng.zhang@gmail.com", "Cestbon", "1234567890");
    }
    
}
