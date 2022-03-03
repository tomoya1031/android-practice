package com.example.myapplication.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FailUtil {

    private static final String FILE_URL = "/data/user/0/com.example.myapplication/files/Atr.properties";
    private Properties prop;

    public FailUtil() {
        prop = new Properties();
        try {
            InputStream is = new FileInputStream(FILE_URL);
            if(is != null){
                prop.load(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object get(String key) {
        return prop.get(key);
    }

    public void set(String key, String value){
        prop.setProperty(key, value);
        try {
            prop.store(new FileOutputStream(FILE_URL), "Comments");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
