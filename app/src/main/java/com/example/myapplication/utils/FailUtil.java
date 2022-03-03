package com.example.myapplication.utils;

import com.example.myapplication.dto.PriceDto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
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
            //フィールド名取得処理参考
            Field[] fields = PriceDto.class.getDeclaredFields();
            for(Field f: fields){
                System.out.println(f.getName());
            }
            prop.store(new FileOutputStream(FILE_URL), "Comments");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
