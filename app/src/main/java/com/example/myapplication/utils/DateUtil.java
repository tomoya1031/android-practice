package com.example.myapplication.utils;

import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.views.activitys.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    static MainActivity sInstance = new MainActivity();

    private final String slash = "/";

    /**
     * テキストに入力された時の処理
     * @param value
     * @param  view
     * @param  beforeValue
     */
    public void dateChange(String value, EditText view, String beforeValue){
        if(value != null && !value.isEmpty()) {
            int valLen = value.length();
            if(valLen == 11){
                //上限を超えて入力した場合は５秒間エラー表示する
                dtoFormat(beforeValue);
                ErrorCheckUtil.inputOver(view, beforeValue, sInstance.getInstance().getString(R.string.err_08));
            } else if (valLen > 4){
                int select = view.getSelectionStart();
                view.setText(viewFormat(value));
                int sub = valLen - beforeValue.length();
                if(select == 5 || select == 8){
                    if(sub == 1){
                        view.setSelection(select + 1);
                    } else {
                        view.setSelection(select - 1);
                    }
                } else {
                    view.setSelection(select);
                }
            }
        }
    }

    /**
     * スラッシュの箇所にカーソル選択できない対応
     * @param sub
     * @param view
     */
    public void moveCursor(int sub, EditText view){
        if(view.getText().toString() != null && !view.getText().toString().isEmpty()){
            if(view.getText().length() > 4){
                int select = view.getSelectionStart();
                if(select == 5 || select == 8){
                    view.setSelection(select + sub);
                } else {
                    view.setSelection(select);
                }
            }
        }
    }

    /**
     * 日付画面表示用変換処理
     * @param value
     */
    public String viewFormat(String value){
        if(value != null){
            value = value.replace(slash, "");
            String y = value.substring(0, 4);
            String m;
            int i = value.length();
            if(i > 4){
                if(i < 7){
                    m = value.substring(4, i);
                    value = String.format("%s/%s", y,m);
                } else{
                    m = value.substring(4, 6);
                    String d = value.substring(6, i);
                    value = String.format("%s/%s/%s", y,m,d);
                }
            }
        }
        return value;
    }

    /**
     * 日付登録変換処理
     * @param value
     */
    public Date dtoFormat(String value){
        Date date = null;
        if(value != null){
            try {
                SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
                date = sdFormat.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
}
