package com.example.myapplication.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class ConnectionUtil {

    public HttpURLConnection connection(String httpUrl, String method) throws IOException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(httpUrl);
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
        urlConnection.setRequestMethod(method);
        //レスポンスボディの受信を許可する
        urlConnection.setDoInput(true);
        // リクエストボディの送信を許可する
        urlConnection.setDoOutput(true);
        // 通信開始
        urlConnection.connect();
        return urlConnection;
    }
}
