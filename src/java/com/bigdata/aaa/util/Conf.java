package com.bigdata.aaa.util;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Conf {

    private static Logger LOG = Logger.getLogger(Conf.class);

    private final static String confFileName = "bigdata-aaa-conf.xml";
    private final static String TAG_ROOT = "bigdata";
    private final static String TAG_PROPERTY = "property";
    private final static String TAG_NAME = "name";
    private final static String TAG_VALUE = "value";

    private static Conf instance;
    private HashMap<String, String> nameValueMap;

    public static Conf getConf() {
        if (instance == null)
            instance = new Conf();
        return instance;
    }

    private Conf() {
        LOG.info("Begin init Conf...");

        nameValueMap = new HashMap<String, String>();

        URL url = Conf.class.getClassLoader().getResource(confFileName);
        if (url == null) {
            LOG.error(confFileName + " : Load Error: not exist.");
            System.exit(-1);
        }
        File file = new File(url.getFile());
        if (file.exists()) {
            LOG.info(confFileName + " : " + file.getAbsolutePath());
        } else {
            LOG.error(confFileName + " : Load Error: not exist.");
            System.exit(-1);
        }

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            NodeList rootNodes = document.getChildNodes();
            Node rootNode = null;

            int propNum = rootNodes.getLength();
            for (int i = 0; i < propNum; i++) {
                Node node = rootNodes.item(i);
                if (node instanceof Element
                        && node.getNodeName().equals(TAG_ROOT)) {
                    rootNode = node;
                    break;
                }
            }

            if (rootNode == null) {
                LOG.error("Cannnot find ROOT_TAG : " + TAG_ROOT);
                System.exit(-1);
            }

            NodeList properties = rootNode.getChildNodes();

            propNum = properties.getLength();
            for (int i = 0; i < propNum; i++) {
                Node node = properties.item(i);
                if (node instanceof Element
                        && node.getNodeName().equals(TAG_PROPERTY)) {

                    NodeList nameValeList = node.getChildNodes();
                    int num = nameValeList.getLength();
                    Node nameNode = null;
                    Node valueNode = null;

                    for (int j = 0; j < num; j++) {
                        Node n = nameValeList.item(j);
                        if (n instanceof Element
                                && n.getNodeName().equals(TAG_NAME))
                            nameNode = n;
                        else if (n instanceof Element
                                && n.getNodeName().equals(TAG_VALUE))
                            valueNode = n;
                    }

                    if (nameNode == null || valueNode == null)
                        continue;

                    String name = nameNode.getTextContent().trim();
                    String value = valueNode.getTextContent().trim();
                    if (name.equals("") || value.equals(""))
                        continue;

                    LOG.info("Get propertity : " + name + " - " + value);
                    nameValueMap.put(name, value);
                }
            }

        } catch (Exception e) {
            LOG.info("Parse " + confFileName + " ERROR : " + e);
            System.exit(-1);
        }
    }

    public String getString(String key, String defaultValue) {
        
        if (nameValueMap.containsKey(key)) {
            String valueString = nameValueMap.get(key);
            return valueString;
        } else {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (nameValueMap.containsKey(key)) {
            String valueString = nameValueMap.get(key);
            try {
                boolean ret = Boolean.parseBoolean(valueString);
                return ret;
            } catch (Exception e) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public int getInt(String key, int defaultValue) {
        if (nameValueMap.containsKey(key)) {
            String valueString = nameValueMap.get(key);
            try {
                int ret = Integer.parseInt(valueString);
                return ret;
            } catch (Exception e) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }
    
    public long getLong(String key, long defaultValue) {
        if (nameValueMap.containsKey(key)) {
            String valueString = nameValueMap.get(key);
            try {
                long ret = Long.parseLong(valueString);
                return ret;
            } catch (Exception e) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }
    
    public String getString(String key) {
        return getString(key, "");
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }
    
    public long getLong(String key) {
        return getLong(key, 0);
    }
    
    public HashMap<String, String> getAllConf() {
        return nameValueMap;
    }
}
