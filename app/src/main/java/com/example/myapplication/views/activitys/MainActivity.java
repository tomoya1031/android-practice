package com.example.myapplication.views.activitys;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.utils.DialogUtil;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static MainActivity sInstance;
    private static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //条件によってセットする
        setTheme(R.style.Theme_MyApplication_test);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setIcon(R.drawable.ic_home_black_24dp);
//        getSupportActionBar().setSubtitle("R.drawable.ic_baseline_check_circle_24");

        setContentView(R.layout.activity_main);

        Button bt1 = findViewById(R.id.button1);
        bt1.setOnClickListener(this);

        Button bt2 = findViewById(R.id.button2);
        bt2.setOnClickListener(this);

        Button bt3 = findViewById(R.id.button3);
        bt3.setOnClickListener(this);

        Button bt4 = findViewById(R.id.button4);
        bt4.setOnClickListener(this);

        Button bt5 = findViewById(R.id.button5);
        bt5.setOnClickListener(this);

        Button bt6 = findViewById(R.id.button6);
        bt6.setOnClickListener(this);

        blinkText(bt6, 1000, 500);

        sInstance = this;

        Button f = findViewById(R.id.fin);
        f.setOnClickListener(v -> {
            finish();
        });
    }

    public void onClick(View view) {
        Intent intent;
        Class<?> c = null;
        switch (view.getId()) {
            case R.id.button1:
                final Handler handler = new Handler();
                dialog = new Dialog(this);
                new DialogUtil().showDialog(dialog);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplication(), QrRead.class);
                        startActivity(intent);
                        dialog.dismiss();
                        finish();
                    }
                }, 5000);
                return;
            case R.id.button2:
                c =  Price.class;
                break;
            case R.id.button3:
                c =  Mask.class;
                break;
            case R.id.button4:
                c = Log.class;
                break;
            case R.id.button5:
                c = FileManag.class;
                break;
            case R.id.button6:
                c = Api.class;
                break;
            case R.id.fin:
                finish();
                return;
        }
        intent = new Intent(getApplication(), c);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static synchronized MainActivity getInstance() {
        return sInstance;
    }

    private void blinkText(Button txtView, long duration, long offset){
        Animation anm = new AlphaAnimation(0.0f, 1.0f);
        anm.setDuration(duration);
        anm.setStartOffset(offset);
        anm.setRepeatMode(Animation.REVERSE);
        anm.setRepeatCount(Animation.INFINITE);
        txtView.startAnimation(anm);
    }
}