package com.example.myapplication.apis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestApi2 {

    //コールバック処理の共通化
    public <T> T getAPI(Call<T> callObject, Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        T t = null;
        callObject.enqueue(new Callback<T>()
        {
            T a;
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful())
                {
                    T a = response.body();
                    //処理書く
                }
            }
            @Override
            public void onFailure(Call<T> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }

        });

        return t;
    }

}
