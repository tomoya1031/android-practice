package com.example.myapplication.apis;

import com.example.myapplication.utils.ConnectionUtil;
import com.example.myapplication.utils.JsonUtil;
import com.example.myapplication.utils.PropertyUtil;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class TestApi {
    private String urlGet = "http://api.openweathermap.org/data/2.5/weather?q=London&appid={API key}" ;
    private String urlPost = "今のところ予定なし";
    private PropertyUtil config = new PropertyUtil();
    private ConnectionUtil connectionUtil = new ConnectionUtil();

    public JsonUtil getAPI() throws JSONException {
        InputStream inputStream = null;
        String result = "";
        String str = "";
        JsonUtil json = new JsonUtil();
        try {
            //configファイルからAPIKEY取得
            String key = (String) config.get("APIKEY");
            urlGet = urlGet.replace("{API key}", key);
            HttpURLConnection urlConnection = connectionUtil.connection(urlGet, "GET");
            // レスポンスコードを取得
            json.setStatusCode(urlConnection.getResponseCode());
            // レスポンス結果を取得
            inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            result = bufferedReader.readLine();
            while (result != null){
                str += result;
                result = bufferedReader.readLine();
            }
            json.setResult(str);
            bufferedReader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    //postはとりあえず適当に書いただけ
    public String postAPI(){
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String result = "";
        String str = "";
        try {
            // リクエストボディに格納するデータ
            String postData = "name=foge&type=fogefoge";

            HttpURLConnection urlConnection = connectionUtil.connection(urlPost, "POST");
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
