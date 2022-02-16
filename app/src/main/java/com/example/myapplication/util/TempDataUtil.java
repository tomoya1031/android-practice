package com.example.myapplication.util;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TempDataUtil {
    private final static String FILE_NAME = "PriceDto.obj";

    /**
     * データを保存する
     * @param context
     * @param object 保存するオブジェクト
     */
    public static void store(Context context, Serializable object){
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
            out.writeObject(object);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * データを読み込む
     * @param context
     * @return 保存しているデータがない場合は context
     */
    public static Object load(Context context){
        Object retObj = null;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    context.openFileInput(FILE_NAME)
            );
            retObj = in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return retObj;
    }
}
