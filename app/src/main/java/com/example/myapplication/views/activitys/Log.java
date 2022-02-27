package com.example.myapplication.views.activitys;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.Arrays;
import java.util.List;


public class Log extends AppCompatActivity {

    List<String> listData = Arrays.asList("1１１１１１１１１１１１１１１１１１ああああああああああああああああああああああああああああああああああ１１", "2", "3", "4", "5", "6", "7", "8", "9", "10","1１１１１１１１１１１１１１１１１１ああああああああああああああああああああああああああああああああああ１１", "2", "3", "4", "5", "6", "7", "8", "9", "10","1１１１１１１１１１１１１１１１１１ああああああああああああああああああああああああああああああああああ１１", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    // ListViewの表示内容を管理するクラス
    ArrayAdapter<String> adapter;
    EditText a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        ListView resultListView = findViewById(R.id.a);

        adapter = new ArrayAdapter<>(this
                , android.R.layout.simple_list_item_1
                , listData);
        // ListViewに表示情報をまとめたAdapterをセット
        resultListView.setAdapter(adapter);

        Button f = findViewById(R.id.fin);
        f.setOnClickListener(v -> {
            finish();
        });

    }
}