package com.example.myapplication.Util;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Price;
import com.example.myapplication.R;

import java.util.regex.Pattern;


public class ErrorCheckUtil {

    static MainActivity sInstance = new MainActivity();

    /**
     * 正の整数以外・null・空文字・０の場合エラーメッセージ返却
     * @param value
     */
    public static String isNull(String value) {
        String errMessage = null;
        if (value == null || value.isEmpty()) {
            errMessage = sInstance.getInstance().getString(R.string.err_01);
        } else if (value.equals("0")) {
            errMessage = sInstance.getInstance().getString(R.string.err_02);
        } else if(!Pattern.compile("^[0-9]+$").matcher(value).matches()){
            errMessage = sInstance.getInstance().getString(R.string.err_03);
        }
        return errMessage;
    }
}
