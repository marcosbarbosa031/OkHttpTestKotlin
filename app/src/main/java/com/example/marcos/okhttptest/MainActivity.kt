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

class MainActivity : AppCompatActivity() {
    private var buttonSendRequest: Button? = null
    private var jsonTextField: TextView? = null
    private var input: EditText? = null

    private var client: OkHttpClient? = null
    private var request: Request? = null
    private val url: String? = null// = "https://minha-merenda.herokuapp.com/ts830/list/escola";
    private val json: String? = null
    private val jsonresp = JSONArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSendRequest = findViewById<View>(R.id.buttonRequest) as Button
        jsonTextField = findViewById<View>(R.id.jsonField) as TextView
        input = findViewById<View>(R.id.urlInput) as EditText


        buttonSendRequest!!.setOnClickListener {
            try {
                val http = OkHttpClass()
                client = http.client
                request = http.getRequest(input!!.text.toString())
                http.connect(client, request, jsonTextField, this@MainActivity)
            } catch (e: IllegalArgumentException) {
                jsonTextField!!.text = e.message
            }
        }
    }

}
