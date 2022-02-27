package com.example.myapplication.views.activitys;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class Mask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mask);

        Button f = findViewById(R.id.fin);
        f.setOnClickListener(v -> {
            finish();
        });

    }

}