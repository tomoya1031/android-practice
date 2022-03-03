package com.example.myapplication.views.activitys;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.utils.FailUtil;

import java.io.File;

public class FileManag extends AppCompatActivity {

    private static final String STR_FILE = "Atr.properties";

    private static final int PERMISSION_WRITE_EX_STR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        ListView resultListView = findViewById(R.id.a);

        Button f = findViewById(R.id.fin);
        f.setOnClickListener(v -> {
            finish();
        });

        //ファイル作成テスト
        FailUtil config = new FailUtil();
        //他の端末でどうなるか確認用
        Context context = getApplicationContext();
        File file = new File(context.getFilesDir(), STR_FILE);
        config.set("TEST","123dddddd");
    }
}
