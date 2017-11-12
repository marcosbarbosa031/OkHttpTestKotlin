package com.example.marcos.okhttptest

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import org.json.JSONArray
import org.json.JSONObject

import java.io.IOException

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import java.lang.IllegalArgumentException
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private var buttonSendGET: Button? = null
    private var buttonSendPOST: Button? = null
    private var jsonTextField: TextView? = null
    private var input: EditText? = null

    private var client: OkHttpClient? = null
    private var request: Request? = null
//    private val url: String? = null// = "https://minha-merenda.herokuapp.com/ts830/list/escola";
    private var json: String? = null
    private var jsonresp = JSONArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSendGET = findViewById<View>(R.id.buttonGET) as Button
        buttonSendPOST = findViewById<View>(R.id.buttonPOST) as Button
        jsonTextField = findViewById<View>(R.id.jsonField) as TextView
        input = findViewById<View>(R.id.urlInput) as EditText


        buttonSendGET!!.setOnClickListener {
            jsonTextField!!.text = "Awaiting response..."

            thread(){
                try{
                    val http = OkHttpClass()
                    client = http.client
                    request = http.getRequest(input!!.text.toString())
                    json = http.GETurl(client, request)

                    this.runOnUiThread {
                        try {
                            jsonresp = org.json.JSONArray(json)
                            var s = "";
                            for (i in 0 until jsonresp.length())
                                s += jsonresp.getJSONObject(i).getString("escolaNome") + "\n"
                            jsonTextField!!.text = s;
                        }catch (e : JSONException){
                            jsonTextField!!.text = json
                        }
                    }

                }catch (e : IllegalArgumentException){
                    jsonTextField!!.text = e.message
                }
            }
        }
    }

}
