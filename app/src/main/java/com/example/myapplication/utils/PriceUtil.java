package com.example.myapplication.utils;

import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.views.activitys.MainActivity;

public class PriceUtil {

    static MainActivity sInstance = new MainActivity();

    private final String yen = "¥";

    /**
     * テキストに入力された時の処理
     * @param value
     * @param  view
     * @param  beforeValue
     */
    public void priceChange(String value, EditText view, String beforeValue){
        if(value != null && !value.isEmpty()) {

            if (value.length() == 1) {
                if (value.equals(yen)) {
                    view.setText(null);
                } else {
                    view.setText(yen + value);
                    view.setSelection(view.getText().toString().length());
                }
            } else if(value.length() == 9){
                //上限を超えて入力した場合は５秒間エラー表示する
                ErrorCheckUtil.inputOver(view, beforeValue, sInstance.getInstance().getString(R.string.err_06));
            } else{
                int i = view.getSelectionStart();

                view.setText(viewFormat(dtoFormat(value)));
                if(i == value.length()) {
                    view.setSelection(view.getText().toString().length());
                } else if(view.getText().toString().length() == 4 &&
                        (value.contains(",") && i >= 2)){
                    view.setSelection(i - 1);
                } else if(view.getText().toString().length() == 6 &&
                        (!value.contains(",") && i >= 1)) {
                    view.setSelection(i + 1);
                } else if(value.substring(1,2).equals("0") && value.length() > 2){
                    view.setSelection(i - 1);
                } else if(i == 0) {
                    view.setSelection(1);
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
            if(view.getSelectionStart() == 0){
                view.setSelection(1);
            } else if(view.getText().length() >= 6){
                int i = view.getText().length() - 3;
                if(i == view.getSelectionStart()){
                    view.setSelection(i + sub);
                } else {
                    view.setSelection(view.getSelectionStart());
                }
            }
        }
    }

    /**
     * 金額画面表示用変換処理
     * @param value
     */
    public String viewFormat(String value){
        if(value != null){
            StringBuilder buf = new StringBuilder();
            buf.append(yen);
            buf.append(String.format("%,d", Integer.valueOf(value)));
            value = buf.toString();
        }
        return value;
    }

    /**
     * 金額登録変換処理
     * @param value
     */
    public String dtoFormat(String value){
        if(value != null){
            value = value.replace(yen, "").replace(",", "");
        }
        return value;
    }
}
