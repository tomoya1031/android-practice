package com.example.myapplication.views.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dto.PriceDto;


public class Total extends AppCompatActivity implements View.OnClickListener {

    private PriceDto dto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total);

        Intent intent = getIntent();
        dto = (PriceDto)intent.getSerializableExtra("price_dto");

        TextView editTextPrice = findViewById(R.id.price);
        TextView editTextPoint = findViewById(R.id.point);
        TextView editTextTotalPoint = findViewById(R.id.total_price);
        editTextPrice.setText(dto.getPrice().toString());
        editTextPoint.setText(dto.getPoint().toString());
        editTextTotalPoint.setText(dto.getPrice().subtract(dto.getPoint()).toString());

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
    }
    /**
     * 画面のボタンがタップされた時の処理
     * @param view
     */
    public void onClick(View view) {
        Intent intent = new Intent(getApplication(), MainActivity.class);
        switch (view.getId()) {
            case R.id.button3:
                // ボタン「終了」がタップされたとき
                break;
            case R.id.button1:
                // ボタン「TEST」がタップされたとき
                intent = new Intent(getApplication(), Price.class);
                intent.putExtra("price_dto",dto);
                break;
            case R.id.button2:
                // ボタン「TEST」がタップされたとき
                intent = new Intent(getApplication(), Point.class);
                intent.putExtra("price_dto",dto);
                break;
        }
        startActivity(intent);
    }

}