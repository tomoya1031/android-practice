package com.example.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.Date;


public class QrReadView extends AppCompatActivity {

    private CompoundBarcodeView mBarcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_read_view);

        mBarcodeView = findViewById(R.id.barcodeView);
        mBarcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {

                TextView textView = findViewById(R.id.textView);
                Resources res = getResources();
                String[] temp = res.getStringArray(R.array.temp);
                temp[0] = temp[0].replace("$time",new Date().toString())
                        .replace("$qr", result.getText());
                textView.setText(temp[0]);
//                if(!result.getText().isEmpty()){
//                    Intent intent = new Intent(getApplication(), QrRead.class);
//                    startActivity(intent);
//                }
            }
        });
//        bt.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplication(), SubActivity.class);
//            startActivity(intent);
//        });
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}