package com.example.myapplication.utils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private Integer statusCode;

    private String result;

    private static Gson mapper = new Gson();

    public JsonUtil() {
    }

    public JsonUtil(Integer statusCode, String result) {
        this.statusCode = statusCode;
        this.result = result;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    /**
     * JSON → Object変換処理
     *
     * @param json
     * @param name
     * @param c
     */
    public static Object convObject(String json, String name, Class<?> c) throws JSONException {
        String wind = new JSONObject(json).getString(name);
        return mapper.fromJson(wind, c);
    }

    /**
     * JSON → Object変換処理(配列 → リスト変換用)
     *
     * @param json
     * @param name
     * @param c
     */
    public static Object convArrey(String json, String name, Class<?> c) throws JSONException {
        List<Object> obj = new ArrayList<>();
        JSONArray rootJSON = new JSONObject(json).getJSONArray(name);

        for (int i = 0; i < rootJSON.length(); i++) {
            JSONObject data = rootJSON.getJSONObject(i);
            obj.add(mapper.fromJson(data.toString(), c));
        }
        return obj;
    }

}