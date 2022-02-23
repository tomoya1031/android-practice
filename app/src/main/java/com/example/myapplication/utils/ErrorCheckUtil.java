package com.example.myapplication.utils;

import android.os.Handler;
import android.widget.EditText;

import com.example.myapplication.views.activitys.MainActivity;
import com.example.myapplication.R;

import java.math.BigDecimal;
import java.util.regex.Pattern;


public class ErrorCheckUtil {

    static MainActivity sInstance = new MainActivity();
    private static Handler handler = new Handler();

    /**
     * 正の整数以外・null・空文字・０以下の場合エラーメッセージ返却
     * @param value
     */
    public static String isNull(String value) {
        String errMessage = null;
        if (value == null || value.isEmpty()) {
            return errMessage = sInstance.getInstance().getString(R.string.err_01);
        } else if(!Pattern.compile("^[0-9]+$").matcher(value).matches()){
            return errMessage = sInstance.getInstance().getString(R.string.err_03);
        }
        if (Integer.valueOf(value) <= 0) {
            return errMessage = sInstance.getInstance().getString(R.string.err_02);
        }
        return errMessage;
    }

    /**
     * 正の整数以外・０未満の場合エラーメッセージ返却
     * @param value
     */
    public static String isNum(String value) {
        String errMessage = null;
        if(!Pattern.compile("^[0-9]+$").matcher(value).matches()){
            return errMessage = sInstance.getInstance().getString(R.string.err_03);
        }
        if (Integer.valueOf(value) < 0) {
            return errMessage = sInstance.getInstance().getString(R.string.err_02);
        }

        return errMessage;
    }

    /**
     * 金額よりポイントが大きい場合エラーメッセージ返却
     * @param point
     * @param price
     */
    public static String isOver(String point, String price){
        String errMessage = null;
        if(point != null && !point.isEmpty()){
            if(new BigDecimal(point).multiply(new BigDecimal(100)).compareTo(new BigDecimal(price)) == 1){
                return errMessage = sInstance.getInstance().getString(R.string.err_04);
            }
        }
        return errMessage;
    }

    /**
     * 上限以上の入力した場合エラーメッセージ返却
     * @param view
     * @param beforeValue
     * @param errMsg
     */
    public static void inputOver(EditText view, String beforeValue, String errMsg){
        //上限を超えて入力した場合は５秒間エラー表示する
        if(view.getError() == null){
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setError(null);
                }
            }, 5000);
        }
        int i = view.getSelectionStart();
        view.setError(errMsg);
        view.setText(beforeValue);
        view.setSelection(i - 1);
    }
}
