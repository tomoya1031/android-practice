package com.example.myapplication.views.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dto.PriceDto;
import com.example.myapplication.utils.ErrorCheckUtil;

import java.math.BigDecimal;


public class Point extends AppCompatActivity implements View.OnClickListener {

    /**
     * 画面のデータを保持するDto
     */
    private PriceDto dto = new PriceDto();

    /**
     * ポイント
     */
    private EditText editTextPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point);

        dto = new PriceDto();
        editTextPoint = findViewById(R.id.edit_text_point);

        Intent intent = getIntent();
        dto = (PriceDto)intent.getSerializableExtra("price_dto");

        if(dto != null){
            editTextPoint.setText(dto.getPoint().toString());
        }

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    /**
     * 画面のボタンがタップされた時の処理
     * @param view
     */
    public void onClick(View view) {
        Intent intent = new Intent(getApplication(), MainActivity.class);
        switch (view.getId()) {
            case R.id.button1:
                // ボタン「終了」がタップされたとき
                break;

            case R.id.button2:
                // ボタン「TEST」がタップされたとき
                String value = editTextPoint.getText().toString();
                String err =  ErrorCheckUtil.isNull(value);
                if(err != null){
                    editTextPoint.setError(err);
                    return;
                }
                dto.setPoint(new BigDecimal(value));
                intent = new Intent(getApplication(), Total.class);
                intent.putExtra("price_dto",dto);
                break;
        }
        startActivity(intent);
    }
}