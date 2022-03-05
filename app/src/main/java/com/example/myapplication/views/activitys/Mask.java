package com.example.myapplication.views.activitys;

import android.content.Context;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.utils.DateUtil;
import com.example.myapplication.utils.TextWatcherUtil;


public class Mask extends AppCompatActivity implements View.OnClickListener {

    // 金額
    private EditText dateInput;

    // キーボード表示を制御するためのオブジェクト
    private InputMethodManager inputMethodManager;

    // 背景のレイアウト
    private LinearLayout mainLayout;

    private static final DateUtil dateUtil = new DateUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mask);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mainLayout = findViewById(R.id.mask_layout);

        dateInput = findViewById(R.id.date_input);
        dateInput.addTextChangedListener((TextWatcher) new TextWatcherUtil(dateInput));

        Button f = findViewById(R.id.fin);
        f.setOnClickListener(this);
        dateInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    dateInput.post(new Runnable() {
                        @Override
                        public void run() {
                            dateUtil.moveCursor(-1, dateInput);
                        }
                    });
                } else {
                    if(dateInput.getError() != null){
                        if(dateInput.getError().toString().equals(getString(R.string.err_08))){
                            dateInput.setError(null);
                        }
                    }
                }
            }
        });
    }

    /**
     * キー入力された時の処理（IMEによって数値の入力は反応しない）
     * @param keyCode
     * @param event
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            dateUtil.moveCursor(-1, dateInput);
        } else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
            dateUtil.moveCursor(1, dateInput);
        } else if(keyCode == KeyEvent.KEYCODE_ENTER){
            // キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            // 背景にフォーカスを移す
            mainLayout.requestFocus();
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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fin:
                // ボタン「終了」がタップされたとき
                finish();
                break;
            case R.id.date_input:
                dateUtil.moveCursor(-1, dateInput);
                break;
        }
    }

}