package com.example.myapplication.utils;

import android.os.Handler;
import android.widget.EditText;

public class PointUtil {

    private boolean flg = true;

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
                int i = view.getSelectionStart();
                view.setError("999,900P以上入力できません");
                view.setText(beforeValue);
                view.setSelection(i - 1);
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

                String point =  value.replace(",", "");

                point= String.format("%,d", Integer.valueOf(point) * 100);
                if(!point.equals("0")){
                    point = point.substring(0, point.length()-2);
                }
                view.setText(point);
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
}
