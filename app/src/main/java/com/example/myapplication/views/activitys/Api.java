package com.example.myapplication.views.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.apis.TestApi;
import com.example.myapplication.apis.TestApi2;
import com.example.myapplication.dto.WeatherDto;
import com.example.myapplication.dto.WindDto;
import com.example.myapplication.models.apis.WeatherInterface;
import com.example.myapplication.models.apis.WeatherNews;
import com.example.myapplication.utils.JsonUtil;
import com.example.myapplication.utils.PropertyUtil;
import com.example.myapplication.utils.RetrofitUtil;

import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Api extends AppCompatActivity implements View.OnClickListener {

    private static TestApi api = new TestApi();

    private static TestApi2 api2 = new TestApi2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        //API呼び出し方お試し実装
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PropertyUtil config = new PropertyUtil();
                    String key = (String) config.get("APIKEY");
                    Retrofit retrofit = new RetrofitUtil().httpConection();
                    WeatherInterface myService = retrofit.create(WeatherInterface.class);
                    //パターン①
                    try {
                        Response<WeatherNews> res = myService.getWeatherByAppId("London",key).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //パターン②
                    Call<WeatherNews> callObject = myService.getWeatherByAppId("London",key);
                    //コールバック処理
                    callObject.enqueue(new Callback<WeatherNews>()
                    {
                        @Override
                        public void onResponse(Call<WeatherNews> call, Response<WeatherNews> response) {
                            if (response.isSuccessful())
                            {
                                WeatherNews device = response.body();
                            }
                        }
                        @Override
                        public void onFailure(Call<WeatherNews> call, Throwable t) {
                            System.out.println(t.getLocalizedMessage());
                        }
                    });

                    //パターン
                    JsonUtil json = api.getAPI();
                    if(json.getStatusCode() == HttpURLConnection.HTTP_OK){
                        WindDto c = (WindDto) json.convObject(json.getResult(), "wind", WindDto.class);
                        List<WeatherDto> d = (List<WeatherDto>) json.convArrey(json.getResult(), "weather", WeatherDto.class);
                    } else {
                        //なんかエラーなら書く
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Button f = findViewById(R.id.fin);
        f.setOnClickListener(v -> {
            finish();
        });
    }


    @Override
    public void onClick(View view) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
