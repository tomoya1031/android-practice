package com.example.myapplication.views;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.HogeService;
import com.example.myapplication.R;

public class QrReadView extends AppCompatActivity {


    private HogeService hoge = new HogeService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_read_view);
        Button bt = findViewById(R.id.button1);
        switch (BuildConfig.FLAVOR) {
            case "app1":
                // paid flavorの時の処理
                View a = findViewById(R.id.barcodeView);
                TextView textView = findViewById(R.id.textView);
                Resources res = getResources();
                hoge.hoge(a, textView, res, this);

                break;
            case "app2":
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                break;
        }
        bt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        switch (BuildConfig.FLAVOR) {
            case "app1":
                hoge.hoge2();
//                mBarcodeView.resume();
                break;
            case "app2":
                break;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        switch (BuildConfig.FLAVOR) {
            case "app1":
                hoge.hoge3();
//                mBarcodeView.pause();
                break;
            case "app2":
                break;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            if(result.getContents() == null) {
//                Log.d("MainActivity", "Cancelled scan");
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//            } else {
//                Log.d("MainActivity", "Scanned");
//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
//            }
//        } else {
//            // This is important, otherwise the result will not be passed to the fragment
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }

}