package com.example.myapplication.views.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static MainActivity sInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1 = findViewById(R.id.button1);
        bt1.setOnClickListener(this);

        Button bt2 = findViewById(R.id.button2);
        bt2.setOnClickListener(this);

        Button bt3 = findViewById(R.id.button3);
        bt3.setOnClickListener(this);

        Button bt4 = findViewById(R.id.button4);
        bt4.setOnClickListener(this);

        sInstance = this;

        Button f = findViewById(R.id.fin);
        f.setOnClickListener(v -> {
            finish();
        });
    }

    public void onClick(View view) {
        Intent intent;
        Class<?> c = null;
        switch (view.getId()) {
            case R.id.button1:
                c = QrRead.class;
                break;
            case R.id.button2:
                c =  Price.class;
                break;
            case R.id.button3:
                c =  Mask.class;
                break;
            case R.id.button4:
                c = Log.class;
                break;
            case R.id.fin:
                finish();
                return;
        }
        intent = new Intent(getApplication(), c);
        startActivity(intent);
    }

    public static synchronized MainActivity getInstance() {
        return sInstance;
    }
}