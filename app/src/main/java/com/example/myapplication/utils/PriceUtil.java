package com.example.myapplication.utils;

import android.os.Handler;
import android.widget.EditText;

public class PriceUtil {

    private final String yen = "¥";

    private boolean flg = true;

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
                int i = view.getSelectionStart();
                view.setError("¥999,999以上入力できません");
                view.setText(beforeValue);
                view.setSelection(i -1);
                if(flg){
                    flg = false;
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.setError(null);
                            flg = true;
                        }
                    }, 5000);
                }
            } else{
                int i = view.getSelectionStart();

                String price =  value.replace(yen, "")
                        .replace(",", "");

                view.setText(yen + String.format("%,d", Integer.valueOf(price)));
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
}
