package com.example.myapplication.views.activitys;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.myapplication.R;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.Date;

public class QrReadView extends Activity {


    private CompoundBarcodeView mBarcodeView ;

    private TextView textView;

    private Handler handler = new Handler();

    private static final int PERMISSION_WRITE_EX_STR = 1;

    private Runnable myRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_read_view);
        Button bt = findViewById(R.id.button1);

        textView = findViewById(R.id.textView);
        mBarcodeView = findViewById(R.id.barcodeView);
        mBarcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                handler.removeCallbacks(myRunnable);

//                TextView textView = findViewById(R.id.textView);
                String[] temp = getResources().getStringArray(R.array.temp);
                temp[0] = temp[0].replace("$time",new Date().toString())
                        .replace("$qr", result.getText());
                textView.setText(temp[0]);
            }
        });
        mBarcodeView.getStatusView().setText("QRコードをかざしてください");

        bt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            finish();
        });
        //権限をリクエスト
        if (Build.VERSION.SDK_INT >= 23) {
            if(ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.CAMERA,
                        },
                        PERMISSION_WRITE_EX_STR);
            }
        }
        myRunnable = new Runnable() {
            @Override
            public void run() {
                finish();
            }
        };
        handler.postDelayed(myRunnable,5000);

    }

    @Override
    public void onResume() {
        super.onResume();
        mBarcodeView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBarcodeView.pause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mBarcodeView.destroyDrawingCache();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        if (grantResults.length <= 0) {
            return;
        }
        switch (requestCode) {
            case PERMISSION_WRITE_EX_STR: {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // 不許可
                    Toast.makeText(this,
                            "アプリを起動できません", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
            return;
        }
    }
}