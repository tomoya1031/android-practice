package com.example.myapplication.utils;

import android.content.Context;

import com.example.myapplication.dto.PriceDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Properties;

public class FailUtil {

    private static final String STR_FILE = "str.properties";
    private Properties prop;
    private File file;

    public FailUtil(Context context) {
        prop = new Properties();
        file = new File(context.getFilesDir(), STR_FILE);
        try {
            InputStream is = new FileInputStream(file);
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
            PriceDto obj = new PriceDto();
            obj.setPrice(new BigDecimal(1));
            obj.setPoint(new BigDecimal(1));
            obj.setQr("a");
            Field[] fields = obj.getClass().getDeclaredFields();
            for(Field f: fields){
                f.setAccessible(true);
                System.out.println(f.getName());
                System.out.println(f.get(obj).toString());
            }
            prop.store(new FileOutputStream(file), "Comments");
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
