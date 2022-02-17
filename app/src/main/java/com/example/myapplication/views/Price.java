package com.example.myapplication.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.example.myapplication.R;
import com.example.myapplication.util.ErrorCheckUtil;
import com.example.myapplication.dto.PriceDto;
import java.math.BigDecimal;


public class Price extends Activity implements View.OnClickListener , TextWatcher {

    // 画面のデータを保持するDto
    private PriceDto dto;

    // 金額
    private EditText editTextPrice;

    // キーボード表示を制御するためのオブジェクト
    InputMethodManager inputMethodManager;
    // 背景のレイアウト
    private LinearLayout mainLayout;

    private final String yen = "¥";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mainLayout = findViewById(R.id.priceLayout);

        if(dto == null){
            dto = new PriceDto();
        }
        editTextPrice = findViewById(R.id.edit_text_price);

        // リスナーを登録
        editTextPrice.addTextChangedListener(this);
        editTextPrice.setOnClickListener(this);

        editTextPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    editTextPrice.post(new Runnable() {
                        @Override
                        public void run() {
                            moveCursor(-1);
                        }
                    });
                }
            }
        });

        Intent intent = getIntent();
        dto = (PriceDto)intent.getSerializableExtra("price_dto");

        if(dto != null){
            editTextPrice.setText(dto.getPrice().toString());
        }

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }


    /**
     * キー入力された時の処理（IMEによって数値の入力は反応しない）
     * @param keyCode
     * @param event
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            moveCursor(-1);
        } else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
            moveCursor(1);
        }
        return super.onKeyUp(keyCode, event);
    }

    /**
     * 画面タップ時の処理
     * @param event
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // キーボードを隠す
        inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        // 背景にフォーカスを移す
        mainLayout.requestFocus();
        return true;
    }

    /**
     * 画面のボタンがタップされた時の処理
     * @param view
     */
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button1:
                // ボタン「終了」がタップされたとき
                intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                break;

            case R.id.button2:
                // ボタン「TEST」がタップされたとき
                String value = editTextPrice.getText().toString();
                value =  value.toString().replace(yen, "")
                        .replace(",", "");
                String err =  ErrorCheckUtil.isNull(value);
                if(err != null){
                    editTextPrice.setError(err);
                    return;
                }
                if(dto == null){
                    dto = new PriceDto();
                    dto.setPoint(BigDecimal.ZERO);
                }
                dto.setPrice(new BigDecimal(value));
                intent = new Intent(getApplication(), Total.class);
                intent.putExtra("price_dto",dto);
                startActivity(intent);
                break;
            case R.id.edit_text_price:
                moveCursor(-1);
                break;
        }
    }

    /**
     * カンマ、円マークの箇所にカーソル選択できない対応
     * @param sub
     */
    public void moveCursor(int sub){
        if(editTextPrice.getText().toString() != null && !editTextPrice.getText().toString().isEmpty()){
            if(editTextPrice.getSelectionStart() == 0){
                editTextPrice.setSelection(1);
            } else if(editTextPrice.getText().length() >= 6){
                int i = editTextPrice.getText().length() - 3;
                if(i == editTextPrice.getSelectionStart()){
                    editTextPrice.setSelection(i + sub);
                } else {
                    editTextPrice.setSelection(editTextPrice.getSelectionStart());
                }
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    /**
     * テキストに入力された時の処理
     * @param editable
     */
    @Override
    public void afterTextChanged(Editable editable) {
        if(editable.toString() != null && !editable.toString().isEmpty()){
            editTextPrice.removeTextChangedListener(this);
            if(editable.toString().length() == 1){
                if(editable.toString().equals(yen)){
                    editTextPrice.setText(null);
                } else {
                    editTextPrice.setText(yen + editable.toString());
                    editTextPrice.setSelection(editTextPrice.getText().toString().length());
                }
            } else{
                int i = editTextPrice.getSelectionStart();

                String value =  editable.toString().replace(yen, "")
                        .replace(",", "");

                editTextPrice.setText(yen + String.format("%,d", Integer.valueOf(value)));
                if(i == editable.toString().length()) {
                    editTextPrice.setSelection(editTextPrice.getText().toString().length());
                } else if(editTextPrice.getText().toString().length() == 4 &&
                        (editable.toString().contains(",") && i >= 2)){
                    editTextPrice.setSelection(i - 1);
                } else if(editTextPrice.getText().toString().length() == 6 &&
                        (!editable.toString().contains(",") && i >= 1)) {
                    editTextPrice.setSelection(i + 1);
                } else if(i == 0) {
                    editTextPrice.setSelection(1);
                } else {
                    editTextPrice.setSelection(i);
                }
            }
            editTextPrice.addTextChangedListener(this);
        }
    }
}