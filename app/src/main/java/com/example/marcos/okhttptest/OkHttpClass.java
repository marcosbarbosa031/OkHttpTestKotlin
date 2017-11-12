package com.example.marcos.okhttptest;

import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Marcos on 12/11/2017.
 */

public class OkHttpClass {

    private OkHttpClient client;
    private Request request;
    private String json;
    private JSONArray jsonarray;
    //private static final String TAG = MainActivity.class.getName();

    public OkHttpClass() {
    }

    public void GETurl(OkHttpClient client, Request request,final TextView jsonTextField, final MainActivity a){
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                json = e.getMessage();
//                Log.i("TAG", json);
                new Thread(){
                    public void run(){
                        a.runOnUiThread(new Runnable(){

                            @Override
                            public void run() {
                                try {
                                    jsonarray = new JSONArray(json);
                                    Log.i("TAG", jsonarray.toString());
                                    jsonTextField.setText(jsonarray.toString());
                                    jsonTextField.setMovementMethod(new ScrollingMovementMethod());
                                } catch (JSONException e1) {
                                    jsonTextField.setText(json);
                                    jsonTextField.setMovementMethod(new ScrollingMovementMethod());
                                }
                            }
                        });
                    }
                }.start();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                json = response.body().string();
                //Log.i("TAG", json);
                new Thread(){
                    public void run(){
                        a.runOnUiThread(new Runnable(){

                            @Override
                            public void run() {
                                //JSONarray Return
                                try {
                                    jsonarray = new JSONArray(json);
                                    Log.i("TAG", jsonarray.toString());
                                    jsonTextField.setText(jsonarray.toString());
                                    jsonTextField.setMovementMethod(new ScrollingMovementMethod());
                                } catch (JSONException e) {
                                    jsonTextField.setText(json);
                                    jsonTextField.setMovementMethod(new ScrollingMovementMethod());
                                }
                            }
                        });
                    }
                }.start();
            }
        });
        //return jsonarray;
        //return json;
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
