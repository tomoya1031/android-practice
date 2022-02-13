package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static MainActivity sInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1 = findViewById(R.id.button1);
        bt1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), QrRead.class);
            startActivity(intent);
        });

        Button bt2 = findViewById(R.id.button2);
        bt2.setOnClickListener(v -> {
            Intent intent2 = new Intent(getApplication(), Price.class);
            startActivity(intent2);
        });
        sInstance = this;
    }

    public static synchronized MainActivity getInstance() {
        return sInstance;
    }
}