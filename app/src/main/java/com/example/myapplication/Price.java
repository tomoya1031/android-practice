package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Util.ErrorCheckUtil;
import com.example.myapplication.dto.PriceDto;


public class Price extends AppCompatActivity implements View.OnClickListener {

    /**
     * 画面のデータを保持するDto
     */
    private PriceDto dto;

    /**
     * 「金額」欄
     */
    private EditText editTextPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price);

        dto = new PriceDto();
        editTextPrice = findViewById(R.id.edit_text_price);

        Intent intent = getIntent();
        dto = (PriceDto)intent.getSerializableExtra("price_dto");

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
                String text = editTextPrice.getText().toString();
                String err =  ErrorCheckUtil.isNull(text);
                if(err != null){
                    editTextPrice.setError(err);
                    return;
                }
                if(dto == null){
                    dto = new PriceDto();
                }
                dto.setPrice(text);
                intent = new Intent(getApplication(), Total.class);
                intent.putExtra("price_dto",dto);
                break;
        }
        startActivity(intent);
    }
}