package com.example.myapplication.views.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class QrRead extends AppCompatActivity {

//    private CaptureActivity PortraitActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_read);

        Button bt = findViewById(R.id.button);
//        Listener listener = new Listener();
//        bt.setOnClickListener(listener);
        bt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), QrReadView.class);
            startActivity(intent);
        });
    }

//    private class Listener implements View.OnClickListener {
//        @Override
//        public void onClick(View view) {
//
//            scanBarcode(view);
//        }
//    }
//
//    public void scanBarcode(View view) {
//        new IntentIntegrator(this).setPrompt("").initiateScan();
//    }
//
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