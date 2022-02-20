package com.example.myapplication.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.myapplication.R;

public class TextWatcherUtil implements TextWatcher {

    private static final PriceUtil priceUtil = new PriceUtil();

    private static final PointUtil pointUtil = new PointUtil();

    private EditText view;

    private  String beforeValue;


    public TextWatcherUtil(EditText view) {
        this.view = view;
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        switch (view.getId()) {
            case R.id.edit_text_price:
            case R.id.edit_text_point:
                beforeValue = s.toString();
                break;
        }
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    /**
     * テキストに入力された時の処理
     * @param s
     */
    public void afterTextChanged(Editable s) {
        view.removeTextChangedListener(this);
        switch (view.getId()) {
            case R.id.edit_text_price:
                priceUtil.priceChange(s.toString(), view, beforeValue);
                break;
            case R.id.edit_text_point:
                pointUtil.priceChange(s.toString(), view, beforeValue);
                break;
        }
        view.addTextChangedListener(this);
    }
}
