package com.example.myapplication.models.apis;

import com.example.myapplication.dto.WeatherDto;
import com.example.myapplication.dto.WindDto;
import com.example.myapplication.utils.PropertyUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestApi {
    private String urlGet = "http://api.openweathermap.org/data/2.5/weather?q=London&appid={API key}" ;
    private String urlPost = "今のところ予定なし";
    private PropertyUtil config = new PropertyUtil();

    public String getAPI() throws JSONException {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String result = "";
        String str = "";
        try {
            //configファイルからAPIKEY取得
            String key = (String) config.get("APIKEY");
            urlGet = urlGet.replace("{API key}", key);
//            urlGet = urlGet.replace("{city}", "Tokyo,JP");
            URL url = new URL(urlGet);
            // 接続先URLへのコネクションを開く．まだ接続されていない
            urlConnection = (HttpURLConnection) url.openConnection();
            // 接続タイムアウトを設定
            urlConnection.setConnectTimeout(10000);
            // レスポンスデータの読み取りタイムアウトを設定
            urlConnection.setReadTimeout(10000);
            // ヘッダーにUser-Agentを設定
            urlConnection.addRequestProperty("User-Agent", "Android");
            // ヘッダーにAccept-Languageを設定
            urlConnection.addRequestProperty("Accept-Language", Locale.getDefault().toString());
            // HTTPメソッドを指定
            urlConnection.setRequestMethod("GET");
            //リクエストボディの送信を許可しない
            urlConnection.setDoOutput(false);
            //レスポンスボディの受信を許可する
            urlConnection.setDoInput(true);
            // 通信開始
            urlConnection.connect();
            // レスポンスコードを取得
            int statusCode = urlConnection.getResponseCode();
            // レスポンスコード200は通信に成功したことを表す
            if (statusCode == 200){
                inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                // 1行ずつレスポンス結果を取得しstrに追記
                result = bufferedReader.readLine();
                while (result != null){
                    str += result;
                    result = bufferedReader.readLine();
                }
                bufferedReader.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //配列の場合
        JSONArray rootJSON = new JSONObject(str).getJSONArray("weather");
        List<WeatherDto> wather = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < rootJSON.length(); i++) {
            JSONObject data = rootJSON.getJSONObject(i);
            wather.add(gson.fromJson(data.toString(), WeatherDto.class));
        }

        //通常パターン
        String wind = new JSONObject(str).getString("wind");
        WindDto t = gson.fromJson(wind, WindDto.class);

        return str;
    }

    //postはとりあえず適当に書いただけ
    public String postAPI(){
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String result = "";
        String str = "";
        try {
            URL url = new URL(urlPost);
            // 接続先URLへのコネクションを開く．まだ接続されていない
            urlConnection = (HttpURLConnection) url.openConnection();
            // リクエストボディに格納するデータ
            String postData = "name=foge&type=fogefoge";
            // 接続タイムアウトを設定
            urlConnection.setConnectTimeout(10000);
            // レスポンスデータの読み取りタイムアウトを設定
            urlConnection.setReadTimeout(10000);
            // ヘッダーにUser-Agentを設定
            urlConnection.addRequestProperty("User-Agent", "Android");
            // ヘッダーにAccept-Languageを設定
            urlConnection.addRequestProperty("Accept-Language", Locale.getDefault().toString());
            // HTTPメソッドを指定
            urlConnection.setRequestMethod("POST");
            //レスポンスボディの受信を許可する
            urlConnection.setDoInput(true);
            // リクエストボディの送信を許可する
            urlConnection.setDoOutput(true);
            // 通信開始
            urlConnection.connect();
            // リクエストボディの書き込みを行う
            outputStream = urlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();

            // レスポンスコードを取得
            int statusCode = urlConnection.getResponseCode();
            // レスポンスコード200は通信に成功したことを表す
            if (statusCode == 200){
                inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                // 1行ずつレスポンス結果を取得しstrに追記
                result = bufferedReader.readLine();
                while (result != null){
                    str += result;
                    result = bufferedReader.readLine();
                }
                bufferedReader.close();
            }
            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // レスポンス結果のJSONをString型で返す
        return str;
    }
}
