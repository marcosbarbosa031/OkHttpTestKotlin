package com.example.marcos.okhttptest;

import org.json.JSONArray;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Marcos on 12/11/2017.
 */

public class OkHttpClass {

    private OkHttpClient client;
    private Request request;
    private String json;
    private JSONArray jsonarray;
    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    //private static final String TAG = MainActivity.class.getName();

    public OkHttpClass() {
    }

    public String GETurl(OkHttpClient client, Request request) throws IOException {
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String POSTurl(OkHttpClient client, String json, String url) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        this.request = new Request.Builder()
                .url("https://"+url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public OkHttpClient getClient() {
        return client = new OkHttpClient();
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public Request getRequest(String url) {
        return request = new Request.Builder()
                .url("https://"+url)
                .build();
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
