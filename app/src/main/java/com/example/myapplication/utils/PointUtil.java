package com.example.myapplication.utils;

import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.views.activitys.MainActivity;

public class PointUtil {

    static MainActivity sInstance = new MainActivity();

    /**
     * テキストに入力された時の処理
     * @param value
     * @param  view
     * @param  beforeValue
     */
    public void priceChange(String value, EditText view, String beforeValue){
        if(value != null && !value.isEmpty()) {
           if(value.length() == 6){
                //上限を超えて入力した場合は５秒間エラー表示する
               ErrorCheckUtil.inputOver(view, beforeValue, sInstance.getInstance().getString(R.string.err_07));
            } else{
                int i = view.getSelectionStart();

                view.setText(viewFormat(dtoFormat(value)));
                if(i == value.length()) {
                    view.setSelection(view.getText().toString().length());
                } else if(value.substring(0,1).equals("0") && value.length() > 1){
                    view.setSelection(i - 1);
                } else {
                    view.setSelection(i);
                }
            }

        }
    }

    /**
     * カンマ、円マークの箇所にカーソル選択できない対応
     * @param sub
     * @param view
     */
    public void moveCursor(int sub, EditText view){
        if(view.getText().toString() != null && !view.getText().toString().isEmpty()){
            if(view.getText().length() >= 3){
                int i = view.getText().length() - 1;
                if(i == view.getSelectionStart()){
                    view.setSelection(i + sub);
                } else {
                    view.setSelection(view.getSelectionStart());
                }
            }
        }
    }

    /**
     * ポイント画面表示用変換処理
     * @param value
     */
    public String viewFormat(String value){
        if(value != null){
            value= String.format("%,d", Integer.valueOf(value) * 100);
            if(!value.equals("0")){
                value = value.substring(0, value.length() - 2);
            }
        }
        return value;
    }

    /**
     * 金額登録変換処理
     * @param value
     */
    public String dtoFormat(String value){
        if(value != null){
            value = value.replace(",", "");
        }
        return value;
    }
}
