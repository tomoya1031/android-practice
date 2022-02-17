package com.example.myapplication;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.views.QrReadView;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.Date;

public class HogeService extends AppCompatActivity {

    private CompoundBarcodeView mBarcodeView ;

    public void hoge(View view, TextView textView, Resources res, QrReadView a) {
//        setContentView(R.layout.qr_read_view);
//        mBarcodeView = findViewById(R.id.barcodeView);
        mBarcodeView = (CompoundBarcodeView) view;
        mBarcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {

//                TextView textView = findViewById(R.id.textView);
                String[] temp = res.getStringArray(R.array.temp);
                temp[0] = temp[0].replace("$time",new Date().toString())
                        .replace("$qr", result.getText());
                textView.setText(temp[0]);
//                if(!result.getText().isEmpty()){
//                    Intent intent = new Intent(getApplication(), QrRead.class);
//                    startActivity(intent);
//                }
//                Intent intent = new Intent(getApplication(), QrReadView.class);
//                startActivity(intent);
            }
        });
        mBarcodeView.getStatusView().setText("QRコードをかざしてください");
    }

    public void hoge2(){
        mBarcodeView.resume();
    }

    public void hoge3(){
        mBarcodeView.pause();
    }


}
