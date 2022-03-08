package com.example.myapplication.views.activitys;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.utils.FailUtil;

public class FileManag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        Button f = findViewById(R.id.fin);
        f.setOnClickListener(v -> {
            finish();
        });

        //ファイル作成テスト
        Context context = getApplicationContext();
        FailUtil config = new FailUtil(context);
        config.set("TEST","123dddddd");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
