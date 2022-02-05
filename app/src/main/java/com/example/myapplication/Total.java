package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Util.TempDataUtil;
import com.example.myapplication.dto.PriceDto;

import org.w3c.dom.Text;


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
        editTextPrice.setText(dto.getPrice());
        editTextPoint.setText(dto.getPoint());

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