package com.example.myapplication.views.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dto.PriceDto;
import com.example.myapplication.utils.ErrorCheckUtil;
import com.example.myapplication.utils.PointUtil;
import com.example.myapplication.utils.PriceUtil;
import com.example.myapplication.utils.TextWatcherUtil;

import java.math.BigDecimal;

public class Price extends AppCompatActivity implements View.OnClickListener {

    private static final PriceUtil priceUtil = new PriceUtil();

    private static final PointUtil pointUtil = new PointUtil();

    // 画面のデータを保持するDto
    private PriceDto dto;
    private final Handler handler = new Handler();

    // 金額
    private EditText editTextPrice;
    // ポイント
    private EditText editTextPoint;

    // キーボード表示を制御するためのオブジェクト
    InputMethodManager inputMethodManager;
    // 背景のレイアウト
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mainLayout = findViewById(R.id.priceLayout);

        editTextPrice = findViewById(R.id.edit_text_price);
        editTextPoint = findViewById(R.id.edit_text_point);

        Intent intent = getIntent();
        dto = (PriceDto)intent.getSerializableExtra("price_dto");

        if(dto != null){
            editTextPrice.setText(priceUtil.viewFormat(dto.getPrice().toString()));
            editTextPoint.setText(pointUtil.viewFormat(dto.getPoint().toString()));
        }

        // リスナーを登録
        editTextPrice.addTextChangedListener((TextWatcher) new TextWatcherUtil(editTextPrice));
        editTextPoint.addTextChangedListener((TextWatcher) new TextWatcherUtil(editTextPoint));
        editTextPrice.setOnClickListener(this);
        editTextPoint.setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

        editTextPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    editTextPrice.post(new Runnable() {
                        @Override
                        public void run() {
                            priceUtil.moveCursor(-1, editTextPrice);
                        }
                    });
                } else {
                    if(editTextPrice.getError() != null){
                        if(editTextPrice.getError().toString().equals(getString(R.string.err_06))){
                            editTextPrice.setError(null);
                        }
                    }
                }
            }
        });
        editTextPoint.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    editTextPoint.post(new Runnable() {
                        @Override
                        public void run() {
                            pointUtil.moveCursor(-1, editTextPoint);
                        }
                    });
                } else {
                    if(editTextPoint.getError() != null){
                        if(editTextPoint.getError().toString().equals(getString(R.string.err_07))){
                            editTextPoint.setError(null);
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
            priceUtil.moveCursor(-1, editTextPrice);
            pointUtil.moveCursor(-1, editTextPoint);
        } else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
            priceUtil.moveCursor(1, editTextPrice);
            pointUtil.moveCursor(1, editTextPoint);
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

    /**
     * 初期フォーカスの処理
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // EditTextがフォーカスを取得した場合
        if(hasFocus && editTextPrice == getCurrentFocus()) {
            new Handler().postDelayed( showKeyboardDelay , 10);
        }
    }

    /**
     * ソフトキーボード表示の遅延実行処理
     */
    private final Runnable showKeyboardDelay = new Runnable() {
        @Override
        public void run() {
            // ソフトキーボードを表示
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editTextPrice, 0);
        }
    };

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
                finish();
                break;

            case R.id.button2:
                // ボタン「次へ」がタップされたとき
                String priceValue = priceUtil.dtoFormat(editTextPrice.getText().toString());
                String priceErr =  ErrorCheckUtil.isNull(priceValue);

                String pointValue = pointUtil.dtoFormat(editTextPoint.getText().toString());
                String pointErr = null;
                if(pointValue != null && !pointValue.isEmpty()){
                    pointErr = ErrorCheckUtil.isNum(pointValue);
                }
                if(priceErr != null || pointErr != null){
                    if(priceErr != null){
                        editTextPrice.setError(priceErr);
                    }
                    if(pointErr != null){
                        editTextPoint.setError(pointErr);
                    }
                    return;
                } else {
                    pointErr = ErrorCheckUtil.isOver(pointValue, priceValue);
                    if(pointErr != null){
                        editTextPoint.setError(pointErr);
                        return;
                    }
                }

                if(dto == null){
                    dto = new PriceDto();
                }
                if(pointValue != null && !pointValue.isEmpty()){
                    dto.setPoint(new BigDecimal(pointValue).multiply(new BigDecimal(100)));
                } else {
                    dto.setPoint(BigDecimal.ZERO);
                }
                dto.setPrice(new BigDecimal(priceValue));
                intent = new Intent(getApplication(), Total.class);
                intent.putExtra("price_dto",dto);
                startActivity(intent);
                finish();
                break;
            case R.id.edit_text_price:
                priceUtil.moveCursor(-1, editTextPrice);
                break;
            case R.id.edit_text_point:
                pointUtil.moveCursor(-1, editTextPoint);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}